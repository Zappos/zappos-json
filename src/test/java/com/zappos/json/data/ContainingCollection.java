/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.zappos.json.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
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
