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

import com.zappos.json.ZapposJson;
import com.zappos.json.data.SimpleBean;

/**
 * 
 * @author hussachai
 *
 */
public abstract class AbstractBaseTest {

  protected static final ZapposJson jacinda = new ZapposJson(true);

  protected static SimpleBean createSimpleBean() {
    SimpleBean simple = new SimpleBean();
    simple.setString("simple");
    simple.setB(true);
    simple.setB2(false);
    simple.setI(new Integer(1));
    simple.setI2(2);
    simple.setD(new Double(1.0));
    simple.setD2(2.0);
    return simple;
  }
}
