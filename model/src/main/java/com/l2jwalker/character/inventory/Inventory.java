package com.l2jwalker.character.inventory;

import com.l2jwalker.character.etc.AbstractKnownList;
import com.l2jwalker.util.FinalContainer;
import org.apache.log4j.Logger;

public class Inventory extends AbstractKnownList<Item> {

    public static final int ADENA_ID = 57;
    private static final Logger LOG = Logger.getLogger(Inventory.class);

    public Inventory() {
        super(200);
    }

    public synchronized long itemCount(int id) {
        FinalContainer<Long> result = new FinalContainer<Long>(0L);
        getKnownList().values().stream().filter(item -> id == item.getItemId()).forEach(item -> result.set(result.get() + item.getCount()));
        return result.get();
    }

    public synchronized long adenaCount() {
        return itemCount(ADENA_ID);
    }

}
