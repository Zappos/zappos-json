package com.zappos.json.format;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaTimeLocalDateFormatter extends AbstractValueFormatter<LocalDate> {

  private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
  
  @Override
  public String format(LocalDate object) {
    return toJsonString(formatter.format(object));
  }
  
  @Override
  public LocalDate parse(String string) throws Exception {
    return LocalDate.from(formatter.parse(string));
  }
  
  @Override
  public ValueFormatter<LocalDate> setPattern(String pattern) {
    super.setPattern(pattern);
    formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
    return this;
  }

  @Override
  public ValueFormatter<LocalDate> newInstance() {
    return new JavaTimeLocalDateFormatter();
  }
  
}
