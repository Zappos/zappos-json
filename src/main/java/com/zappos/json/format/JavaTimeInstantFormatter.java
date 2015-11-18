package com.zappos.json.format;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaTimeInstantFormatter extends AbstractValueFormatter<Instant> {
  
  /**
   * This class is thread-safe
   */
  private DateTimeFormatter formatter = null;
  
  @Override
  public String format(Instant object) {
    if(formatter != null){
      return toJson(formatter.format(object));
    }
    return String.valueOf(object.toEpochMilli());
  }
  
  @Override
  public Instant parse(String string) throws Exception {
    if(formatter != null){
      return Instant.from(formatter.parse(string));
    }
    return Instant.ofEpochMilli(Long.parseLong(string));
  }

  @Override
  public ValueFormatter<Instant> setPattern(String pattern) {
    super.setPattern(pattern);
    formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
    return this;
  }
  
  @Override
  public ValueFormatter<Instant> newInstance() {
    return new JavaTimeInstantFormatter();
  }
  
  
}
