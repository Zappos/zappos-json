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

/**
 * 
 * @author Hussachai
 *
 */
public class NoOpValueFormatter extends AbstractValueFormatter<Object>{

  @Override
  public String format(Object object) {
    throw new UnsupportedOperationException("NoOp");
  }
  
  @Override
  public Object parse(String string) throws Exception {
    throw new UnsupportedOperationException("NoOp");
  }

  @Override
  public ValueFormatter<Object> newInstance() {
    throw new UnsupportedOperationException("NoOp");
  }
  
}
