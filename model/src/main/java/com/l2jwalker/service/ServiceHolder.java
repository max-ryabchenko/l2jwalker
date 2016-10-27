package com.l2jwalker.service;

public class ServiceHolder {

    private static volatile ServiceHolder instance;
    private SkillDataService skillDataService;
    private NpcDataService npcDataService;
    private ItemDataService itemDataService;

    public static ServiceHolder getInstance() {
        ServiceHolder localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceHolder.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ServiceHolder();
                }
            }
        }
        return localInstance;
    }

    public SkillDataService getSkillDataService() {
        return skillDataService;
    }

    public void setSkillDataService(SkillDataService skillDataService) {
        this.skillDataService = skillDataService;
    }

    public NpcDataService getNpcDataService() {
        return npcDataService;
    }

    public void setNpcDataService(NpcDataService npcDataService) {
        this.npcDataService = npcDataService;
    }

    public ItemDataService getItemDataService() {
        return itemDataService;
    }

    public void setItemDataService(ItemDataService itemDataService) {
        this.itemDataService = itemDataService;
    }

}
