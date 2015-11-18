package com.zappos.json.format;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Hussachai
 *
 */
public class JavaSqlDateFormatter extends AbstractValueFormatter<Date> {

  @Override
  public String format(Date object) {
    return toJsonString(newFormatter().format(object));
  }
  
  @Override
  public Date parse(String string) throws Exception {
    return new Date(newFormatter().parse(string).getTime());
  }
  
  public SimpleDateFormat newFormatter() {
    if(getPattern() != null) return new SimpleDateFormat(getPattern());
    return new SimpleDateFormat("yyyy-MM-dd");
  }

  @Override
  public ValueFormatter<Date> newInstance() {
    return new JavaSqlDateFormatter();
  }
  
}
