package com.xiangzhurui.core.ext.lock;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * 基于 Redis 的分布式锁
 *
 * @author
 */
@Slf4j
public class RedisLock implements Lock {

  private static final String LOCK_PREFIX = "lock:";

  private static final String LOCK_SUCCESS = "OK";

  private static final Long RELEASE_SUCCESS = 1L;

  private static final long DEFAULT_EXPIRE = TimeUnit.SECONDS.toMillis(30);

  private final Jedis jedis;

  private final String lockKey;

  private final String requestId;

  private long lockThreadId = -1L;

  private boolean interrupt;

  public RedisLock(Jedis jedis, String lockName) {
    this.jedis = jedis;
    this.lockKey = LOCK_PREFIX + lockName;
    this.requestId = UUID.randomUUID().toString();
    this.interrupt = false;
  }


  @Override
  public void lock() {
    AtomicInteger count = new AtomicInteger(0);
    while (!tryLock()) {
      int i = count.incrementAndGet();
      if (i>20){
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    log.debug("获取到锁或者中断时的计数：[{}]", count.longValue());
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    //TODO
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean tryLock() {
    SetParams ex = SetParams.setParams().nx().px(DEFAULT_EXPIRE);
    String result = jedis.set(lockKey, requestId, ex);

    if (LOCK_SUCCESS.equals(result)) {
      log.debug("成功获取锁 lockKey:[{}],requestId:[{}]", lockKey, requestId);
      return true;
    }
    return false;
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    long secondsToExpire;
    if (unit == null) {
      secondsToExpire = DEFAULT_EXPIRE;
    } else {
      secondsToExpire = unit.toMillis(time);
    }
    SetParams ex = SetParams.setParams().nx().px(secondsToExpire);
    String result = jedis.set(lockKey, requestId, ex);

    if (LOCK_SUCCESS.equals(result)) {
      log.debug("成功获取锁 lockKey:[{}],requestId:[{}]", lockKey, requestId);
      return true;
    }
    return false;
  }

  @Override
  public void unlock() {

    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

    if (Objects.equals(RELEASE_SUCCESS, result)) {
      log.debug("成功解锁 lockKey:[{}],requestId:[{}]", lockKey, requestId);
    }
  }

  @Override
  public Condition newCondition() {
    throw new UnsupportedOperationException("not support method newCondition()");

  }
}
