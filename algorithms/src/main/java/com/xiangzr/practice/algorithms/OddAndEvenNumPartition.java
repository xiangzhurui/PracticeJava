package com.xiangzr.practice.algorithms;

import java.util.Arrays;

/**
 * 输入一个整数数组，
 * 实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class OddAndEvenNumPartition {

  public static void reOrderArray(int[] array) {
    if (array == null) {
      return;
    }

    for (int i = 0, size = array.length; i < size; i++) {
      int tmp = array[i];
      if (tmp % 2 == 1) {
        //当前 index 的元素是奇数，则把当前元素插入到偶数前面
        int j = i - 1;

        while (j >= 0 && array[j] % 2 == 0) {
          //array[j] 是偶数
          array[j+1]=array[j];
          j--;
        }

        array[j + 1] = tmp;
      }
    }

  }

  public static void main(String[] args) {
    int x = 97 % 2;
    System.out.println(x);
    int[] arr = new int[]{8, 2, 12, 14, 3, 4, 5, 6, 7, 8};
    reOrderArray(arr);
    System.out.println(arr);
    System.out.println(Arrays.toString(arr));
  }
}
