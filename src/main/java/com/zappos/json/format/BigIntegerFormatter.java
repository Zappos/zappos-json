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

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * 
 * @author Hussachai
 *
 */
public class BigIntegerFormatter extends AbstractValueFormatter<BigInteger> {

  @Override
  public String format(BigInteger object) {
    if(getPattern() != null){
      /* It's possible to have result containing , (comma)*/
      return toJson(new DecimalFormat(getPattern()).format(object));
    }
    return object.toString();
  }

  @Override
  public BigInteger parse(String string) throws Exception {
    if(getPattern() != null){
      DecimalFormat df = new DecimalFormat(getPattern());
      return BigInteger.valueOf(df.parse(string).longValue());
    }
    return new BigInteger(string);
  }

  @Override
  public ValueFormatter<BigInteger> newInstance() {
    return new BigIntegerFormatter();
  }
  
}
