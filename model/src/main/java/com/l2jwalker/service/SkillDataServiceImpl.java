package com.l2jwalker.service;

import com.l2jwalker.dao.SkillDataDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.SkillData;
import com.l2jwalker.service.support.GenericEntityServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("skillDataService")
public class SkillDataServiceImpl extends GenericEntityServiceImpl<SkillData, ID> implements SkillDataService {

    private static final Logger log = Logger.getLogger(SkillDataServiceImpl.class);

    protected SkillDataDao skillDataDao;

    @Autowired
    public void setSkillDataDao(SkillDataDao skillDataDao) {
        this.skillDataDao = skillDataDao;
    }

    @Override
    public GenericDao<SkillData, ID> getDao() {
        return skillDataDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SkillData getNew() {
        return new SkillData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SkillData getNewWithDefaults() {
        return getNew();
    }
}
