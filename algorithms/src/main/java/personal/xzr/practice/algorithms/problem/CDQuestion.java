package personal.xzr.practice.algorithms.problem;

public class CDQuestion {

    public static void main(String[] args) {

    }

    /**
     * 欧几里得算法，求解最大公约数
     * 
     * @param p
     * @param q
     * @return
     */
    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        } else {
            int r = p % q;
            return gcd(q, r);
        }

    }
}
