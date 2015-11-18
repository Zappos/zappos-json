package com.zappos.jacinda.data;

import java.util.Arrays;

/**
 * 
 * @author hussachai
 *
 */
public class NestedBean {

  private NestedBean1 bean1;

  private NestedBean1 beans[];

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bean1 == null) ? 0 : bean1.hashCode());
    result = prime * result + Arrays.hashCode(beans);
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
    NestedBean other = (NestedBean) obj;
    if (bean1 == null) {
      if (other.bean1 != null)
        return false;
    } else if (!bean1.equals(other.bean1))
      return false;
    if (!Arrays.equals(beans, other.beans))
      return false;
    return true;
  }

  public NestedBean1 getBean1() {
    return bean1;
  }

  public void setBean1(NestedBean1 bean1) {
    this.bean1 = bean1;
  }

  public NestedBean1[] getBeans() {
    return beans;
  }

  public void setBeans(NestedBean1[] beans) {
    this.beans = beans;
  }

  public static class NestedBean1 {
    private NestedBean2 bean2;

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((bean2 == null) ? 0 : bean2.hashCode());
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
      NestedBean1 other = (NestedBean1) obj;
      if (bean2 == null) {
        if (other.bean2 != null)
          return false;
      } else if (!bean2.equals(other.bean2))
        return false;
      return true;
    }

    public NestedBean2 getBean2() {
      return bean2;
    }

    public void setBean2(NestedBean2 bean2) {
      this.bean2 = bean2;
    }
  }

  public static class NestedBean2 {
    private NestedBean3 bean3;

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((bean3 == null) ? 0 : bean3.hashCode());
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
      NestedBean2 other = (NestedBean2) obj;
      if (bean3 == null) {
        if (other.bean3 != null)
          return false;
      } else if (!bean3.equals(other.bean3))
        return false;
      return true;
    }

    public NestedBean3 getBean3() {
      return bean3;
    }

    public void setBean3(NestedBean3 bean3) {
      this.bean3 = bean3;
    }
  }

  public static class NestedBean3 {
    private SimpleBean simples[];
    private String value;

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(simples);
      result = prime * result + ((value == null) ? 0 : value.hashCode());
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
      NestedBean3 other = (NestedBean3) obj;
      if (!Arrays.equals(simples, other.simples))
        return false;
      if (value == null) {
        if (other.value != null)
          return false;
      } else if (!value.equals(other.value))
        return false;
      return true;
    }

    public SimpleBean[] getSimples() {
      return simples;
    }

    public void setSimples(SimpleBean[] simples) {
      this.simples = simples;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }
}
