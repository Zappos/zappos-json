package com.zappos.json;

import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author Hussachai
 *
 */
public class JsonReaderInvoker {

  private ZapposJson jacinda;

  private Constructor<?> constructorWithString;

  private Constructor<?> constructorWithReader;

  private Method parseMethod;

  public JsonReaderInvoker(ZapposJson jacinda, Class<?> readerClass)
      throws Exception {
    this.jacinda = jacinda;
    this.constructorWithString = readerClass.getConstructor(ZapposJson.class,
        String.class);
    this.constructorWithReader = readerClass.getConstructor(ZapposJson.class,
        Reader.class);
    parseMethod = readerClass.getDeclaredMethod("parse");
  }

  @SuppressWarnings("unchecked")
  public <T> T readJson(Reader reader, Class<T> clazz) {
    try {
      Object jsonReader = constructorWithReader.newInstance(jacinda, reader);
      return (T) parseMethod.invoke(jsonReader);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e.getCause());
    } catch (InstantiationException | IllegalAccessException
        | IllegalArgumentException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T readJson(String json, Class<T> clazz) {
    try {
      Object jsonReader = constructorWithString.newInstance(jacinda, json);
      return (T) parseMethod.invoke(jsonReader);
    } catch (InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

}
