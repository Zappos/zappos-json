package com.zappos.json.format;

import java.io.IOException;
import java.io.StringWriter;

import com.zappos.json.JsonWriter;
import com.zappos.json.util.Strings;

/**
 * 
 * @author Hussachai
 *
 */
public abstract class AbstractValueFormatter<T> implements ValueFormatter<T>{

  private String pattern;
  
  private boolean htmlSafe;
  
  @Override
  public ValueFormatter<T> setPattern(String pattern) {
    this.pattern = pattern;
    return this;
  }
  
  public String getPattern(){
    return pattern;
  }
  
  public boolean isHtmlSafe() {
    return htmlSafe;
  }

  public void setHtmlSafe(boolean htmlSafe) {
    this.htmlSafe = htmlSafe;
  }

  public String toJson(String value){
    return Strings.isNumber(value)? value: toJsonString(value);
  }
  
  public String toJsonString(String value){
    StringWriter writer = new StringWriter();
    try {
      JsonWriter.writeString(value, writer, htmlSafe);
    } catch (IOException e) {}
    return writer.toString();
  }
  
}
