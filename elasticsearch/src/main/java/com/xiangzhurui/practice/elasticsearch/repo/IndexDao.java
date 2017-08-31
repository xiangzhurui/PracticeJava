package com.xiangzhurui.practice.elasticsearch.repo;


import com.google.gson.JsonObject;

public interface IndexDao extends CRUD {

    public JsonObject getSource();

}
