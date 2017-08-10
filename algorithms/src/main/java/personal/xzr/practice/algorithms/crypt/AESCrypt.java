package personal.xzr.practice.algorithms.crypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AES 对称加密
 * 
 * <pre>
 * String keyStr = "0123456789abcdef";
 * String content = "hello world";
 * byte[] temp0 = AESCrypt.enCrypt(content, keyStr);
 * String secretStr = toHex(temp0);
 * System.out.println("加密后得到的密文：" + secretStr);
 * byte[] encodeStr = AESCrypt.deCrypt(secretStr, keyStr);
 * System.out.println("解密后得到的原文：" + new String(encodeStr));
 * </pre>
 * 
 * @see {@link https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html}
 * @author XiangZhuRui
 */
public class AESCrypt {
	private static final Logger log = LoggerFactory.getLogger(AESCrypt.class);
	private static final String TRANSFORMATION = "AES/ECB/PKCS5PADDING";
	private static final String HEXSTR = "0123456789ABCDEF";

	/**
	 * 加密
	 * 
	 * @param bytes
	 * @param privateKey 长度为16
	 * @return
	 */
	public static byte[] enCrypt(byte[] bytes, byte[] privateKey) {
		byte[] result = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(privateKey));
			SecretKeySpec secretKey = new SecretKeySpec(privateKey, "AES");
			// SecretKey secretKey = getKey(privateKey);

			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			log.info("encry mode:" + TRANSFORMATION);
			// cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			result = cipher.doFinal(bytes);
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
		} catch (NoSuchPaddingException e) {
			log.error("", e);
		} catch (InvalidKeyException e) {
			log.error("", e);
		} catch (IllegalBlockSizeException e) {
			log.error("", e);
		} catch (BadPaddingException e) {
			log.error("", e);
		}
		return result;
	}

	/**
	 * 加密
	 * 
	 * @param bytes
	 * @param privateKey 16个字符
	 * @return
	 */
	public static byte[] enCrypt(String bytes, String privateKey) {
		return enCrypt(bytes.getBytes(), privateKey.getBytes());
	}

	/**
	 * 解密
	 * 
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static byte[] deCrypt(byte[] content, byte[] privateKey) {
		log.debug("参数为[{}][{}]", content, privateKey);
		byte[] result = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(privateKey));
			SecretKeySpec secretKey = new SecretKeySpec(privateKey, "AES");
			log.debug("privateKey数组长{}", privateKey.length);
			// SecretKey secretKey = getKey(privateKey);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			log.info("encry mode:" + TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			result = cipher.doFinal(content);
		} catch (NoSuchAlgorithmException e) {
			log.error("", e);
		} catch (NoSuchPaddingException e) {
			log.error("", e);
		} catch (IllegalBlockSizeException e) {
			log.error("", e);
		} catch (BadPaddingException e) {
			log.error("", e);
		} catch (InvalidKeyException e) {
			log.error("", e);
		}
		return result;
	}

	/**
	 * 解密
	 * 
	 * @param content 十六进制的密文字符串
	 * @param privateKey 长度为16的秘钥字符串
	 * @return
	 */
	public static byte[] deCrypt(String content, String privateKey) {
		return deCrypt(toByteArray(content), privateKey.getBytes());
	}

	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			System.out.println("blockSize:" + cipher.getBlockSize());
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * byte数组转十六进制字符串
	 * 
	 * @param bytes[]
	 * @return
	 */
	public static String toHex(byte[] bytes) {
		String result = "";
		String hex = "";
		for (int i = 0; i < bytes.length; i++) {
			// 字节高4位
			hex = String.valueOf(HEXSTR.charAt((bytes[i] & 0xF0) >> 4));
			// 字节低4位
			hex += String.valueOf(HEXSTR.charAt(bytes[i] & 0x0F));
			result += hex;
		}
		return result;
	}

	/**
	 * 十六进制字符串转byte数组
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] toByteArray(String hexStr) {
		/* 对输入值进行规范化整理 */
		hexStr = hexStr.trim().replace(" ", "").toUpperCase(Locale.US);
		// 处理值初始化
		int m = 0, n = 0;
		int iLen = hexStr.length() / 2;
		byte[] ret = new byte[iLen];

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + hexStr.substring(i * 2, m) + hexStr.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	/**
	 * byte数组转回普通的char[]的String
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toString(byte[] bytes) {
		return new String(bytes);
	}
}