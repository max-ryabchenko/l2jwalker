package com.l2jwalker.character.trade;

import com.l2jwalker.character.inventory.Item;
import com.l2jwalker.util.PropertiesReader;
import javolution.util.FastList;
import org.apache.log4j.Logger;

import java.util.List;

public class Trade {

    private static final Logger LOG = Logger.getLogger(Trade.class);

    private static final int REQUEST_DIALOG_SHOW_TIME = PropertiesReader.getIntegerProperty("request_dialog.show_time");
    private final List<Item> walkerInventoryTradeList = FastList.newInstance();
    private final List<Item> tradeOwnList = FastList.newInstance();
    private final List<Item> tradePartnerList = FastList.newInstance();
    private int tradePartnerId;
    private volatile boolean tradeRequested = false;
    private volatile boolean tradeStarted = false;
    private long tradeRequestStartTime;
    private volatile long tradeRequestDialogOpenTime = 0;

    public Trade() {

    }

    public void clear() {

    }

}
