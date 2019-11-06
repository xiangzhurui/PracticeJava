package com.xiangzhurui.sample.guava;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

/**
 * @author xiangzhurui
 * @version 2017/12/4
 */
public class GuavaCache {


    public static LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                                                                    .maximumSize(1000)
                                                                    .refreshAfterWrite(5, TimeUnit.SECONDS)
                                                                    .build(
                                                                            new CacheLoader<String, String>() {
                                                                                @Override
                                                                                public String load(String key) { // no checked exception
                                                                                    return loadDataFromDb(key);
                                                                                }

                                                                                @Override
                                                                                public ListenableFuture<String> reload(final String key, String prevGraph) {
                                                                                    if (neverNeedsRefresh(key)) {
                                                                                        return Futures.immediateFuture(prevGraph);
                                                                                    } else {
                                                                                        // asynchronous!
                                                                                        return ListenableFutureTask.create(() -> loadDataFromDb(key));
                                                                                    }
                                                                                }
                                                                            });

    public static boolean neverNeedsRefresh(String Key) {
        //TODO
        return false;
    }

    public static String loadDataFromDb(String key) {
        //TODO
        return null;
    }
}
