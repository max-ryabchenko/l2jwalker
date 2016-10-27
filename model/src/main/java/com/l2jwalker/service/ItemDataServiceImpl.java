package com.l2jwalker.service;

import com.l2jwalker.dao.ItemDataDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.ItemData;
import com.l2jwalker.service.support.GenericEntityServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemDataService")
public class ItemDataServiceImpl extends GenericEntityServiceImpl<ItemData, ID> implements ItemDataService {

    private static final Logger log = Logger.getLogger(ItemDataServiceImpl.class);

    protected ItemDataDao itemDataDao;

    @Autowired
    public void setItemDataDao(ItemDataDao itemDataDao) {
        this.itemDataDao = itemDataDao;
    }

    @Override
    public GenericDao<ItemData, ID> getDao() {
        return itemDataDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemData getNew() {
        return new ItemData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemData getNewWithDefaults() {
        return getNew();
    }
}
