package com.epam.task9.logic.threads;

import com.epam.task9.logic.base.Base;
import com.epam.task9.logic.base.exception.BaseException;
import com.epam.task9.model.exception.ResourceException;
import com.epam.task9.model.terminal.Terminal;
import com.epam.task9.view.ConsolePrinter;
import com.epam.task9.view.Printer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.log4j.Logger;

import java.util.Objects;

@JsonAutoDetect
public class DeliveryVan implements Runnable {
    private int goodsNumber;
    private int number;
    private int id;
    private static final Printer PRINTER=new ConsolePrinter();
    private static final Base BASE = Base.getInstance();
    private static final Logger LOGGER=Logger.getLogger(DeliveryVan.class);

    public DeliveryVan() {
    }

    public DeliveryVan(int number, int id, int goodsNumber) {
        this.number = number;
        this.id = id;
        this.goodsNumber=goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public int getNumber() {
        return number;
    }

    public int getId() {
        return id;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeliveryVan van = (DeliveryVan) o;
        return goodsNumber == van.goodsNumber &&
                number == van.number &&
                id == van.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsNumber, number, id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeliveryVan{");
        sb.append("goodsNumber=").append(goodsNumber);
        sb.append(", number=").append(number);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void run() {
        PRINTER.print(this);
        PRINTER.print(BASE);
        Terminal terminal=null;
        try {
            terminal = BASE.getTerminal();
            terminal.process(this);
        } catch (ResourceException | BaseException e) {
            LOGGER.error(e.getMessage(),e);
        }finally {
            BASE.returnTerminal(terminal);
        }
        PRINTER.print(BASE );
    }
}
