package personal.xzr.practice.basic;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64UseCase {

    public static void main(String[] args) throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "字符串文字fsdafa";
        final byte[] textByte = text.getBytes("UTF-8");
        final String encodedText = encoder.encodeToString(textByte);
        // 编码
        System.out.println(encodedText);
        // 解码
        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));

    }

}
