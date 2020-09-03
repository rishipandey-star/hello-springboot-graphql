package com.rishi.springboot.graphql.hellospringbootgraphql.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@Service
public class HelloService {

    private RestTemplate restTemplate;

    @Value("${graphql.product.url}")
    private String graphqlProductUrl;

    public HelloService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<Object> getProductWithIdAndWithAllProducts (String productId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(prepareRequestBodyWithAllProducts(productId),headers);
        return restTemplate.exchange(graphqlProductUrl, HttpMethod.POST, entity, Object.class);
    }

    private String prepareRequestBodyWithAllProducts(String productId){
        return "{product(id: \""+
                productId+"\")" +
                "{" +
                   "name " +
                   "description" +
                "} " +
                "allProducts{" +
                 "name" +
                "}" +
                "}";

    }
}
