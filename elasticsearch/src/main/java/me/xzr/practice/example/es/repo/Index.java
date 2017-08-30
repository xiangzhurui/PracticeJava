package me.xzr.practice.example.es.repo;

import org.apache.http.Header;

public interface Index {

    String createIndex(String indexName, Header ...headers);

    String createType(String indexName, String typeName);
}
