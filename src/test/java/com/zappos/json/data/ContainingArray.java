package com.zappos.json.data;

public class ContainingArray {

  private String strings[] = new String[]{"A", "B", "C"};

  private Integer integers[] = new Integer[]{1, 2, 3};

  private double doubles[] = new double[]{1.0, 2.0, 3.0};

  private SimpleBean simples[];

  public String[] getStrings() {
    return strings;
  }

  public void setStrings(String[] strings) {
    this.strings = strings;
  }

  public Integer[] getIntegers() {
    return integers;
  }

  public void setIntegers(Integer[] integers) {
    this.integers = integers;
  }

  public double[] getDoubles() {
    return doubles;
  }

  public void setDoubles(double[] doubles) {
    this.doubles = doubles;
  }

  public SimpleBean[] getSimples() {
    return simples;
  }

  public void setSimples(SimpleBean[] simples) {
    this.simples = simples;
  }

}
