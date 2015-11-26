package com.zappos.json.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class ContainingCollection {

  private Collection<Integer> integers;

  private List<String> strings;

  private ArrayList<Boolean> booleans;

  private Vector<SimpleBean> simples;

  public Collection<Integer> getIntegers() {
    return integers;
  }

  public void setIntegers(Collection<Integer> integers) {
    this.integers = integers;
  }

  public List<String> getStrings() {
    return strings;
  }

  public void setStrings(List<String> strings) {
    this.strings = strings;
  }

  public ArrayList<Boolean> getBooleans() {
    return booleans;
  }

  public void setBooleans(ArrayList<Boolean> booleans) {
    this.booleans = booleans;
  }

  public Vector<SimpleBean> getSimples() {
    return simples;
  }

  public void setSimples(Vector<SimpleBean> simples) {
    this.simples = simples;
  }

}
