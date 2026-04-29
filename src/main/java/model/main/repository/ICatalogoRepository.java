package model.main.repository;

import java.util.List;

import model.main.menu.ProductoCatalogo;

public interface ICatalogoRepository {
    void saveData(ProductoCatalogo data);

    List<ProductoCatalogo> findAll();

    List<ProductoCatalogo> loadData();

    void updateData(ProductoCatalogo data);

    void deleteData(String id);

    ProductoCatalogo findDataById(String id);
}
