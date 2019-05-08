package com.xiangzhurui.sample.java.lang;

import java.util.HashSet;
import java.util.Set;

public class ArraySample {

	public static void main(String[] args) {
		int[] a = { 1, 2, 2, 1 }, b = { 2, 2 };
		int[] c = intersection(a, b);
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
		}

	}

	/**
	 * @param nums1 an integer array
	 * @param nums2 an integer array
	 * @return an integer array
	 */
	public static int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		for (int i : nums1) {
			a.add(i);
		}
		for (int i : nums2) {
			b.add(i);
		}
		a.retainAll(b);
		int[] r = new int[a.size()];
		Object[] x = a.toArray();
		for (int c = 0; c < r.length; c++) {
			r[c] = (int) x[c];
		}
		return r;
	}
}
