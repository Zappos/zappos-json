package com.zappos.json;

/**
 * 
 * @author hussachai
 *
 */
public enum JsonType {

  NUMBER, STRING, BOOLEAN, ARRAY, OBJECT;

  public static JsonType toJsonType(Class<?> clazz) {
    if (isStringType(clazz)) {
      return STRING;
    } else if (isNumberType(clazz)) {
      return NUMBER;
    } else if (isBooleanType(clazz)) {
      return BOOLEAN;
    } else if (isArrayType(clazz)) {
      return ARRAY;
    }
    return JsonType.OBJECT;
  }

  public static boolean isNumberType(Class<?> clazz) {
    if (Number.class.isAssignableFrom(clazz)) {
      return true;
    } else if (clazz.isPrimitive()) {
      return clazz != boolean.class && clazz != char.class;
    }
    return false;
  }

  public static boolean isStringType(Class<?> clazz) {
    return clazz == String.class;
  }

  public static boolean isBooleanType(Class<?> clazz) {
    return clazz == boolean.class || Boolean.class.isAssignableFrom(clazz);
  }

  public static boolean isArrayType(Class<?> clazz) {
    return clazz.isArray() || Iterable.class.isAssignableFrom(clazz);
  }
}
