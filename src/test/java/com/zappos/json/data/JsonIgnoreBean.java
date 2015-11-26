package com.zappos.json.data;

import com.zappos.json.annot.JsonIgnore;

public class JsonIgnoreBean {

  private String countMeIn = "Count Me In";
  
  @JsonIgnore
  private String ignoreMe = "Ignore Me";
  
  private String ignoreGetter = "Exclude me from getter";
  
  private String ignoreSetter = "Exclude me from setter";

  public String getCountMeIn() {
    return countMeIn;
  }

  public void setCountMeIn(String countMeIn) {
    this.countMeIn = countMeIn;
  }

  public String getIgnoreMe() {
    return ignoreMe;
  }

  public void setIgnoreMe(String ignoreMe) {
    this.ignoreMe = ignoreMe;
  }

  @JsonIgnore
  public String getIgnoreGetter() {
    return ignoreGetter;
  }

  public void setIgnoreGetter(String ignoreGetter) {
    this.ignoreGetter = ignoreGetter;
  }

  public String getIgnoreSetter() {
    return ignoreSetter;
  }
  
  @JsonIgnore
  public void setIgnoreSetter(String ignoreSetter) {
    this.ignoreSetter = ignoreSetter;
  }
  
  
}
