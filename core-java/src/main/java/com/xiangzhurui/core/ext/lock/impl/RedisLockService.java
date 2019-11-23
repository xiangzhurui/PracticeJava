package com.xiangzhurui.core.ext.lock.impl;


import com.xiangzhurui.core.ext.lock.LockService;
import com.xiangzhurui.core.ext.lock.RedisLock;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPool;

@RequiredArgsConstructor
public class RedisLockService implements LockService {

  private final JedisPool jedisPool;

  @Override
  public RedisLock getLock(String lockName) {
    return new RedisLock(jedisPool.getResource(), lockName);
  }

}
