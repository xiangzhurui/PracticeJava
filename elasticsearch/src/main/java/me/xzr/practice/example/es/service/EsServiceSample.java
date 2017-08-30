package me.xzr.practice.example.es.service;

import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EsServiceSample {
    @Autowired()
    @Qualifier("esRestClient")
    private RestClient restClient;

    public <T> boolean save(T t){

        return true;
    }
}
