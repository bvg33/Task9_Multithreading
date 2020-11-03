package com.epam.task9.logic.director;

import com.epam.task9.data.JsonParser;
import com.epam.task9.data.exceptions.DataException;
import com.epam.task9.logic.threads.DeliveryVan;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class DirectorTest {
    @Test
    public void testStartShouldReturnTrueWhenDataIsCorrect() throws DataException {
        JsonParser parser = Mockito.mock(JsonParser.class);
        List<DeliveryVan> listToReturn=new ArrayList<>(Arrays.asList(new DeliveryVan(1234,1,10)));
        when(parser.parse(anyString())).thenReturn(listToReturn);
        Director director=new Director(parser);
        boolean actual=director.start();
        Assert.assertEquals(true,actual);
    }
}
