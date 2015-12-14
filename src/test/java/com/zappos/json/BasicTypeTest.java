/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.zappos.json;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author hussachai
 *
 */
public class BasicTypeTest extends AbstractBaseTest {
  
  @Test
  public void testMap() {
    
    Map<String, String> simpleMap = new HashMap<>();
    simpleMap.put("A", "1");
    simpleMap.put("B", "2");
    
    String json = zapposJson.toJson(simpleMap);
    System.out.println(json);
    
    /* default implementation of Map is HashMap */
//    Assert.assertEquals(simpleMap, jacinda.fromJson(json, Map.class));
//    System.out.println((jacinda.fromJson(json, LinkedHashMap.class)));
  }
}
