package com.rishi.springboot.graphql.hellospringbootgraphql.service.datafetcher;

import com.rishi.springboot.graphql.hellospringbootgraphql.model.Product;
import com.rishi.springboot.graphql.hellospringbootgraphql.repository.ProductsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDataFetcher implements DataFetcher<Product> {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Product get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        return productsRepository.findById(id).get();
    }
}
