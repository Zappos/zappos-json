package com.zappos.jacinda.data;

import com.zappos.json.annot.JsonEnum;
import com.zappos.json.annot.JsonEnum.EnumValue;

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
