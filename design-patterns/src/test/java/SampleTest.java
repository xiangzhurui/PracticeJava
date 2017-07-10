/**
 * Created by XiangZhuRui on 2017/7/10.
 */
public class SampleTest {
    private SampleTest() {
    }

    private static SampleTest INSTANCE;

    public static SampleTest getInstance() {
        if (INSTANCE == null) {
            synchronized (SampleTest.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SampleTest();
                }
            }
        }
        return INSTANCE;
    }
}
