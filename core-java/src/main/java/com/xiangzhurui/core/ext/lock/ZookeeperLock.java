package com.xiangzhurui.core.ext.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于 Zookeeper 的分布式锁
 */
@Slf4j
public class ZookeeperLock implements Lock {

  private final CuratorFramework curatorFramework;

  private final InterProcessLock lock;
  /**
   * 锁名称
   */
  private final String lockName;

  /**
   * 锁 ID
   */
  private final String requestId;

  public ZookeeperLock(CuratorFramework curatorFramework, String lockName) {
    this.curatorFramework = curatorFramework;
    this.lockName = lockName;
    this.requestId = UUID.randomUUID().toString();
    this.lock = new InterProcessMutex(this.curatorFramework, "/lock/" + lockName);
  }

  @Override
  public void lock() {
    try {
      lock.acquire();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    throw new IllegalStateException("not support method lockInterruptibly()");
  }

  @Override
  public boolean tryLock() {
    try {
      lock.acquire();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    boolean acquire = false;
    try {
      acquire = lock.acquire(time, unit);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return acquire;
  }

  @Override
  public void unlock() {
    try {
      lock.release();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Condition newCondition() {
    throw new IllegalStateException("not support method newCondition()");
  }
}
