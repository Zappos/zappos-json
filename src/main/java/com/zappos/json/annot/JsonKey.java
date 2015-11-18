package com.zappos.json.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonKey {

  public final static String DEFAULT_KEY = "";
  
  String value() default DEFAULT_KEY;
  
}
