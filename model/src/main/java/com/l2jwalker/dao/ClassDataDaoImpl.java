package com.l2jwalker.dao;

import com.l2jwalker.dao.hibernate.HibernateGenericDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ClassData;
import com.l2jwalker.entity.ID;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassDataDaoImpl extends HibernateGenericDao<ClassData, ID> implements GenericDao<ClassData, ID>, ClassDataDao {

    public ClassDataDaoImpl() {
        super(ClassData.class);
    }

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
