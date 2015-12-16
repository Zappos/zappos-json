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

import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author hussachai
 *
 */
public class BasicTypeTest extends AbstractBaseTest {
  
  @Test
  public void testNativeWrapper() {
    //Byte
    String json = zapposJson.toJson(new Byte("123"));
    Assert.assertEquals("123", json);
    
    //Boolean
    json = zapposJson.toJson(new Boolean(true));
    Assert.assertEquals("true", json);
    
    //Short
    json = zapposJson.toJson(new Short("123"));
    Assert.assertEquals("123", json);
    
    //Integer
    json = zapposJson.toJson(new Integer(123));
    Assert.assertEquals("123", json);
    
    //Long
    json = zapposJson.toJson(new Long(123));
    Assert.assertEquals("123", json);
    
    //Float
    json = zapposJson.toJson(new Float(123.123));
    Assert.assertEquals("123.123", json);
    
    //Double
    json = zapposJson.toJson(new Double(123.123));
    Assert.assertEquals("123.123", json);
  }
  
  @Test
  public void testArrayOfNative() throws Exception {
    //byte[] (Base64)
    String json = zapposJson.toJson("hello".getBytes());
    Assert.assertEquals("hello", new String(DatatypeConverter.parseBase64Binary(json)));
    
    //boolean[]
    json = zapposJson.toJson(new boolean[]{true, false, true});
    Assert.assertEquals("[true,false,true]", json);
    
    //char[]
    json = zapposJson.toJson(new char[]{'A', 'B', 'C'});
    Assert.assertEquals("[\"A\",\"B\",\"C\"]", json);
    
    //short[]
    json = zapposJson.toJson(new short[]{1,2,3});
    Assert.assertEquals("[1,2,3]", json);
    
    //int[]
    json = zapposJson.toJson(new int[]{1,2,3});
    Assert.assertEquals("[1,2,3]", json);
    
    //float[]
    json = zapposJson.toJson(new float[]{1.1f,2.2f,3.3f});
    Assert.assertEquals("[1.1,2.2,3.3]", json);
    
    //double[]
    json = zapposJson.toJson(new double[]{1.1,2.2,3.3});
    Assert.assertEquals("[1.1,2.2,3.3]", json);
  }
  
  @Test
  public void testArrayOfNativeWrapper() throws Exception {
    //byte[] (Base64)
    String json = zapposJson.toJson(new Byte[]{1,2,3});
    Assert.assertEquals("[1,2,3]", json);
    
    //boolean[]
    json = zapposJson.toJson(new Boolean[]{true, false, true});
    Assert.assertEquals("[true,false,true]", json);
    
    //char[]
    json = zapposJson.toJson(new Character[]{'A','B','C'});
    Assert.assertEquals("[\"A\",\"B\",\"C\"]", json);
    
    //short[]
    json = zapposJson.toJson(new Short[]{1,2,3});
    Assert.assertEquals("[1,2,3]", json);
    
    //int[]
    json = zapposJson.toJson(new Integer[]{1,2,3});
    Assert.assertEquals("[1,2,3]", json);
    
    //float[]
    json = zapposJson.toJson(new Float[]{1.1f,2.2f,3.3f});
    Assert.assertEquals("[1.1,2.2,3.3]", json);
    
    //double[]
    json = zapposJson.toJson(new Double[]{1.1,2.2,3.3});
    Assert.assertEquals("[1.1,2.2,3.3]", json);
  }
  
  @Test
  public void testString() {
    String json = zapposJson.toJson("hello");
    Assert.assertEquals("\"hello\"", json);
    
    json = zapposJson.toJson("escape \" should work");
    Assert.assertEquals("\"escape \\\" should work\"", json);
  }
  
  
}
