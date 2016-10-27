package com.l2jwalker.dao;

import com.l2jwalker.dao.hibernate.HibernateGenericDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.SkillData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SkillDataDaoImpl extends HibernateGenericDao<SkillData, ID> implements GenericDao<SkillData, ID>, SkillDataDao {

    public SkillDataDaoImpl() {
        super(SkillData.class);
    }

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
