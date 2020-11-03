package com.epam.task9.view;

import com.epam.task9.logic.base.Base;
import com.epam.task9.logic.threads.DeliveryVan;

public interface Printer {
    void print(DeliveryVan deliveryVan);
    void print(Base base);
}
