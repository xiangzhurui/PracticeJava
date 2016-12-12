package algorithms.crypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import basic.ByteArrayToHexString;

/**
 * AES symmetric encryption algorithm
 * 
 * @author XiangZhuRui
 *
 */
public class AESCrypt {
    public static void main(String[] args) throws Exception {
        String content = "哈哈哈";
        String encryptKey = "sfdasSFDS123";
        byte[] data = aesEncryptToBytes(content, encryptKey);
        System.out.println(ByteArrayToHexString.bytesToHexString(data));
//        3394dad0600e228ff09c904af804f594
        System.out.println(aesDecryptByBytes(ByteArrayToHexString.hexStringToBytes("3394dad0600e228ff09c904af804f594"), encryptKey));
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }
}