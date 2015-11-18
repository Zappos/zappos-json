package com.zappos.jacinda.data;

public class ByteArrayBean {

  byte data[] = "it's supposed to be binary data".getBytes();

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
  
}
