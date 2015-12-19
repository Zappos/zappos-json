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
public class StringTypeTest extends AbstractBaseTest {

  @Test
  public void testRegularString(){
    String input = "Hello World";
    String expectedJson = '"' + input + '"';
    String json = zapposJson.toJson(input);
    Assert.assertEquals(expectedJson, json);
    
    String output = zapposJson.fromJson(json, String.class);
    Assert.assertEquals(input, output);
  }
  
  @Test 
  public void testEscapeString(){
    String input = "\" and \n will be escaped";
    System.out.println(input);
    String expectedJson = "\"\\\" and \\n will be escaped\"";
    String json = zapposJson.toJson(input);
    System.out.println(json);
    System.out.println(expectedJson);
    Assert.assertEquals(expectedJson, json);
    
    String output = zapposJson.fromJson(json, String.class);
    System.out.println(input);
    System.out.println(output);
    Assert.assertEquals(input, output);
  }
  
}


