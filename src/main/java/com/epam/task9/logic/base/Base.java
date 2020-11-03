package com.epam.task9.logic.base;

import com.epam.task9.logic.base.exception.BaseException;
import com.epam.task9.model.terminal.Terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base {
    private static Base INSTANCE;
    private static final int TERMINALS_NUMBER =3;
    private final Semaphore terminals =new Semaphore(TERMINALS_NUMBER,true);
    private final static Lock LOCK = new ReentrantLock();
    private final List<Terminal>terminalList= new ArrayList<Terminal>(Arrays.asList(new Terminal(),new Terminal(),new Terminal()));
    private Base(){

    }
    public Terminal getTerminal() throws BaseException {

        LOCK.lock();
        Terminal terminal = null;
        try {
            terminals.acquire();
            int permits=terminals.availablePermits();
            terminal = terminalList.remove(TERMINALS_NUMBER-permits-1);
        } catch (InterruptedException e) {
            throw new BaseException(e.getMessage(),e);
        } finally {
            LOCK.unlock();
        }
        return terminal;
    }

    public void returnTerminal(Terminal terminal) {
        LOCK.lock();
        try {
            terminals.release();
            terminalList.add(terminal);
        } finally {
            LOCK.unlock();
        }
    }

    public static Base getInstance() {
        Base local = INSTANCE;
        if (local == null) {
            LOCK.lock();
            local = INSTANCE;
            try {
                if (local == null) {
                    local = new Base();
                    INSTANCE = local;
                }
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Base{");
        sb.append("terminalList=").append(terminalList);
        sb.append('}');
        return sb.toString();
    }
}
