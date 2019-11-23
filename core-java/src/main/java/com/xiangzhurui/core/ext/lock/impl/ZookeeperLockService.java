package com.xiangzhurui.core.ext.lock.impl;

import java.util.concurrent.locks.Lock;

import org.apache.curator.framework.CuratorFramework;

import com.xiangzhurui.core.ext.lock.LockService;
import com.xiangzhurui.core.ext.lock.ZookeeperLock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ZookeeperLockService implements LockService {

  private final CuratorFramework curatorFramework;

  @Override
  public Lock getLock(String lockName) {
    return new ZookeeperLock(curatorFramework, lockName);
  }
}
