package com.xiangzhurui.core.ext.lock;

import java.util.concurrent.locks.Lock;

/**
 * 锁
 */
public interface LockService {

  Lock getLock(String lockName);
}
