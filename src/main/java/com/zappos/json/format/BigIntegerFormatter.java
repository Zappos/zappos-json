package com.zappos.json.format;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * 
 * @author Hussachai
 *
 */
public class BigIntegerFormatter extends AbstractValueFormatter<BigInteger> {

  @Override
  public String format(BigInteger object) {
    if(getPattern() != null){
      /* It's possible to have result containing , (comma)*/
      return toJson(new DecimalFormat(getPattern()).format(object));
    }
    return object.toString();
  }

  @Override
  public BigInteger parse(String string) throws Exception {
    if(getPattern() != null){
      DecimalFormat df = new DecimalFormat(getPattern());
      return BigInteger.valueOf(df.parse(string).longValue());
    }
    return new BigInteger(string);
  }

  @Override
  public ValueFormatter<BigInteger> newInstance() {
    return new BigIntegerFormatter();
  }
  
}
