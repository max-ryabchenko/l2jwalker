package com.l2jwalker.dao.hibernate;

import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.dao.support.SearchTemplate;
import com.l2jwalker.entity.Identifiable;
import javolution.util.FastList;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

import static com.l2jwalker.dao.hibernate.HibernateUtil.isEntityIdManuallyAssigned;

public abstract class HibernateGenericDao<Entity extends Identifiable<PK>, PK extends Serializable> extends HibernateDaoSupport implements
        GenericDao<Entity, PK> {

    protected final Logger log = Logger.getLogger(getClass());
    private Class<Entity> type;
    private String cacheRegion;
    private boolean cacheable = true;

    public HibernateGenericDao(Class<Entity> type) {
        this.type = type;
        this.cacheRegion = type.getCanonicalName();
    }

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Entity getById(PK pk) {
        if (null == pk) {
            return null;
        }
        return getHibernateTemplate().get(type, pk);
    }

    @Override
    public Entity get(Entity entity) {
        if (entity == null) {
            return null;
        }

        Serializable id = entity.getId();
        if (id == null) {
            return null;
        }

        Entity entityFound = getHibernateTemplate().get(type, id);

        if (entityFound == null) {
            log.warn("get returned null with pk=" + id);
        }

        return entityFound;
    }

    @Override
    public void refresh(Entity entity) {
        if (getHibernateTemplate().contains(entity)) {
            getHibernateTemplate().refresh(entity);
        }
    }

    @Override
    public void save(Entity entity) {
        Validate.notNull(entity, "The entity to save cannot be null element");

//        getHibernateTemplate().persist(entity);

        // creation with auto generated id
        if (!entity.isIdSet()) {
            getHibernateTemplate().persist(entity);
            return;
        }
//
//        // creation with manually assigned key
        if (isEntityIdManuallyAssigned(type) && !getHibernateTemplate().contains(entity)) {
            try {
                getHibernateTemplate().persist(entity);
            } catch (Exception e) {
                log.info(entity.getId());
                log.error(e);
            }
            return;
        }

        // other cases are update
        // the simple fact to invoke this method, from a service method annotated with @Transactional,
        // does the job (assuming the passed entity is present in the persistence context)
    }

    @Override
    public Entity merge(Entity entity) {
        return getHibernateTemplate().merge(entity);
    }

    @Override
    public void save(Iterable<Entity> entities) {
        Validate.notNull(entities, "The entities to save cannot be null");

        for (Entity entity : entities) {
            save(entity);
        }

        if (log.isDebugEnabled()) {
            log.debug("Save List done");
        }
    }

    @Override
    public void delete(Entity entity) {
//        if (getHibernateTemplate().contains(entity)) {
        getHibernateTemplate().delete(entity);
//        }
//        else {
//            // could be a delete on a transient instance
//            Entity entityRef = getHibernateTemplate().getReference(type, entity.getId());
//
//            if (entityRef != null) {
//                getHibernateTemplate().delete(entityRef);
//            } else {
//                log.warn("Attempt to delete an instance that is not present in the database: " + entity.toString());
//            }
//        }
    }

    @Override
    public void delete(PK pk) {
        Entity entity = getById(pk);
        if (null == entity) {
            return;
        }
        delete(entity);
    }

    @Override
    public void delete(Iterable<Entity> entities) {
        Validate.notNull(entities, "Cannot delete null collection");
        for (Entity entity : entities) {
            delete(entity);
        }
    }

    @Override
    public List<Entity> find(Entity entity, SearchTemplate searchTemplate) {
        Validate.notNull(entity, "The passed entity cannot be null");
        SearchTemplate localSearchTemplate = getLocalSearchTemplate(searchTemplate);

//        if (localSearchTemplate.hasNamedQuery()) {
//            return (List<Entity>) getNamedQueryUtil().findByNamedQuery(localSearchTemplate, entity);
//        }

        Criteria criteria = getCriteria(entity, localSearchTemplate);
        HibernateUtil.applyPaginationAndOrderOnCriteria(criteria, localSearchTemplate);

        List<Entity> entities = criteria.list();
        if (log.isDebugEnabled()) {
            log.debug("Returned " + entities.size() + " elements");
        }

        return entities;
    }

    @Override
    public int findCount(Entity entity, SearchTemplate searchTemplate) {
        Validate.notNull(entity, "The entity cannot be null");
        SearchTemplate localSearchTemplate = getLocalSearchTemplate(searchTemplate);

//        if (localSearchTemplate.hasNamedQuery()) {
//            return getNamedQueryUtil().numberByNamedQuery(localSearchTemplate, entity).intValue();
//        }

        Criteria criteria = getCriteria(entity, searchTemplate);
        criteria.setProjection(Projections.rowCount());

        Number count = (Number) criteria.uniqueResult();

        if (count != null) {
            return count.intValue();
        } else {
            log.warn("findCount returned null!");
            return 0;
        }
    }

    @Override
    public void quickInsert(Iterable<Entity> entities) {
        StatelessSession session = getSessionFactory().openStatelessSession();
        Transaction tx = session.beginTransaction();
        for (Entity entity : entities) {
            session.insert(entity);
        }
//        session.flush();
//        session.clear();

        tx.commit();
        session.close();
//        getHibernateTemplate().execute(new HibernateCallback<Entity>() {
//
//            public Entity doInHibernate(Session s)
//                    throws HibernateException, SQLException {
//                SQLQuery sql = s.nam("SELECT AVG(RATING) as r, COUNT(*) as c FROM RATINGS WHERE ADVENTURE_ID = ?");
//                sql.setParameter(0, adventureId);
//                sql.addScalar("r");
//                sql.addScalar("c");
//                return sql.list();
//            }
//        });
    }

    public SearchTemplate getLocalSearchTemplate(SearchTemplate searchTemplate) {
        if (searchTemplate == null) {
            SearchTemplate localSearchTemplate = new SearchTemplate();
            localSearchTemplate.setCacheable(getCacheable());
            localSearchTemplate.setCacheRegion(getCacheRegion());
            return localSearchTemplate;
        }

        SearchTemplate localSearchTemplate = new SearchTemplate(searchTemplate);

        if (searchTemplate.isCacheable() == null) {
            localSearchTemplate.setCacheable(getCacheable());
        }

        if (!searchTemplate.hasCacheRegion()) {
            localSearchTemplate.setCacheRegion(getCacheRegion());
        }

        return localSearchTemplate;
    }

    protected Criteria getCriteria(Entity entity, SearchTemplate searchTemplate) {
        Criteria criteria = getSession().createCriteria(type);
        setUpCacheOnCriteria(criteria, searchTemplate);
        List<Criterion> criterions = getCriterions(entity, searchTemplate);
        for (Criterion criterion : criterions) {
            criteria.add(criterion); // AND
        }

        return criteria;
    }

    protected List<Criterion> getCriterions(Entity entity, SearchTemplate searchTemplate) {
        List<Criterion> criterions = new FastList<Criterion>();

        // search by date range
        Criterion dateCriterion = getByDateCriterion(searchTemplate);
        if (dateCriterion != null) {
            criterions.add(dateCriterion);
        }

        // search by example
        Criterion exampleCriterion = getByExampleCriterion(entity, searchTemplate);
        if (exampleCriterion != null) {
            criterions.add(exampleCriterion);
        }

        // search by pattern
        Criterion patternCriterion = getByPatternCriterion(searchTemplate);
        if (patternCriterion != null) {
            criterions.add(patternCriterion);
        }

        // additional criterion (for example isNull criterion on x-to-many association)
        for (Criterion c : searchTemplate.getCriterions()) {
            criterions.add(c);
        }

        return criterions;
    }

    protected Criterion getByDateCriterion(SearchTemplate searchTemplate) {
        return HibernateUtil.constructDate(searchTemplate);
    }

    protected Criterion getByExampleCriterion(Entity entity, SearchTemplate searchTemplate) {
        Criterion example = HibernateUtil.constructExample(entity, searchTemplate);
        List<Criterion> extras = getByExampleExtraCriterions(entity, searchTemplate);

        if (extras != null && extras.size() > 0) {
            Junction conjunction = Restrictions.conjunction();
            for (Criterion extra : extras) {
                conjunction.add(extra);
            }
            conjunction.add(example);
            return conjunction;
        } else {
            return example;
        }
    }

    /**
     * Subclass may provide extra criterion. Used along with search by example. Default implementation does nothing.
     * Principal use case is to add criterion so the id fields part of the composite primary key are included in a
     * search by example.
     *
     * @param entity
     * @param searchTemplate
     * @return a criterion that will be appended (AND) to the example criterion. Null if no criterion should be
     * appended.
     */
    protected List<Criterion> getByExampleExtraCriterions(Entity entity, SearchTemplate searchTemplate) {
        return null;
    }

    protected Criterion getByPatternCriterion(SearchTemplate searchTemplate) {
        return HibernateUtil.constructPattern(type, searchTemplate);
    }

    protected void setUpCacheOnCriteria(Criteria criteria, SearchTemplate searchTemplate) {
        if (searchTemplate.isCacheable()) {
            criteria.setCacheable(true);

            if (searchTemplate.hasCacheRegion()) {
                criteria.setCacheRegion(searchTemplate.getCacheRegion());
            } else {
                criteria.setCacheRegion(getCacheRegion());
            }
        }
    }

    // ------------------------------------------
    // Configuration
    // ------------------------------------------

    public boolean getCacheable() {
        return cacheable;
    }

    /**
     * Set the default cacheable property to be used when the searchTemplate argument is null or do not specify one.
     */
    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    public String getCacheRegion() {
        return cacheRegion;
    }

    /**
     * Set the default cacheRegion property to use when the searchTemplate argument is null or do not specify one.
     */
    public void setCacheRegion(String cacheRegion) {
        this.cacheRegion = cacheRegion;
    }

}
