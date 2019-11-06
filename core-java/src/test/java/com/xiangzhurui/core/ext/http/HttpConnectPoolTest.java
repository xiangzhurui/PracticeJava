package com.xiangzhurui.core.ext.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author xiangzhurui
 * @version 2018/9/4 14:11
 */
@Slf4j
public class HttpConnectPoolTest {

    @Test
    public void getInstance() {
    }

    @Test
    public void httpClient() throws IOException {
        HttpClient httpClient = HttpConnectPool.getInstance().httpClient();
        HttpResponse httpResponse = httpClient.execute(new HttpGet("http://www.baidu.com"));
        log.info("getProtocolVersion={}", httpResponse.getStatusLine().getProtocolVersion().toString());
        log.info("getProtocolVersion={}", httpResponse.getStatusLine().getReasonPhrase());
        log.info("getProtocolVersion={}", httpResponse.getStatusLine().getStatusCode());
        System.out.println(httpResponse);
        StatusLine statusLine = httpResponse.getStatusLine();
        System.out.println(statusLine);
        HttpEntity entity = httpResponse.getEntity();

        System.out.println(entity);

        System.out.println(entity.getContentEncoding());
        System.out.println(entity.getContentLength());
        Header header = entity.getContentType();
        System.out.println(header);
        for (HeaderElement s : header.getElements()) {
            log.info("=={}", s);

        }

        System.out.println("响应的HTML：");
        InputStream inputStream = entity.getContent();
        StringWriter stringWriter = new StringWriter();
        IOUtils.copy(inputStream, stringWriter, "UTF-8");
        String resContent = stringWriter.toString();
        System.out.println(resContent);

        System.out.println("格式化的html===========");
        Document doc = Jsoup.parseBodyFragment(resContent);
        String html = doc.body().html();
        System.out.println(html);
    }
}