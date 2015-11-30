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
