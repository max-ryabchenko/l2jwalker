package com.l2jwalker.dao;

import com.l2jwalker.dao.hibernate.HibernateGenericDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.NpcData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NpcDataDaoImpl extends HibernateGenericDao<NpcData, ID> implements GenericDao<NpcData, ID>, NpcDataDao {

    public NpcDataDaoImpl() {
        super(NpcData.class);
    }

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
