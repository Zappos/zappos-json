package com.zappos.json.util;

import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * @author Hussachai
 *
 */
public class TypeImpl {

  private Class<?> infClass;

  private Class<?> implClass;

  private TypeImpl(Class<?> infClass, Class<?> implClass) {
    this.infClass = infClass;
    this.implClass = implClass;
  }

  public Class<?> getInfClass() {
    return infClass;
  }

  public Class<?> getImplClass() {
    return implClass;
  }

  public static TypeImpl getMapImpl(Class<?> mapClass) {
    if (mapClass == Map.class || mapClass == AbstractMap.class) {
      return new TypeImpl(mapClass, HashMap.class);
    } else if (mapClass == ConcurrentMap.class) {
      return new TypeImpl(mapClass, ConcurrentHashMap.class);
    } else if (SortedMap.class.isAssignableFrom(mapClass)) {
      return new TypeImpl(mapClass, TreeMap.class);
    } else if (Modifier.isAbstract(mapClass.getModifiers())
        || Modifier.isInterface(mapClass.getModifiers())) {
      throw new RuntimeException(
          "Cannot find appropriate implementation of collection type: "
              + mapClass.getName());
    }
    return new TypeImpl(mapClass, mapClass);
  }

  public static TypeImpl getIterableImpl(Class<?> iterClass) {
    if (iterClass == List.class || iterClass == Collection.class
        || iterClass == Iterable.class) {
      return new TypeImpl(iterClass, ArrayList.class);
    } else if (Modifier.isAbstract(iterClass.getModifiers())
        || Modifier.isInterface(iterClass.getModifiers())) {
      throw new RuntimeException(
          "Cannot find appropriate implementation of collection type: "
              + iterClass.getName());
    }
    return new TypeImpl(iterClass, iterClass);
  }

}
