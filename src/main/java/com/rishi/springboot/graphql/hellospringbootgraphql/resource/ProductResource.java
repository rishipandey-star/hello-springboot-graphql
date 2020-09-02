package com.rishi.springboot.graphql.hellospringbootgraphql.resource;

import com.rishi.springboot.graphql.hellospringbootgraphql.service.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v1/products")
@RestController
public class ProductResource {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllProducts(@RequestBody String query){
        ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<Object>(executionResult, HttpStatus.OK);
    }
}
