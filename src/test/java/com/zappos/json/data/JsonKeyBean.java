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

import com.zappos.json.annot.JsonKey;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
public class JsonKeyBean {
  
  public static class NickName {
    
    @JsonKey("short_name")
    private String name = "He";

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
    
  }
  
  @JsonKey("first_name")
  private String firstName = "Jason";
  
  @JsonKey("last_name")
  private String lastName = "Voorhees";

  @JsonKey("nick_name")
  private NickName nickname;
  
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public NickName getNickname() {
    return nickname;
  }

  public void setNickname(NickName nickname) {
    this.nickname = nickname;
  }
  
}
