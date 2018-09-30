import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author xiangzhurui
 * @version 2018/9/28 18:46
 */
public class GuavaExample {

    public static void main(String[] args) {
        List<Integer> integerList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //map

        List<Integer> mapedList = Lists.transform(integerList, new Function<Integer, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable Integer input) {
                return pow(input);
            }
        });
        System.out.println(mapedList);
        //[1, 4, 9, 16, 25, 36, 49, 64, 81]
        // Reduce
        Integer ages = Reduce.reduce(mapedList, new Func<Integer, Integer>() {
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        }, 0);
        System.out.println(ages);
    }

    public static Integer pow(int a) {
        return a * a;
    }

    public static class Reduce {
        private Reduce() {

        }
        public static <F, T> T reduce(final Iterable<F> iterable, final Func<F, T> func, T origin) {

            for (Iterator iterator = iterable.iterator(); iterator.hasNext(); ) {
                origin = func.apply((F) (iterator.next()), origin);
            }

            return origin;
        }
    }

    public interface Func<F, T> {
        T apply(F currentElement, T origin);

    }
}
