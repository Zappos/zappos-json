package com.zappos.json;

import java.util.Base64;

import org.junit.Assert;
import org.junit.Test;

import com.zappos.jacinda.data.ByteArrayBean;

public class Base64Test extends AbstractBaseTest {
  
  @Test
  public void testByteArray() {
    ByteArrayBean bean = new ByteArrayBean();
    byte data[] = "it's supposed to be binary data".getBytes();
    String base64 = Base64.getEncoder().encodeToString(data);
    bean.setData(data);
    String json = jacinda.toJson(bean);
    System.out.println(json);
    
    Assert.assertTrue(json.contains(base64));
    
    ByteArrayBean bean2 = jacinda.fromJson(json, ByteArrayBean.class);
    Assert.assertArrayEquals(bean.getData(), bean2.getData());
  }
  
}
