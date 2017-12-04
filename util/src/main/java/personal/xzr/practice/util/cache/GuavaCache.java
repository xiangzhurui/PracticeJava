package personal.xzr.practice.util.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.MoreObjects;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.ToString;

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
                        public String load(String key) { // no checked exception
                            return getGraphFromDatabase(key);
                        }

                        public ListenableFuture<String> reload(final String key, String prevGraph) {
                            if (neverNeedsRefresh(key)) {
                                return Futures.immediateFuture(prevGraph);
                            } else {
                                // asynchronous!
                                return ListenableFutureTask.create(new Callable<String>() {
                                    public String call() {
                                        return getGraphFromDatabase(key);
                                    }
                                });
                            }
                        }
                    });

    public static boolean neverNeedsRefresh(String Key) {
        return false;
    }

    public static String getGraphFromDatabase(String key) {
        return null;
    }
}
