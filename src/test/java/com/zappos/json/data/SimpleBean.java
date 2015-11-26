package com.zappos.json.data;

public class SimpleBean {

  private String string;

  private Boolean b;

  private boolean b2;

  private Integer i;

  private int i2;

  private Double d;

  private double d2;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((b == null) ? 0 : b.hashCode());
    result = prime * result + (b2 ? 1231 : 1237);
    result = prime * result + ((d == null) ? 0 : d.hashCode());
    long temp;
    temp = Double.doubleToLongBits(d2);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((i == null) ? 0 : i.hashCode());
    result = prime * result + i2;
    result = prime * result + ((string == null) ? 0 : string.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SimpleBean other = (SimpleBean) obj;
    if (b == null) {
      if (other.b != null)
        return false;
    } else if (!b.equals(other.b))
      return false;
    if (b2 != other.b2)
      return false;
    if (d == null) {
      if (other.d != null)
        return false;
    } else if (!d.equals(other.d))
      return false;
    if (Double.doubleToLongBits(d2) != Double.doubleToLongBits(other.d2))
      return false;
    if (i == null) {
      if (other.i != null)
        return false;
    } else if (!i.equals(other.i))
      return false;
    if (i2 != other.i2)
      return false;
    if (string == null) {
      if (other.string != null)
        return false;
    } else if (!string.equals(other.string))
      return false;
    return true;
  }

  public String getString() {
    return string;
  }

  public void setString(String string) {
    this.string = string;
  }

  public Boolean getB() {
    return b;
  }

  public void setB(Boolean b) {
    this.b = b;
  }

  public boolean isB2() {
    return b2;
  }

  public void setB2(boolean b2) {
    this.b2 = b2;
  }

  public Integer getI() {
    return i;
  }

  public void setI(Integer i) {
    this.i = i;
  }

  public int getI2() {
    return i2;
  }

  public void setI2(int i2) {
    this.i2 = i2;
  }

  public Double getD() {
    return d;
  }

  public void setD(Double d) {
    this.d = d;
  }

  public double getD2() {
    return d2;
  }

  public void setD2(double d2) {
    this.d2 = d2;
  }

}
