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

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaSqlDateFormatter extends AbstractValueFormatter<Date> {

  @Override
  public String format(Date object) {
    return toJsonString(newFormatter().format(object));
  }
  
  @Override
  public Date parse(String string) throws Exception {
    return new Date(newFormatter().parse(string).getTime());
  }
  
  public SimpleDateFormat newFormatter() {
    if(getPattern() != null) return new SimpleDateFormat(getPattern());
    return new SimpleDateFormat("yyyy-MM-dd");
  }

  @Override
  public ValueFormatter<Date> newInstance() {
    return new JavaSqlDateFormatter();
  }
  
}
