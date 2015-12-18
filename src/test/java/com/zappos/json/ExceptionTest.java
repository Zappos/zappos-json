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

import org.junit.Test;

import com.zappos.json.data.SimpleBean;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
public class ExceptionTest extends AbstractBaseTest {
  
  @Test(expected = NumberFormatException.class)
  public void testPrimitiveTypeMismatch() throws Throwable {
    try{
      String json = "Hello";
      zapposJson.fromJson(json, Integer.class);
    }catch(JsonException e){
      throw e.getCause();
    }
  }
  
  @Test(expected = ClassCastException.class)
  public void testTypeMismatch() throws Throwable {
    try{
      String json = "{\"b\":[1,2],\"b2\":false,\"d\":1.0,\"d2\":2.0,\"i\":1,\"i2\":2,\"string\":\"simple\"}";
      zapposJson.fromJson(json, SimpleBean.class);
    }catch(JsonException e){
      throw e.getCause();
    }
  }
  
  
}
