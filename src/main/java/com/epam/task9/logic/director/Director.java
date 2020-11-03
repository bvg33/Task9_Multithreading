package com.epam.task9.logic.director;

import com.epam.task9.Main;
import com.epam.task9.data.JsonParser;
import com.epam.task9.data.Parser;
import com.epam.task9.data.exceptions.DataException;
import com.epam.task9.logic.threads.DeliveryVan;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Director {
    private Parser<DeliveryVan> parser=new JsonParser();
    private static final Logger LOGGER=Logger.getLogger(Director.class);

    public Director(Parser parser) {
        this.parser = parser;
    }

    public boolean start(){
        List<DeliveryVan> deliveryVanList= null;
        try {
            deliveryVanList = parser.parse("Vans.json");
        } catch (DataException e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }
        int vansSize=deliveryVanList.size();
        final ExecutorService executorService= Executors.newFixedThreadPool(vansSize);
        deliveryVanList.forEach(deliveryVan->executorService.submit(deliveryVan));
        executorService.shutdown();
        return true;
    }
}
