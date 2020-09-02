package com.rishi.springboot.graphql.hellospringbootgraphql.service.datafetcher;

import com.rishi.springboot.graphql.hellospringbootgraphql.model.Product;
import com.rishi.springboot.graphql.hellospringbootgraphql.repository.ProductsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllProductsDataFetcher implements DataFetcher<List<Product>> {

    @Autowired
    ProductsRepository productsRepository;
    @Override
    public List<Product> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return productsRepository.findAll();
    }
}
