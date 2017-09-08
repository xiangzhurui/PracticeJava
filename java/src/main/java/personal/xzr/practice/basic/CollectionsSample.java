package personal.xzr.practice.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author xiangzhurui
 * @version 2017/9/7
 */
@Slf4j
public class CollectionsSample {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<Integer>() {
            {

                add(2);
                add(1);
                add(3);
            }
        };
        log.info("sssss:: {}",integerList);

        Collections.sort(integerList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        log.info("sssss:: {}",integerList);


    }
}
