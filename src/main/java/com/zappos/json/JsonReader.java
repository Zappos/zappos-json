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
 *
 * Copyright (c) 2013, 2015 EclipseSource.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.zappos.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * The JSON parser template. @{code JsonReaderCodeGenerator} generates the custom parser 
 * for a target type using this class as a template.
 * 
 * The code was modified from com.eclipsesource.jsonJsonParser from minimal-json project.
 * 
 * @author Hussachai Puripunpinyo
 * 
 */
public class JsonReader {
  
  /*
   * TODO: single-quote (') is not defined in JSON spec but it is valid in JavaScript.
   * We should make it configurable and the default configuration is throwing exception.
   * 
   */
  public static final int MAX_OBJECT_TREE_DEEP = 12;
  private static final int MIN_BUFFER_SIZE = 10;
  private static final int DEFAULT_BUFFER_SIZE = 1024;

  private final Reader reader;
  private final char[] buffer;
  private int bufferOffset;
  private int index;
  private int fill;
  private int line;
  private int lineOffset;
  private int current;
  private StringBuilder captureBuffer;
  private int captureStart;
  private String paths[] = new String[MAX_OBJECT_TREE_DEEP];

  @SuppressWarnings("unused")
  private ZapposJson jacinda; /* will be used by generated code */

  /*
   * | bufferOffset v [a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t] < input
   * [l|m|n|o|p|q|r|s|t|?|?] < buffer ^ ^ | index fill
   */

  public JsonReader(ZapposJson jacinda, String string) {
    this(jacinda, new StringReader(string), Math.max(MIN_BUFFER_SIZE,
        Math.min(DEFAULT_BUFFER_SIZE, string.length())));
  }

  public JsonReader(ZapposJson jacinda, Reader reader) {
    this(jacinda, reader, DEFAULT_BUFFER_SIZE);
  }

  public JsonReader(ZapposJson jacinda, Reader reader, int buffersize) {
    this.jacinda = jacinda;
    this.reader = reader;
    buffer = new char[buffersize];
    line = 1;
    captureStart = -1;
  }

  public Object parse() throws IOException {
    read();
    skipWhiteSpace();
    Object result = readValue(null, 0);
    skipWhiteSpace();
    if (!isEndOfText()) {
      throw error("Unexpected character");
    }
    return result;
  }

  private Object readValue(String path, int level) throws IOException {
    switch (current) {
    case 'n':
      return readNull();
    case 't':
      return readTrue();
    case 'f':
      return readFalse();
    case '\'':
    case '"':
      return readString();
    case '[':
      return readArray(path, level);
    case '{':
      return readObject(path, ++level);
    case '-':
    case '0':
    case '1':
    case '2':
    case '3':
    case '4':
    case '5':
    case '6':
    case '7':
    case '8':
    case '9':
      return readNumber();
    default:
      throw expected("value");
    }
  }

  private Object readArray(String path, int level) throws IOException{
    read();
    List<Object> array = new ArrayList<>();
    skipWhiteSpace();
    if (readChar(']')) {
      return array;
    }
    do {
      skipWhiteSpace();
      array.add(readValue(path, level));
      skipWhiteSpace();
    } while (readChar(','));
    if (!readChar(']')) {
      throw expected("',' or ']'");
    }
    return array;
  }

  private Object readObject(String path, int level) throws IOException{
    read();
    Map<String, Object> object = new HashMap<>(); //TODO: replace this with more lightweight object
    skipWhiteSpace();
    if (readChar('}')) {
      return object;
    }
    do {
      skipWhiteSpace();
      String name = readName();
      paths[level] = name;
      skipWhiteSpace();
      if (!readChar(':')) {
        throw expected("':'");
      }
      skipWhiteSpace();
      object.put(name, readValue(path, level));
      skipWhiteSpace();
    } while (readChar(','));
    if (!readChar('}')) {
      throw expected("',' or '}'");
    }

    return createObject(level, object);
  }

  private Object createObject(int level, Map<String, Object> data) {
    // generated code
    return data;
  }

  @SuppressWarnings("unused")
  private String getPath(int level) {
    if (level == 1)
      return "";
    String path = paths[1];
    for (int i = 2; i < level; i++) {
      path = path + "." + paths[i];
    }
    return path;
  }

  private String readName() throws IOException{
    if (current != '"' && current != '\'') {
      throw expected("name");
    }
    return readStringInternal();
  }

  private Object readNull() throws IOException{
    read();
    readRequiredChar('u');
    readRequiredChar('l');
    readRequiredChar('l');
    return null;
  }

  private Object readTrue() throws IOException{
    read();
    readRequiredChar('r');
    readRequiredChar('u');
    readRequiredChar('e');
    return Boolean.TRUE;
  }

  private Object readFalse() throws IOException{
    read();
    readRequiredChar('a');
    readRequiredChar('l');
    readRequiredChar('s');
    readRequiredChar('e');
    return Boolean.FALSE;
  }

  private void readRequiredChar(char ch) throws IOException{
    if (!readChar(ch)) {
      throw expected("'" + ch + "'");
    }
  }

  private Object readString() throws IOException{
    return readStringInternal();
  }

  private String readStringInternal() throws IOException{
    read();
    startCapture();
    while (current != '"' && current != '\'') {
      if (current == '\\') {
        pauseCapture();
        readEscape();
        startCapture();
      } else if (current < 0x20) {
        throw expected("valid string character");
      } else {
        read();
      }
    }
    String string = endCapture();
    read();
    return string;
  }

  private void readEscape() throws IOException{
    read();
    switch (current) {
    case '"':
    case '/':
    case '\\':
      captureBuffer.append((char) current);
      break;
    case 'b':
      captureBuffer.append('\b');
      break;
    case 'f':
      captureBuffer.append('\f');
      break;
    case 'n':
      captureBuffer.append('\n');
      break;
    case 'r':
      captureBuffer.append('\r');
      break;
    case 't':
      captureBuffer.append('\t');
      break;
    case 'u':
      char[] hexChars = new char[4];
      for (int i = 0; i < 4; i++) {
        read();
        if (!isHexDigit()) {
          throw expected("hexadecimal digit");
        }
        hexChars[i] = (char) current;
      }
      captureBuffer.append((char) Integer.parseInt(new String(hexChars), 16));
      break;
    default:
      throw expected("valid escape sequence");
    }
    read();
  }

  private Object readNumber() throws IOException{
    startCapture();
    readChar('-');
    int firstDigit = current;
    if (!readDigit()) {
      throw expected("digit");
    }
    if (firstDigit != '0') {
      while (readDigit()) {
      }
    }
    readFraction();
    readExponent();
    return endCapture();
  }

  private boolean readFraction() throws IOException{
    if (!readChar('.')) {
      return false;
    }
    if (!readDigit()) {
      throw expected("digit");
    }
    while (readDigit()) {
    }
    return true;
  }

  private boolean readExponent() throws IOException{
    if (!readChar('e') && !readChar('E')) {
      return false;
    }
    if (!readChar('+')) {
      readChar('-');
    }
    if (!readDigit()) {
      throw expected("digit");
    }
    while (readDigit()) {
    }
    return true;
  }

  private boolean readChar(char ch) throws IOException{
    if (current != ch) {
      return false;
    }
    read();
    return true;
  }

  private boolean readDigit() throws IOException{
    if (!isDigit()) {
      return false;
    }
    read();
    return true;
  }

  private void skipWhiteSpace() throws IOException{
    while (isWhiteSpace()) {
      read();
    }
  }

  private void read() throws IOException{
    if (index == fill) {
      if (captureStart != -1) {
        captureBuffer.append(buffer, captureStart, fill - captureStart);
        captureStart = 0;
      }
      bufferOffset += fill;
      fill = reader.read(buffer, 0, buffer.length);
      index = 0;
      if (fill == -1) {
        current = -1;
        return;
      }
    }
    if (current == '\n') {
      line++;
      lineOffset = bufferOffset + index;
    }
    current = buffer[index++];
  }

  private void startCapture() {
    if (captureBuffer == null) {
      captureBuffer = new StringBuilder();
    }
    captureStart = index - 1;
  }

  private void pauseCapture() {
    int end = current == -1 ? index : index - 1;
    captureBuffer.append(buffer, captureStart, end - captureStart);
    captureStart = -1;
  }

  private String endCapture() {
    int end = current == -1 ? index : index - 1;
    String captured;
    if (captureBuffer.length() > 0) {
      captureBuffer.append(buffer, captureStart, end - captureStart);
      captured = captureBuffer.toString();
      captureBuffer.setLength(0);
    } else {
      captured = new String(buffer, captureStart, end - captureStart);
    }
    captureStart = -1;
    return captured;
  }

  private JsonException expected(String expected) {
    if (isEndOfText()) {
      return error("Unexpected end of input");
    }
    return error("Expected " + expected);
  }
  
  private JsonException error(String message) {
    int absIndex = bufferOffset + index;
    int column = absIndex - lineOffset;
    int offset = isEndOfText() ? absIndex : absIndex - 1;
    return new JsonException(message + "[offset:" + offset + ", line: " + line
        + ", column: " + (column - 1));
  }
  
  private boolean isWhiteSpace() {
    return current == ' ' || current == '\t' || current == '\n'
        || current == '\r';
  }

  private boolean isDigit() {
    return current >= '0' && current <= '9';
  }

  private boolean isHexDigit() {
    return current >= '0' && current <= '9' || current >= 'a' && current <= 'f'
        || current >= 'A' && current <= 'F';
  }

  private boolean isEndOfText() {
    return current == -1;
  }

}
