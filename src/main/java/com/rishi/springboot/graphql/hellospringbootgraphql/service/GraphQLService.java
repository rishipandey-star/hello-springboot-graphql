package com.rishi.springboot.graphql.hellospringbootgraphql.service;

import com.rishi.springboot.graphql.hellospringbootgraphql.model.Product;
import com.rishi.springboot.graphql.hellospringbootgraphql.repository.ProductsRepository;
import com.rishi.springboot.graphql.hellospringbootgraphql.service.datafetcher.AllProductsDataFetcher;
import com.rishi.springboot.graphql.hellospringbootgraphql.service.datafetcher.ProductDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;


@Service
public class GraphQLService {

    @Value("classpath:products.graphql")
    Resource resource;
    private GraphQL graphQL;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private AllProductsDataFetcher allProductsDataFetcher;
    @Autowired
    private ProductDataFetcher productDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        //Load Data into the repository
        loadData();
        // Get the Schema
        File schemaFile = resource.getFile();
        // Parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();

    }

    private void loadData(){
        Stream.of(
                new Product("1","Dell Laptop","14 inc Laptop"),
                new Product("2","Headphones","Wireless Headphones"),
                new Product("3","Keyboard","Wireless Keyboard")
        ).forEach(product ->productsRepository.save(product));
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allProducts", allProductsDataFetcher)
                        .dataFetcher("product", productDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

}
