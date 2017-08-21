package me.xzr.practice.example.es.service;

import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

@Service
public class EsServiceSample {
    private RestClient restClient;

    public <T> boolean save(T t){

        return true;
    }
}
