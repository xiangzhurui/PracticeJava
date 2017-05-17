package basic;

import java.math.BigInteger;

public class Sample {

	public static void main(String[] args) {
		long x = 1L;
		BigInteger bi = new BigInteger("1");
		System.out.println(bi.shiftLeft(99));
		int a = 4;
		System.out.println(one(a));
		System.out.println(two(a));
	}

	static int one(int a) {
		return ++a + a;
	}

	static int two(int a) {
		return a + a++;
	}
}
