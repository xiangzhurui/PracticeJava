package com.xiangzhurui.core.java.thread;

/**
 * @author xiangzhurui
 * @version 2018/2/8
 */
public class ThreadExecutorHelpler {
    /**
     * 最小线程数据
     */
    public final static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 1;
    /**
     * CPU 核心线程数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 根据类型获取最大线程数, 参考这个文章
     * <p>
     * <p>http://www.blogjava.net/bolo/archive/2015/01/20/422296.html</>
     */
    public final static int maxPoolSize(CALCULATE_TYPE calculateType) {
        if (calculateType == null) {
            return CPU_COUNT * 2 + 1;
        }
        int maxPoolSize;
        switch (calculateType) {
            case IO: {
                maxPoolSize = CORE_POOL_SIZE * 2 < 64 ? 64 : CORE_POOL_SIZE * 2;
                break;
            }
            case CALCULAT:
            default: {
                maxPoolSize = CORE_POOL_SIZE * 2 + 1;
                break;
            }
        }
        return maxPoolSize;

    }


    // 计算类型
    public enum CALCULATE_TYPE {
        /**
         * 计算密集型
         */
        CALCULAT,
        /**
         * IO 密集型
         */
        IO

    }


}
