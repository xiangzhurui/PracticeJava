package com.xiangzhurui.core.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtils {

  private String host = "127.0.0.1";

  private int port = 6379;

  public Jedis getJedis() {
    return jedisPool().getResource();
  }

  private JedisPool jedisPool() {
    JedisPool jedisPool = new JedisPool(host, port);
    return jedisPool;
  }

  private static class RedisUtilsHolder {

    private static final RedisUtils instance = new RedisUtils();
  }

  public static RedisUtils getInstance() {
    return RedisUtilsHolder.instance;
  }

  public Long lpush(String key, String obj) {
    Long lpush = getJedis().lpush(key, obj);
    return lpush;
  }

  public String rpop(String key){
    return getJedis().rpop(key);
  }
}
