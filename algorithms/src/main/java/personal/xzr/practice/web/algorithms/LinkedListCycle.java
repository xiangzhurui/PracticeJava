package personal.xzr.practice.web.algorithms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断单向链表是否有闭环
 *
 * @author XiangZhuRui
 */
public class LinkedListCycle<T> {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Node<T> {
        private T value;
        private Node next;
    }

    /**
     * 使用Set辅助判断
     *
     * @param node 第一个节点
     * @return 有闭环返回true，否则范围false
     */
    public boolean hasCycle0(Node<T> node) {
        if (node == null || node.getNext() == null) {
            return false;
        }
        T value = node.getValue();
        Node next = node.getNext();
        Set<T> values = new HashSet<T>();
        values.add(value);
        while ((node = node.getNext()) != null) {
            value = node.getValue();
            if (values.contains(value)) {
                return true;
            }
            values.add(value);
        }
        return false;
    }

    /**
     * 使用两个指针，一个以速度1前进，另一个以速度2前进，如果存在闭环，则会指向同一个节点。
     *
     * @param node 第一个节点
     * @return 有闭环返回true，否则返回false
     */
    public boolean hasCycle1(Node<T> node) {
        if (node == null || node.next == null) {
            return false;
        }
        Node<T> first = node;
        Node<T> secend = node.getNext();

        while (secend != null && secend.getNext() != null && secend.getNext().getNext() != null) {
            first = first.getNext();
            secend = secend.getNext().getNext();
            if (first.getValue() == secend.getValue()) {
                return true;
            }
        }
        return false;
    }

}
