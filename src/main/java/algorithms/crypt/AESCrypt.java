package algorithms.crypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES symmetric encryption algorithm
 * 
 * @author XiangZhuRui
 *
 */
public class AESCrypt {
	public static void main(String[] args) {
		String content = "123";

		String password = "abcdefghabcdefgh";

		System.out.println("加密前明文：" + content);
		System.out.println("password:" + password);

		byte[] encryptResult = encrypt(content.getBytes(), password.getBytes());
		System.out.println("getBytes 加密后：" + BinaryToHexString(encryptResult));
	}

	private static String TRANSFORMATION = "AES/ECB/PKCS5PADDING";

	private static String hexStr = "0123456789ABCDEF";

	private static final char[] map = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	public static byte[] encrypt(byte[] bytes, byte[] password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES"); // KeyGenerator提供（对称）密钥生成器的功能。使用getInstance
			// 类方法构造密钥生成器。
			kgen.init(256, new SecureRandom(password));// 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥大小。
			SecretKeySpec key = new SecretKeySpec(password, "AES");// 使用SecretKeySpec类来根据一个字节数组构造一个
			// SecretKey,，而无须通过一个（基于
			// provider
			// 的）SecretKeyFactory.
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);// 创建密码器 //为创建
			// Cipher
			// 对象，应用程序调用
			// Cipher 的
			// getInstance
			// 方法并将所请求转换
			// 的名称传递给它。还可以指定提供者的名称（可选）。

			System.out.println("encry mode:" + TRANSFORMATION);

			byte[] byteContent = bytes;

			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent); // 按单部分操作加密或解密数据，或者结束一个多部分操作。数据将被加密或解密（具体取决于此
															// Cipher 的初始化方式）。

			return result; // 加密
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

	public static String BinaryToHexString(byte[] bytes) {

		String result = "";
		String hex = "";
		for (int i = 0; i < bytes.length; i++) {
			// 字节高4位
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			// 字节低4位
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex;
		}
		return result;
	}

	public static String getHexString(byte[] input) {
		char[] result = new char[input.length * 2];
		for (int i = 0; i < input.length; i++) {
			result[i * 2] = map[(input[i] & 240) >>> 4];
			result[i * 2 + 1] = map[input[i] & 15];
		}
		return new String(result);
	}

}