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

package com.zappos.json.data;

import com.zappos.json.annot.JsonEnum;
import com.zappos.json.annot.JsonEnum.EnumValue;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
public class JsonEnumBean {
  
  public static enum MyEnum {
    FIRST, SECOND, THIRD
  }
  
  private MyEnum defaultEnum;
  
  @JsonEnum(EnumValue.ORDINAL)
  private MyEnum ordinalEnum;
  
  @JsonEnum(EnumValue.STRING)
  private MyEnum stringEnum;

  public MyEnum getDefaultEnum() {
    return defaultEnum;
  }

  public void setDefaultEnum(MyEnum defaultEnum) {
    this.defaultEnum = defaultEnum;
  }

  public MyEnum getOrdinalEnum() {
    return ordinalEnum;
  }

  public void setOrdinalEnum(MyEnum ordinalEnum) {
    this.ordinalEnum = ordinalEnum;
  }

  public MyEnum getStringEnum() {
    return stringEnum;
  }

  public void setStringEnum(MyEnum stringEnum) {
    this.stringEnum = stringEnum;
  }
  
  
}
