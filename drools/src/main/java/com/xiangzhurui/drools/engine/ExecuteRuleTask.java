package com.xiangzhurui.drools.engine;

import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RecursiveTask;

/**
 * @author xiangzhurui
 * @version 2018/3/28
 */
public class ExecuteRuleTask extends RecursiveTask<Map<String, Integer>> {

    private Queue<String> taskQueue;

    public ExecuteRuleTask(Queue<String> taskQueue) {
        this.taskQueue = new LinkedBlockingDeque<>(taskQueue);
    }

    @Override
    protected Map<String, Integer> compute() {
        final int size = taskQueue.size();
        if (size > 1) {
            ExecuteRuleTask left = new ExecuteRuleTask(new LinkedList<String>() {{
                push(taskQueue.poll());
            }});
            ExecuteRuleTask right = new ExecuteRuleTask(taskQueue);

            left.fork();
            right.fork();

            Map map1 = right.join();
            Map map2 = left.join();
            map1.putAll(map2);
            return map1;
        } else if (size == 1) {
            return act(taskQueue.poll());
        }
        return Maps.newHashMap();
    }

    private Map<String, Integer> act(String s) {
        return Maps.newHashMap();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Future<Map<String, Integer>> task = forkJoinPool.submit(new ExecuteRuleTask(new LinkedList<>()));
        Map<String, Integer> result = task.get();
    }
}
