package com.zappos.jacinda.data;

import com.zappos.json.annot.JsonKey;

/**
 * 
 * @author Hussachai
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
