package algorithms;

/**
 * 实现一个函数， 输入一个整数，输出该数二进制表示中1的个数。
 * 例如把9表示成二进制是1001 ，有2位是1. 因此如果输入9，该出2。
 *
 * @author XiangZhuRui
 */
public class BinaryNumCount {
    public static int count0(byte v) {
        int num = 0;
        while (v != 0) {
            if (v % 2 == 1) {
                num++;
            }
            v /= 2;
        }
        return num;
    }

    /**
     * @param n 待的数字
     * @return 数字中二进制表表的1的数目
     */
    public static int numberOfOne(int n) {
        // 记录数字中1的位数
        int result = 0;
        // JAVA语言规范中，int整形占四个字节，总计32位
        // 对每一个位置与1进行求与操作，再累加就可以求出当前数字的表示是多少位1
        for (int i = 0; i < 32; i++) {
            result += (n & 1);
            n >>>= 1;
        }
        // 返回求得的结果
        return result;
    }
}
