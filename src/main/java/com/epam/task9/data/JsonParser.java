package com.epam.task9.data;

import com.epam.task9.data.exceptions.DataException;
import com.epam.task9.logic.threads.DeliveryVan;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JsonParser implements Parser<DeliveryVan> {
    public List<DeliveryVan> parse(String fileName) throws DataException {
        ObjectMapper objectMapper = new ObjectMapper();
        DeliveryVan[] vans;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            vans = objectMapper.readValue(reader, DeliveryVan[].class);
        } catch (IOException e) {
            throw new DataException(e.getMessage(),e);
        }
        return Arrays.asList(vans);
    }
}
