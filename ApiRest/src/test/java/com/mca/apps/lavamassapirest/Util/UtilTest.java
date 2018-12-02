package com.mca.apps.lavamassapirest.Util;

import com.mca.apps.lavamassapirest.utils.G;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilTest {

    private static final Logger LOG = LoggerFactory.getLogger(UtilTest.class);

    @Test
    public void testBuilderCodUser(){
        String cod = G.buildCodUser("70417573", G.upCase("Cortegana"));
        LOG.info("\nCodigo: {}\nLongitud: {}", cod, cod.length());
        Assert.assertEquals(10, cod.length());
    }

    @Test
    public void testGetTimeStamp(){
        String timestamp = G.getTimestamp().toString();
        LOG.info("TIMESTAMP: {}",timestamp);
    }

    @Test
    public void testCapitalize(){
        String cap = G.capitalize("C A");
        LOG.info("TEXTO: {}",cap);
    }

}
