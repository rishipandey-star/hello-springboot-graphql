package com.rishi.springboot.graphql.hellospringbootgraphql.resource;

import com.rishi.springboot.graphql.hellospringbootgraphql.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v1/hello")
@RestController
public class HelloResource {

    @Autowired
    HelloService helloService;

    @PostMapping
    public ResponseEntity<Object> getProductWithIdAndWithAllProducts(@RequestBody String productId){
        ResponseEntity<Object> response
                = helloService.getProductWithIdAndWithAllProducts(productId);
        return new ResponseEntity<Object>(response.getBody(), HttpStatus.OK);
    }
}
