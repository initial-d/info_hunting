package duqu;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>������URLEncoderHZ
 * <p>���ã������滻�ַ�������ĺ��ֲ��֡�
 * <p>@author :born to try
 */
public class URLEncoderHZ {
  private static String zhPattern = "[\u4e00-\u9fa5]+";

  /**
   * �滻�ַ�����
   * 
   * @param str ���滻���ַ���
   * @param charset �ַ���
   * @return �滻�õ�
   * @throws UnsupportedEncodingException ��֧�ֵ��ַ���
   */
  public static String encode(String str, String charset) throws UnsupportedEncodingException {
    Pattern p = Pattern.compile(zhPattern);
    Matcher m = p.matcher(str);
    StringBuffer b = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
    }
    m.appendTail(b);
    return b.toString();
    
  }
}