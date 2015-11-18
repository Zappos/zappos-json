package com.zappos.culture.test.data;

import java.util.Date;

public class Bar extends Foo {

  double random = Math.random();
  Date date = new Date();
  Foo foo = new Foo();

  public double getRandom() {
    return random;
  }

  public void setRandom(double random) {
    this.random = random;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Foo getFoo() {
    return foo;
  }

  public void setFoo(Foo foo) {
    this.foo = foo;
  }

}