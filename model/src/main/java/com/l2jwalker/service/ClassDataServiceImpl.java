package com.l2jwalker.service;

import com.l2jwalker.dao.ClassDataDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ClassData;
import com.l2jwalker.entity.ID;
import com.l2jwalker.service.support.GenericEntityServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classDataService")
public class ClassDataServiceImpl extends GenericEntityServiceImpl<ClassData, ID> implements ClassDataService {

    private static final Logger log = Logger.getLogger(ClassDataServiceImpl.class);

    protected ClassDataDao classDataDao;

    @Autowired
    public void setClassDataDao(ClassDataDao classDataDao) {
        this.classDataDao = classDataDao;
    }

    @Override
    public GenericDao<ClassData, ID> getDao() {
        return classDataDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClassData getNew() {
        return new ClassData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClassData getNewWithDefaults() {
        return getNew();
    }
}
