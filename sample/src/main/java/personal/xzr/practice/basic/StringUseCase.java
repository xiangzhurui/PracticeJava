package personal.xzr.practice.basic;

public class StringUseCase {

	/**
	 * 首字母转为大写
	 * 
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);

	}
}
