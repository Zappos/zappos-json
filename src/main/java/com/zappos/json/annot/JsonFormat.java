package com.zappos.json.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zappos.json.format.NoOpValueFormatter;
import com.zappos.json.format.ValueFormatter;

@Target({ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormat {
  
  /**
   * Format pattern
   * @return
   */
  String value() default "";
  
  Class<? extends ValueFormatter<?>> formatter() default NoOpValueFormatter.class;
  
}
