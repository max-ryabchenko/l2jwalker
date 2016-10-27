package com.l2jwalker.dao;

import com.l2jwalker.dao.hibernate.HibernateGenericDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.ItemData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDataDaoImpl extends HibernateGenericDao<ItemData, ID> implements GenericDao<ItemData, ID>, ItemDataDao {

    public ItemDataDaoImpl() {
        super(ItemData.class);
    }

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
