package com.epam.task9.model.terminal;

import com.epam.task9.Main;
import com.epam.task9.logic.threads.DeliveryVan;
import com.epam.task9.model.exception.ResourceException;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class Terminal {
    private int goodsNumber=250;
    private static final int VAN_MAX_CAPACITY =10;
    private static final ReentrantLock LOCKER=new ReentrantLock();
    public void process(DeliveryVan van) throws ResourceException {
        LOCKER.lock();
        try {
        if (van.getGoodsNumber() == 0 && goodsNumber >= VAN_MAX_CAPACITY) {
            van.setGoodsNumber(VAN_MAX_CAPACITY);
            goodsNumber -= VAN_MAX_CAPACITY;
        } else if (van.getGoodsNumber() != 0) {
            int vanGoodsNumber=van.getGoodsNumber();
            goodsNumber+=vanGoodsNumber;
            van.setGoodsNumber(0);
        } else {
            throw new ResourceException("terminal is empty");
        }
        } finally {
            LOCKER.unlock();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Terminal{");
        sb.append("goodsNumber=").append(goodsNumber);
        sb.append('}');
        return sb.toString();
    }
}
