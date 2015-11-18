package com.zappos.culture.test.data;

import java.util.ArrayList;
import java.util.List;

public class FooBar {

  Foo foo = new Foo();
  Bar bar = new Bar();
  List<Foo> foos = new ArrayList<>();

  public FooBar() {
    for (int i = 0; i < 100; i++) {
      foos.add(new Foo());
    }
  }

  public Foo getFoo() {
    return foo;
  }

  public Bar getBar() {
    return bar;
  }

  public List<Foo> getFoos() {
    return foos;
  }
}