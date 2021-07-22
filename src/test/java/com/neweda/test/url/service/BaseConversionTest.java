package com.neweda.test.url.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BaseConversionTest {

    @InjectMocks
    private BaseConversion baseConversion;

    @Test
    public void encodeZeroSuccess() {
        String outcome = baseConversion.encode(0);
        assertEquals("a", outcome);
    }

    @Test
    public void encodeSuccess() {
        String outcome = baseConversion.encode(55);
        assertEquals("3", outcome);
    }

    @Test
    public void decodeSuccess() {
        long outcome = baseConversion.decode("3");
        assertEquals(55, outcome);
    }

}
