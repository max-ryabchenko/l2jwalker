package com.l2jwalker.service;

import com.l2jwalker.dao.NpcDataDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.NpcData;
import com.l2jwalker.service.support.GenericEntityServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("npcDataService")
public class NpcDataServiceImpl extends GenericEntityServiceImpl<NpcData, ID> implements com.l2jwalker.service.NpcDataService {

    private static final Logger log = Logger.getLogger(NpcDataServiceImpl.class);

    protected NpcDataDao npcDataDao;

    @Autowired
    public void setItemDataDao(NpcDataDao npcDataDao) {
        this.npcDataDao = npcDataDao;
    }

    @Override
    public GenericDao<NpcData, ID> getDao() {
        return npcDataDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NpcData getNew() {
        return new NpcData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NpcData getNewWithDefaults() {
        return getNew();
    }
}
