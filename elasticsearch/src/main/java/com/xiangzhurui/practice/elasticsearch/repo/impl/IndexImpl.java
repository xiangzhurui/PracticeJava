package com.xiangzhurui.practice.elasticsearch.repo.impl;

import com.google.gson.JsonObject;
import com.xiangzhurui.practice.elasticsearch.constant.HttpMethod;
import com.xiangzhurui.practice.elasticsearch.repo.IndexDao;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xiangzhurui
 * @version 2017/9/6
 */
@Repository
public class IndexImpl implements IndexDao {

    @Autowired
    private RestClient client;

    @Override
    public JsonObject getSource() {
        return null;
    }

    @Override
    public void deleteIndex(String index) {
    }

    @Override
    public void deleteType(String index, String type) {

    }

    @Override
    public void deleteDocument(String index, String type, String docId) {
        String method = HttpMethod.DELETE;

    }

}
