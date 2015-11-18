package com.zappos.json.format;

/**
 * 
 * The value formatter for other types of object such as Date, Enum, etc.
 * 
 * @author Hussachai
 *
 */
public interface ValueFormatter<T> {

  /**
   * Format specified object to string. The result will not be escaped. The
   * object formatter is responsible for JSON escaping.
   * 
   * @param object
   * @return
   */
  public String format(T object);

  /**
   * Parse string to target typpe
   * @param string
   * @return
   * @throws Exception
   */
  public T parse(String string) throws Exception;

  public ValueFormatter<T> setPattern(String pattern);
  
  public ValueFormatter<T> newInstance();
  
}
