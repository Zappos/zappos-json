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

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * @author Hussachai
 *
 */
public class BigDecimalFormatter extends AbstractValueFormatter<BigDecimal> {
  
  @Override
  public String format(BigDecimal object) {
    if(getPattern() != null){
      /* It's possible to have result containing , (comma)*/
      return toJson(new DecimalFormat(getPattern()).format(object));
    }
    return object.toString();
  }
  
  @Override
  public BigDecimal parse(String string) throws Exception {
    if(getPattern() != null){
      DecimalFormat df = new DecimalFormat(getPattern());
      df.setParseBigDecimal(true);
      return (BigDecimal)df.parse(string);
    }
    return new BigDecimal(string);
  }

  @Override
  public ValueFormatter<BigDecimal> newInstance() {
    return new BigDecimalFormatter();
  }
  
}
