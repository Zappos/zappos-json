package json.test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zappos.json.ZapposJson;

/**
 * 
 * @author Hussachai
 *
 */
public class JsonReaderTmp {

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

  public JsonReaderTmp(ZapposJson jacinda, String string) {
    this(jacinda, new StringReader(string), Math.max(MIN_BUFFER_SIZE,
        Math.min(DEFAULT_BUFFER_SIZE, string.length())));
  }

  public JsonReaderTmp(ZapposJson jacinda, Reader reader) {
    this(jacinda, reader, DEFAULT_BUFFER_SIZE);
  }

  public JsonReaderTmp(ZapposJson jacinda, Reader reader, int buffersize) {
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

  private Object readArray(String path, int level) throws IOException {
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

  private Object readObject(String path, int level) throws IOException {
    read();
    Map<String, Object> object = new HashMap<>();
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
    // System.out.println(Arrays.toString(paths)+":"+level+"=>"+object);

    // System.out.println(getPath(level)+":"+level+"=>"+object);
    System.out.println(">>>>> " + object);
    Object o = createObject(level, object);
    System.out.println(o.getClass());
    System.out.println("<<<<< " + o);
    return o;
  }

  private Object createObject(int level, Map<String, Object> data) {
    String path = getPath(level);

    if ("".equals(path)) {
      com.zappos.jacinda.data.ContainingMap __o = new com.zappos.jacinda.data.ContainingMap();
      Object _simpleMap = data.get("simpleMap");
      if (_simpleMap != null) {
        // Map _simpleMap_map = (Map) _simpleMap;
        // java.util.Map simpleMap = new java.util.HashMap();
        // Iterator _simpleMap_iter = _simpleMap_map.keySet().iterator();
        // while (_simpleMap_iter.hasNext()) {
        // String _key = (String) _simpleMap_iter.next();
        // Object _m1 = _simpleMap_map.get(_key);
        // com.zappos.jacinda.data.SimpleBean _m2 =
        // (com.zappos.jacinda.data.SimpleBean) _m1;
        // simpleMap.put(_key, _m2);
        // }
        // __o.setSimpleMap(simpleMap);
      }
      ;
      return __o;
    } else if ("simpleMap".equals(path)) {
      // java.util.Map __map = new java.util.HashMap();
      // Map _data = new HashMap(data);/*copy data to new map */
      // Iterator __map_iter = _data.keySet().iterator();
      // while (__map_iter.hasNext()) {
      // String _key = (String) __map_iter.next();
      // data = (Map)__map.get(_key);
      //
      // com.zappos.jacinda.data.SimpleBean __o = new
      // com.zappos.jacinda.data.SimpleBean();
      // Object _b = data.get("b");
      // if (_b != null) {
      // Boolean b = (Boolean) _b;
      // __o.setB(b);
      // }
      //
      // __map.put(_key, __o);
      // }
      com.zappos.jacinda.data.SimpleBean __o = new com.zappos.jacinda.data.SimpleBean();
      Object _b = data.get("b");
      if (_b != null) {
        Boolean b = (Boolean) _b;
        __o.setB(b);
      }
      ;
      Object _b2 = data.get("b2");
      if (_b2 != null) {
        boolean b2 = ((Boolean) _b2).booleanValue();
        __o.setB2(b2);
      }
      ;
      Object _d = data.get("d");
      if (_d != null) {
        java.lang.Double d = new java.lang.Double((String) _d);
        __o.setD(d);
      }
      ;
      Object _d2 = data.get("d2");
      if (_d2 != null) {
        double d2 = Double.parseDouble((String) _d2);
        __o.setD2(d2);
      }
      ;
      Object _i = data.get("i");
      if (_i != null) {
        java.lang.Integer i = new java.lang.Integer((String) _i);
        __o.setI(i);
      }
      ;
      Object _i2 = data.get("i2");
      if (_i2 != null) {
        int i2 = Integer.parseInt((String) _i2);
        __o.setI2(i2);
      }
      ;
      Object _string = data.get("string");
      if (_string != null) {
        String string = (String) _string;
        __o.setString(string);
      }
      ;
      return __o;
    }
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

  private String readName() throws IOException {
    if (current != '"' && current != '\'') {
      throw expected("name");
    }
    return readStringInternal();
  }

  private Object readNull() throws IOException {
    read();
    readRequiredChar('u');
    readRequiredChar('l');
    readRequiredChar('l');
    return null;
  }

  private Object readTrue() throws IOException {
    read();
    readRequiredChar('r');
    readRequiredChar('u');
    readRequiredChar('e');
    return Boolean.TRUE;
  }

  private Object readFalse() throws IOException {
    read();
    readRequiredChar('a');
    readRequiredChar('l');
    readRequiredChar('s');
    readRequiredChar('e');
    return Boolean.FALSE;
  }

  private void readRequiredChar(char ch) throws IOException {
    if (!readChar(ch)) {
      throw expected("'" + ch + "'");
    }
  }

  private Object readString() throws IOException {
    return readStringInternal();
  }

  private String readStringInternal() throws IOException {
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

  private void readEscape() throws IOException {
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

  private Object readNumber() throws IOException {
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

  private boolean readFraction() throws IOException {
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

  private boolean readExponent() throws IOException {
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

  private boolean readChar(char ch) throws IOException {
    if (current != ch) {
      return false;
    }
    read();
    return true;
  }

  private boolean readDigit() throws IOException {
    if (!isDigit()) {
      return false;
    }
    read();
    return true;
  }

  private void skipWhiteSpace() throws IOException {
    while (isWhiteSpace()) {
      read();
    }
  }

  private void read() throws IOException {
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

  private IOException expected(String expected) {
    if (isEndOfText()) {
      return error("Unexpected end of input");
    }
    return error("Expected " + expected);
  }

  private IOException error(String message) {
    int absIndex = bufferOffset + index;
    int column = absIndex - lineOffset;
    int offset = isEndOfText() ? absIndex : absIndex - 1;
    return new IOException(message + "[offset:" + offset + ", line: " + line
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

  public static void main(String[] args) throws Exception {
    ZapposJson jacinda = ZapposJson.getInstance();
    String json = "{\"simpleMap\":{\"S1\":{\"b\":true,\"b2\":false,\"d\":1.0,\"d2\":2.0,\"i\":1,\"i2\":2,\"string\":\"simple\"},\"S2\":{\"b\":true,\"b2\":false,\"d\":1.0,\"d2\":2.0,\"i\":1,\"i2\":2,\"string\":\"simple\"}}}";
    System.out.println(json);
    JsonReaderTmp t = new JsonReaderTmp(jacinda, json);
    t.parse();
  }
}
