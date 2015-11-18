package json.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.zappos.json.ZapposJson;

/**
 * 
 * @author Hussachai
 *
 */
public class JsonWriterTmp {

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

  private static final int CONTROL_CHARACTERS_END = 0x001f;

  private static final char[] QUOT_CHARS = { '\\', '"' };
  private static final char[] BS_CHARS = { '\\', '\\' };
  private static final char[] LF_CHARS = { '\\', 'n' };
  private static final char[] CR_CHARS = { '\\', 'r' };
  private static final char[] TAB_CHARS = { '\\', 't' };
  // In JavaScript, U+2028 and U+2029 characters count as line endings and
  // must be encoded.
  // http://stackoverflow.com/questions/2965293/javascript-parse-error-on-u2028-unicode-character
  private static final char[] UNICODE_2028_CHARS = { '\\', 'u', '2', '0', '2',
      '8' };
  private static final char[] UNICODE_2029_CHARS = { '\\', 'u', '2', '0', '2',
      '9' };
  private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6',
      '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

  protected ZapposJson jacinda;

  public JsonWriterTmp(ZapposJson jacinda) {
    this.jacinda = jacinda;
  }

  public void writeJson(String value, Appendable writer) throws IOException {
    writer.append(JsonWriterTmp.CONST_DOUBLE_QUOTE);
    for (int i = 0; i < value.length(); i++) {
      char c = value.charAt(i);
      char chars[] = JsonWriterTmp.getReplacementChars(c);
      if (chars != null) {
        for (char e : chars)
          writer.append(e);
      } else {
        writer.append(c);
      }
    }
    writer.append(JsonWriterTmp.CONST_DOUBLE_QUOTE);
  }

  public String toString(char value) {
    char v[] = null;
    if ((v = getReplacementChars(value)) != null) {
      return "\"" + new String(v) + "\"";
    }
    return "\"" + value + "\"";
  }

  public void writeJson(char values[], Appendable writer) throws IOException {
    writer.append(JsonWriterTmp.CONST_OPEN_ARRAY);
    int j = values.length - 1;
    for (int i = 0; i < j; i++) {
      writer.append(toString(values[i]));
      writer.append(JsonWriterTmp.CONST_COMMA);
    }
    if (j > -1) {
      writer.append(String.valueOf(values[j]));
    }
    writer.append(JsonWriterTmp.CONST_CLOSE_ARRAY);
  }
  
  public void writeJson (com.zappos.jacinda.data.ContainingMap __o, Appendable writer) throws Exception {
    
    }

  /**
   * This code was taken from minimal-json project.
   * 
   * @param ch
   * @return
   */
  public static char[] getReplacementChars(char ch) {
    if (ch > '\\') {
      if (ch < '\u2028' || ch > '\u2029') {
        // The lower range contains 'a' .. 'z'. Only 2 checks required.
        return null;
      }
      return ch == '\u2028' ? UNICODE_2028_CHARS : UNICODE_2029_CHARS;
    }
    if (ch == '\\') {
      return BS_CHARS;
    }
    if (ch > '"') {
      // This range contains '0' .. '9' and 'A' .. 'Z'. Need 3 checks to
      // get here.
      return null;
    }
    if (ch == '"') {
      return QUOT_CHARS;
    }
    if (ch > CONTROL_CHARACTERS_END) {
      return null;
    }
    if (ch == '\n') {
      return LF_CHARS;
    }
    if (ch == '\r') {
      return CR_CHARS;
    }
    if (ch == '\t') {
      return TAB_CHARS;
    }
    return new char[] { '\\', 'u', '0', '0', HEX_DIGITS[ch >> 4 & 0x000f],
        HEX_DIGITS[ch & 0x000f] };
  }
}