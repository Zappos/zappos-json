package com.zappos.json;

import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author Hussachai
 *
 */
public class JsonWriterInvoker {

  private Method writerMethod;
  private Object jsonWriter;

  public JsonWriterInvoker(Class<?> dataClass, Object jsonWriter) {
    try {
      this.writerMethod = jsonWriter.getClass().getDeclaredMethod("writeJson",
          dataClass, Writer.class, boolean.class);
    } catch (NoSuchMethodException | SecurityException e) {
      throw new RuntimeException(e);
    }
    this.jsonWriter = jsonWriter;
  }
  
  public void writeJson(Object data, Writer writer, boolean htmlSafe) {
    try {
      writerMethod.invoke(jsonWriter, data, writer, htmlSafe);
    } catch (IllegalAccessException | IllegalArgumentException
        | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }
}