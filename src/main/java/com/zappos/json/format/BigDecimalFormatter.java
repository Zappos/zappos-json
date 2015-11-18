package com.zappos.json.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * @author Hussachai
 *
 */
public class BigDecimalFormatter extends AbstractValueFormatter<BigDecimal> {
  
  @Override
  public String format(BigDecimal object) {
    if(getPattern() != null){
      /* It's possible to have result containing , (comma)*/
      return toJson(new DecimalFormat(getPattern()).format(object));
    }
    return object.toString();
  }
  
  @Override
  public BigDecimal parse(String string) throws Exception {
    if(getPattern() != null){
      DecimalFormat df = new DecimalFormat(getPattern());
      df.setParseBigDecimal(true);
      return (BigDecimal)df.parse(string);
    }
    return new BigDecimal(string);
  }

  @Override
  public ValueFormatter<BigDecimal> newInstance() {
    return new BigDecimalFormatter();
  }
  
}
