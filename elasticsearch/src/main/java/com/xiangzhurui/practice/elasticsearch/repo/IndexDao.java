package com.xiangzhurui.practice.elasticsearch.repo;


import com.google.gson.JsonObject;

public interface IndexDao {

    JsonObject getSource();

    void deleteIndex(String index);

    void deleteType(String index, String type);

    void deleteDocument(String index, String type, String docId);
}
