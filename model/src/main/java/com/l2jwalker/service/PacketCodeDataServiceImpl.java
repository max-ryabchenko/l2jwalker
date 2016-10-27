package com.l2jwalker.service;

import com.l2jwalker.dao.PacketCodeDataDao;
import com.l2jwalker.dao.support.GenericDao;
import com.l2jwalker.entity.ID;
import com.l2jwalker.entity.PacketCodeData;
import com.l2jwalker.service.support.GenericEntityServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("packetCodeDataService")
public class PacketCodeDataServiceImpl extends GenericEntityServiceImpl<PacketCodeData, ID> implements PacketCodeDataService {

    private static final Logger log = Logger.getLogger(PacketCodeDataServiceImpl.class);

    protected PacketCodeDataDao packetCodeDataDao;

    @Autowired
    public void setPacketCodeDataDao(PacketCodeDataDao packetCodeDataDao) {
        this.packetCodeDataDao = packetCodeDataDao;
    }

    @Override
    public GenericDao<PacketCodeData, ID> getDao() {
        return packetCodeDataDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PacketCodeData getNew() {
        return new PacketCodeData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PacketCodeData getNewWithDefaults() {
        return getNew();
    }
}
