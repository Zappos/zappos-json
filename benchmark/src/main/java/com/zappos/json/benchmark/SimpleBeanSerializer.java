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
package com.zappos.json.benchmark;

import java.io.StringWriter;

import com.zappos.json.ZapposJson;
import com.zappos.json.benchmark.data.SimpleBean;
import com.zappos.json.util.JsonUtils;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
public class SimpleBeanSerializer {

  private static ZapposJson zapposJson = ZapposJson.getInstance();
  
  public static String toJson(SimpleBean bean) throws Exception {
    StringWriter writer = new StringWriter();
    writer.append("{").append("\"string\":\"");
    JsonUtils.escape(zapposJson, bean.getString(), writer);
    writer.append("\",\"flag\":").append(String.valueOf(bean.getFlag()))
    .append(",\"number\":").append(String.valueOf(bean.getNumber()));
    return writer.toString();
  }
  
}
