package me.xzr.practice.example.es.repo;

import org.apache.http.Header;
import sun.jvm.hotspot.memory.HeapBlock;

public interface Index {

    String createIndex(String indexName, Header ...headers);

    String createType(String indexName, String typeName);
}
