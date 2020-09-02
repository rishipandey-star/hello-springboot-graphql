package com.rishi.springboot.graphql.hellospringbootgraphql.repository;

import com.rishi.springboot.graphql.hellospringbootgraphql.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,String> {
}
