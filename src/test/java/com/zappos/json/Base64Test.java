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

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

import com.zappos.json.data.ByteArrayBean;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
public class Base64Test extends AbstractBaseTest {
  
  @Test
  public void testByteArray() {
    ByteArrayBean bean = new ByteArrayBean();
    byte data[] = "it's supposed to be binary data".getBytes();
    String base64 = Base64.getEncoder().encodeToString(data);
    bean.setData(data);
    String json = zapposJson.toJson(bean);
    System.out.println(json);
    
    Assert.assertTrue(json.contains(base64));
    
    ByteArrayBean bean2 = zapposJson.fromJson(json, ByteArrayBean.class);
    Assert.assertArrayEquals(bean.getData(), bean2.getData());
  }
  
}
