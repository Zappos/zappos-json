package com.zappos.json.format;

/**
 * 
 * @author Hussachai
 *
 */
public class NoOpValueFormatter extends AbstractValueFormatter<Object>{

  @Override
  public String format(Object object) {
    throw new UnsupportedOperationException("NoOp");
  }
  
  @Override
  public Object parse(String string) throws Exception {
    throw new UnsupportedOperationException("NoOp");
  }

  @Override
  public ValueFormatter<Object> newInstance() {
    throw new UnsupportedOperationException("NoOp");
  }
  
}
