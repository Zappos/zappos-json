package com.zappos.json;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class BasicTypeTest extends AbstractBaseTest {
  
  @Test
  public void testMap() {
    
    Map<String, String> simpleMap = new HashMap<>();
    simpleMap.put("A", "1");
    simpleMap.put("B", "2");
    
    String json = jacinda.toJson(simpleMap);
    System.out.println(json);
    
    /* default implementation of Map is HashMap */
//    Assert.assertEquals(simpleMap, jacinda.fromJson(json, Map.class));
//    System.out.println((jacinda.fromJson(json, LinkedHashMap.class)));
  }
}
