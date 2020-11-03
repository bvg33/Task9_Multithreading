package com.epam.task9.data;

import com.epam.task9.data.exceptions.DataException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface Parser<T> {
    List<T> parse(String fileName) throws DataException;
}
