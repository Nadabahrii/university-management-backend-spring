package com.example.pidev1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

@Service
@AllArgsConstructor
public class ProfanityService implements IProfanityService{
    public boolean containsProfanity(String text) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://www.purgomalum.com/service/containsprofanity");
        StringEntity params = new StringEntity(text);
        request.addHeader("content-type", "text/plain");
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        String result = org.apache.http.util.EntityUtils.toString(response.getEntity());
        return Boolean.parseBoolean(result);
    }

}
