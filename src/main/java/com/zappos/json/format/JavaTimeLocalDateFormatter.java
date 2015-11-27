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

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaTimeLocalDateFormatter extends AbstractValueFormatter<LocalDate> {

  private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
  
  @Override
  public String format(LocalDate object) {
    return toJsonString(formatter.format(object));
  }
  
  @Override
  public LocalDate parse(String string) throws Exception {
    return LocalDate.from(formatter.parse(string));
  }
  
  @Override
  public ValueFormatter<LocalDate> setPattern(String pattern) {
    super.setPattern(pattern);
    formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
    return this;
  }

  @Override
  public ValueFormatter<LocalDate> newInstance() {
    return new JavaTimeLocalDateFormatter();
  }
  
}
