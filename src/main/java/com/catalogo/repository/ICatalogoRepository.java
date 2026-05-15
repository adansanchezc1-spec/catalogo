package com.catalogo.repository;

import com.catalogo.model.ProductoCatalogo;

import java.util.List;

public interface ICatalogoRepository {

    void save(ProductoCatalogo producto);

    List<ProductoCatalogo> findAll();

    void update(ProductoCatalogo producto);

    void delete(String id);
}
