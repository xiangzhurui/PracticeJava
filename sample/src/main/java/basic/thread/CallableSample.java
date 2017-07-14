package basic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by XiangZhuRui on 2017/7/13.
 */
@Slf4j
public class CallableSample {
    public static void main(String[] args) {


        FutureTask futureTask = new FutureTask(() -> {
            AtomicReference<LongAdder> sum = new AtomicReference<>(new LongAdder());
            for (int i = 0; i < 100; i++) {
                sum.get().add(i);

            }
            TimeUnit.SECONDS.sleep(1);
            return sum.get().sum();
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(futureTask);
        log.info("开始执行");

        try {
            log.info("执行结果{}", futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("任务执行完成");
        executorService.shutdown();
        log.info("线程池关闭");
    }
}
