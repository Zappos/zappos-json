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

package com.zappos.json;

import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author Hussachai
 *
 */
public class JsonWriter {

  /*
   * Static will not be copied. So they are fine to be here.
   */
  public static final String CONST_NULL = "null";
  public static final char CONST_DOUBLE_QUOTE = '"';
  public static final char CONST_COMMA = ',';
  public static final char CONST_COLON = ':';
  public static final char CONST_OPEN_OBJECT = '{';
  public static final char CONST_CLOSE_OBJECT = '}';
  public static final char CONST_OPEN_ARRAY = '[';
  public static final char CONST_CLOSE_ARRAY = ']';
  
  /*
   * Source: GSON
   * From RFC 4627, "All Unicode characters may be placed within the
   * quotation marks except for the characters that must be escaped:
   * quotation mark, reverse solidus, and the control characters
   * (U+0000 through U+001F)."
   *
   * We also escape '\u2028' and '\u2029', which JavaScript interprets as
   * newline characters. This prevents eval() from failing with a syntax
   * error. http://code.google.com/p/google-gson/issues/detail?id=341
   */
  private static final String[] REPLACEMENT_CHARS;
  private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
  static {
    REPLACEMENT_CHARS = new String[128];
    for (int i = 0; i <= 0x1f; i++) {
      REPLACEMENT_CHARS[i] = String.format("\\u%04x", (int) i);
    }
    REPLACEMENT_CHARS['"'] = "\\\"";
    REPLACEMENT_CHARS['\\'] = "\\\\";
    REPLACEMENT_CHARS['\t'] = "\\t";
    REPLACEMENT_CHARS['\b'] = "\\b";
    REPLACEMENT_CHARS['\n'] = "\\n";
    REPLACEMENT_CHARS['\r'] = "\\r";
    REPLACEMENT_CHARS['\f'] = "\\f";
    HTML_SAFE_REPLACEMENT_CHARS = REPLACEMENT_CHARS.clone();
    HTML_SAFE_REPLACEMENT_CHARS['<'] = "\\u003c";
    HTML_SAFE_REPLACEMENT_CHARS['>'] = "\\u003e";
    HTML_SAFE_REPLACEMENT_CHARS['&'] = "\\u0026";
    HTML_SAFE_REPLACEMENT_CHARS['='] = "\\u003d";
    HTML_SAFE_REPLACEMENT_CHARS['\''] = "\\u0027";
  }
  
  protected ZapposJson jacinda;

  public JsonWriter(ZapposJson jacinda) {
    this.jacinda = jacinda;
  }
  
  public static void writeString(byte value[], Writer writer) throws IOException {
    writer.write(JsonWriter.CONST_DOUBLE_QUOTE);
    writer.write(DatatypeConverter.printBase64Binary(value));
    writer.write(JsonWriter.CONST_DOUBLE_QUOTE);
  }
  
  public static void writeString(String value, Writer writer, boolean htmlSafe) throws IOException {
    writer.write(JsonWriter.CONST_DOUBLE_QUOTE);
    String[] replacements = (htmlSafe) ? HTML_SAFE_REPLACEMENT_CHARS : REPLACEMENT_CHARS;
    int last = 0;
    int length = value.length();
    for (int i = 0; i < length; i++) {
      char c = value.charAt(i);
      String replacement;
      if (c < 128) {
        replacement = replacements[c];
        if (replacement == null) {
          continue;
        }
      } else if (c == '\u2028') {
        replacement = "\\u2028";
      } else if (c == '\u2029') {
        replacement = "\\u2029";
      } else {
        continue;
      }
      if (last < i) {
        writer.write(value, last, i - last);
      }
      writer.write(replacement);
      last = i + 1;
    }
    if (last < length) {
      writer.write(value, last, length - last);
    }
    writer.write(JsonWriter.CONST_DOUBLE_QUOTE);
  }
  
}
