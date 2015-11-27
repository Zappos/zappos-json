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
