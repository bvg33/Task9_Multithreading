package com.epam.task9.view;

import com.epam.task9.logic.base.Base;
import com.epam.task9.logic.threads.DeliveryVan;

public class ConsolePrinter implements Printer{

    @Override
    public void print(DeliveryVan deliveryVan) {
        System.out.println(deliveryVan);
    }

    @Override
    public void print(Base base) {
        System.out.println(base);
    }
}
