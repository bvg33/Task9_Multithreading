package com.epam.task9.data;

import com.epam.task9.data.exceptions.DataException;
import com.epam.task9.logic.threads.DeliveryVan;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JsonParserTest {
    private final JsonParser parser=new JsonParser();
    private static final String CORRECT_FILE_NAME="src/test/resources/testCorrect.json";
    private static final String INCORRECT_FILE_NAME="src/test/resources/testIncorrect.json";
    @Test
    public void testParseShouldReturnListWhenFileIsCorrect() throws DataException {
        List<DeliveryVan>actual= parser.parse(CORRECT_FILE_NAME);
        List<DeliveryVan>expected= Arrays.asList(new DeliveryVan(0,1234,1),new DeliveryVan(10,1233,2),
                new DeliveryVan(5,1134,3));
        Assert.assertEquals(expected,actual);
    }
    @Test(expected =DataException.class )
    public void testParseShouldThrowExceptionWhenFileIsNotCorrect() throws DataException {
        List<DeliveryVan>actual= parser.parse(INCORRECT_FILE_NAME);
    }
}
