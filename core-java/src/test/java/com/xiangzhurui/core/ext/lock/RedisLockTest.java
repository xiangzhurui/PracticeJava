package com.xiangzhurui.core.ext.lock;


import org.junit.Before;
import org.junit.Test;


import com.xiangzhurui.core.util.RedisUtils;

public class RedisLockTest {

  private RedisLock redisLock;

  @Before
  public void init(){
    redisLock = new RedisLock(RedisUtils.getInstance().getJedis(),"test1");
    System.out.println(redisLock);
  }

  @Test
  public void lock() {
    boolean b = redisLock.tryLock();
    System.out.println(b);
    redisLock.lock();
    redisLock.unlock();
  }

  @Test
  public void lockInterruptibly() {
  }

  @Test
  public void tryLock() {
    boolean b = redisLock.tryLock();
    System.out.println(b);
    redisLock.unlock();
  }


}