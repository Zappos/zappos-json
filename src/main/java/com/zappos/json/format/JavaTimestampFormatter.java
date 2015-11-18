package com.zappos.json.format;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaTimestampFormatter extends AbstractValueFormatter<Timestamp> {

  @Override
  public String format(Timestamp object) {
    if(getPattern() != null){
      return toJson(new SimpleDateFormat(getPattern()).format(object));
    }
    return String.valueOf(object.getTime());
  }

  @Override
  public Timestamp parse(String string) throws Exception {
    if(getPattern() != null){
      return new Timestamp(new SimpleDateFormat(getPattern()).parse(string).getTime());
    }
    return new Timestamp(Long.parseLong(string));
  }

  @Override
  public ValueFormatter<Timestamp> newInstance() {
    return new JavaTimestampFormatter();
  }
  
}
