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

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
public class PrimitiveTypeTest extends AbstractBaseTest {

  @Test
  public void testBoolean(){
    Boolean input = new Boolean("true");
    String json = zapposJson.toJson(input);
    Assert.assertEquals("true", json);
    Boolean output = zapposJson.fromJson(json, Boolean.class);
    Assert.assertEquals(input, output);
    
    boolean output2 = zapposJson.fromJson(json, boolean.class);
    Assert.assertTrue(output2 == input.booleanValue());
  }
  
  @Test
  public void testByte(){
    Byte input = new Byte("123");
    String json = zapposJson.toJson(input);
    Assert.assertEquals("123", json);
    Byte output = zapposJson.fromJson(json, Byte.class);
    Assert.assertEquals(input, output);
    
    byte output2 = zapposJson.fromJson(json, byte.class);
    Assert.assertTrue(output2 == input.byteValue());
  }
  
  @Test
  public void testChar(){
    Character input = new Character('A');
    String json = zapposJson.toJson(input);
    Assert.assertEquals("\"A\"", json);
    Character output = zapposJson.fromJson(json, Character.class);
    Assert.assertEquals(input, output);
    
    char output2 = zapposJson.fromJson(json, char.class);
    Assert.assertTrue(output2 == input.charValue());
  }
  
  @Test
  public void testShort(){
    Short input = new Short("123");
    String json = zapposJson.toJson(input);
    Assert.assertEquals("123", json);
    Short output = zapposJson.fromJson(json, Short.class);
    Assert.assertEquals(input, output);
    
    short output2 = zapposJson.fromJson(json, short.class);
    Assert.assertTrue(output2 == input.shortValue());
  }
  
  @Test
  public void testInt(){
    Integer input = new Integer("123");
    String json = zapposJson.toJson(input);
    Assert.assertEquals("123", json);
    Integer output = zapposJson.fromJson(json, Integer.class);
    Assert.assertEquals(input, output);
    
    int output2 = zapposJson.fromJson(json, int.class);
    Assert.assertTrue(output2 == input.intValue());
  }
  
  @Test
  public void testLong(){
    Long input = new Long("123");
    String json = zapposJson.toJson(input);
    Assert.assertEquals("123", json);
    Long output = zapposJson.fromJson(json, Long.class);
    Assert.assertEquals(input, output);
    
    long output2 = zapposJson.fromJson(json, long.class);
    Assert.assertTrue(output2 == input.longValue());
  }
  
  @Test
  public void testFloat(){
    Float input = new Float("123.123");
    String json = zapposJson.toJson(input);
    Assert.assertEquals("123.123", json);
    Float output = zapposJson.fromJson(json, Float.class);
    Assert.assertEquals(input, output);
    
    float output2 = zapposJson.fromJson(json, float.class);
    Assert.assertTrue(output2 == input.floatValue());
  }
  
  @Test
  public void testDouble(){
    Double input = new Double("123.123");
    String json = zapposJson.toJson(input);
    Assert.assertEquals("123.123", json);
    Double output = zapposJson.fromJson(json, Double.class);
    Assert.assertEquals(input, output);
    
    double output2 = zapposJson.fromJson(json, double.class);
    Assert.assertTrue(output2 == input.doubleValue());
  }
}
