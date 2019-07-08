package com.hisen.string;

public class StringSpecharsHandle {

  public static void main(String[] args) {
    String s = "\\大/家\\好\b我\f是\n李\r刚\t";
    System.out.println(string2Json(s));
  }

  /**
   * 特殊字符处理，比如：“\A1;1300”
   *
   * @return string
   */
  public static String string2Json(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case '\"':
          sb.append("\\\"");
          break;
        case '\\':
          sb.append("\\\\");
          break;
        case '/':
          sb.append("\\/");
          break;
        case '\b':
          sb.append("\\b");
          break;
        case '\f':
          sb.append("\\f");
          break;
        case '\n':
          sb.append("\\n");
          break;
        case '\r':
          sb.append("\\r");
          break;
        case '\t':
          sb.append("\\t");
          break;
        default:
          sb.append(c);
      }
    }
    return sb.toString();
  }
}