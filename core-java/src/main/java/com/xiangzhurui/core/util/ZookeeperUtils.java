package com.xiangzhurui.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.retry.RetryNTimes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZookeeperUtils {

  private static String connectString = "127.0.0.1:2181";

  public static CuratorFramework curatorFramework() {

    CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
        .connectString(connectString)
        .retryPolicy(new RetryNTimes(3, 1000))
        .build();
    return curatorFramework;
  }

  public static void main(String[] args) throws Exception {
    CuratorFramework curatorFramework = curatorFramework();
    curatorFramework.start();
    GetChildrenBuilder children = curatorFramework.getChildren();
    List<String> strings = children.forPath("/");
    int size = strings
        .size();
    System.out.println(size);
    strings.forEach(s -> System.out.println(s));
  }

  public int[] twoSum(int[] nums, int target) {
    int firstIndex = 0, secondIndex;
    for (int size = nums.length; firstIndex < size; firstIndex++) {
      int a = nums[firstIndex];
      for (secondIndex = firstIndex + 1; secondIndex < size; secondIndex++) {
        if (nums[secondIndex] + a == target) {
          return new int[]{
            firstIndex, secondIndex
          };
        }
      }
    }
    return null;
  }

  public int[] twoSum1(int[] nums, int target) {
    int[] ret = new int[2];
    Map<Integer, Integer> h = new HashMap<>(nums.length);
    for (int i = 0; i < nums.length; i++) {
      if (h.containsKey(nums[i])) {
        ret[0] = i;
        ret[1] = h.get(nums[i]);
        break;
      }
      h.put(target - nums[i], i);
    }
    return ret;
  }
}
