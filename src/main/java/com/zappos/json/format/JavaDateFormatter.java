package com.zappos.json.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaDateFormatter extends AbstractValueFormatter<Date> {

  @Override
  public String format(Date object) {
    if(getPattern() != null){
      return toJsonString(new SimpleDateFormat(getPattern()).format(object));
    }
    return String.valueOf(((Date) object).getTime());/* milliseconds */
  }
  
  @Override
  public Date parse(String string) throws Exception {
    if(getPattern() != null){
      return new SimpleDateFormat(getPattern()).parse(string);
    }
    return new Date(Long.parseLong(string));
  }

  @Override
  public ValueFormatter<Date> newInstance() {
    return new JavaDateFormatter();
  }
  
}
