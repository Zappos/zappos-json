package com.zappos.json;

import com.zappos.json.ZapposJson;
import com.zappos.json.data.SimpleBean;

/**
 * 
 * @author hussachai
 *
 */
public abstract class AbstractBaseTest {

  protected static final ZapposJson jacinda = new ZapposJson(true);

  protected static SimpleBean createSimpleBean() {
    SimpleBean simple = new SimpleBean();
    simple.setString("simple");
    simple.setB(true);
    simple.setB2(false);
    simple.setI(new Integer(1));
    simple.setI2(2);
    simple.setD(new Double(1.0));
    simple.setD2(2.0);
    return simple;
  }
}
