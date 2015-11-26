package com.zappos.json.data;

import java.util.Map;

/**
 * 
 * @author hussachai
 *
 */
public class ContainingMap {

  private Map<String, String> stringMap;

  private Map<String, Integer> intMap;

  private Map<String, SimpleBean> mapOfObject;
  
  private Map<String, ContainingArray> mapOfObject2;
  
  private Map<String, Integer[]> mapOfArray;
  
  public Map<String, String> getStringMap() {
    return stringMap;
  }

  public void setStringMap(Map<String, String> stringMap) {
    this.stringMap = stringMap;
  }

  public Map<String, Integer> getIntMap() {
    return intMap;
  }

  public void setIntMap(Map<String, Integer> intMap) {
    this.intMap = intMap;
  }
  
  public Map<String, SimpleBean> getMapOfObject() {
    return mapOfObject;
  }

  public void setMapOfObject(Map<String, SimpleBean> mapOfObject) {
    this.mapOfObject = mapOfObject;
  }

  public Map<String, ContainingArray> getMapOfObject2() {
    return mapOfObject2;
  }

  public void setMapOfObject2(Map<String, ContainingArray> mapOfObject2) {
    this.mapOfObject2 = mapOfObject2;
  }

  public Map<String, Integer[]> getMapOfArray() {
    return mapOfArray;
  }

  public void setMapOfArray(Map<String, Integer[]> mapOfArray) {
    this.mapOfArray = mapOfArray;
  }
  
}
