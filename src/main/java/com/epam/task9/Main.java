package com.epam.task9;

import com.epam.task9.data.JsonParser;
import com.epam.task9.logic.director.Director;

public class Main {
    public static void main(String[] args) {
        JsonParser parser = new JsonParser();
        Director director=new Director(parser);
        director.start();
    }
}
