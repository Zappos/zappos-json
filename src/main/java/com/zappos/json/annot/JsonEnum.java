package com.zappos.json.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonEnum {
  
  EnumValue value();
  
  public static enum EnumValue {
    ORDINAL, STRING
  }
  
}
