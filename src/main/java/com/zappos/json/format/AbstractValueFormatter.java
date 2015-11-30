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

package com.zappos.json.format;

import java.io.IOException;
import java.io.StringWriter;

import com.zappos.json.JsonWriter;
import com.zappos.json.util.Strings;

/**
 * 
 * @author Hussachai
 *
 */
public abstract class AbstractValueFormatter<T> implements ValueFormatter<T>{

  private String pattern;
  
  private boolean htmlSafe;
  
  @Override
  public ValueFormatter<T> setPattern(String pattern) {
    this.pattern = pattern;
    return this;
  }
  
  public String getPattern(){
    return pattern;
  }
  
  public boolean isHtmlSafe() {
    return htmlSafe;
  }

  public void setHtmlSafe(boolean htmlSafe) {
    this.htmlSafe = htmlSafe;
  }

  public String toJson(String value){
    return Strings.isNumber(value)? value: toJsonString(value);
  }
  
  public String toJsonString(String value){
    StringWriter writer = new StringWriter();
    try {
      JsonWriter.writeString(value, writer, htmlSafe);
    } catch (IOException e) {}
    return writer.toString();
  }
  
}
