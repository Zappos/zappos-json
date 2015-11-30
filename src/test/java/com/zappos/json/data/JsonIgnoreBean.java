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
