package com.l2jwalker.dao;

import com.l2jwalker.dao.hibernate.HibernateGenericDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.PacketCodeData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PacketDataDaoImpl extends HibernateGenericDao<PacketCodeData, ID> implements GenericDao<PacketCodeData, ID>, PacketCodeDataDao {

    public PacketDataDaoImpl() {
        super(PacketCodeData.class);
    }

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
