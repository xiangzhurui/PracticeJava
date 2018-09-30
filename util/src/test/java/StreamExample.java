import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import com.google.common.collect.Lists;


/**
 * @author xiangzhurui
 * @version 2018/9/28 19:12
 */
public class StreamExample {

    public static void main(String[] args) {
        List<Integer> integerList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Integer result = integerList.stream()
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) {
                        return pow(integer);
                    }
                }).reduce(0, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) {
                        return plus(integer, integer2);
                    }
                });
        System.out.println(result);
        //285
    }

    public static int pow(int a) {
        return a * a;
    }

    public static int plus(int a, int b) {
        return a + b;
    }
}
