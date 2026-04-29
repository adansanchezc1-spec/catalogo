/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.main.menu.ProductoCatalogo;

/**
 *
 * @author ADAN
 */
public class CatalogoRepositoryJSON implements ICatalogoRepository {
    private static final String FILE_NAME = "catalogo.json";
    private final Path filePath;

    public CatalogoRepositoryJSON() {
        this.filePath = Paths.get(FILE_NAME);
        ensureFileExists();
    }

    @Override
    public void saveData(ProductoCatalogo data) {
        Objects.requireNonNull(data, "El producto no puede ser nulo");
        List<ProductoCatalogo> products = readAllProducts();
        if (findById(data.getId(), products) != null) {
            throw new IllegalArgumentException("Ya existe un producto con el ID: " + data.getId());
        }
        products.add(data);
        writeAllProducts(products);
    }

    @Override
    public List<ProductoCatalogo> findAll() {
        return readAllProducts();
    }

    @Override
    public List<ProductoCatalogo> loadData() {
        return findAll();
    }

    @Override
    public void updateData(ProductoCatalogo data) {
        Objects.requireNonNull(data, "El producto no puede ser nulo");
        List<ProductoCatalogo> products = readAllProducts();
        int index = indexOfId(data.getId(), products);
        if (index == -1) {
            throw new IllegalArgumentException("No existe un producto con el ID: " + data.getId());
        }
        products.set(index, data);
        writeAllProducts(products);
    }

    @Override
    public void deleteData(String id) {
        Objects.requireNonNull(id, "El ID no puede ser nulo");
        List<ProductoCatalogo> products = readAllProducts();
        boolean removed = products.removeIf(product -> id.equals(product.getId()));
        if (removed) {
            writeAllProducts(products);
        }
    }

    @Override
    public ProductoCatalogo findDataById(String id) {
        Objects.requireNonNull(id, "El ID no puede ser nulo");
        return findById(id, readAllProducts());
    }

    private void ensureFileExists() {
        try {
            if (Files.notExists(filePath)) {
                Files.writeString(filePath, "[]", StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo crear el archivo JSON: " + filePath, ex);
        }
    }

    private List<ProductoCatalogo> readAllProducts() {
        try {
            String content = Files.readString(filePath, StandardCharsets.UTF_8).trim();
            if (content.isEmpty() || "[]".equals(content)) {
                return new ArrayList<>();
            }
            if (!content.startsWith("[") || !content.endsWith("]")) {
                throw new IllegalStateException("Formato JSON inválido en " + filePath);
            }
            String arrayContent = content.substring(1, content.length() - 1).trim();
            if (arrayContent.isEmpty()) {
                return new ArrayList<>();
            }
            List<ProductoCatalogo> products = new ArrayList<>();
            List<String> objectStrings = splitJsonObjects(arrayContent);
            for (String objectString : objectStrings) {
                String id = readJsonString(objectString, "id");
                String nombre = readJsonString(objectString, "nombre");
                String descripcion = readJsonString(objectString, "descripcion");
                String precioText = readJsonValue(objectString, "precio");
                double precio = precioText == null ? 0.0 : Double.parseDouble(precioText);
                products.add(new ProductoCatalogo(id, nombre, descripcion, precio));
            }
            return products;
        } catch (IOException ex) {
            throw new RuntimeException("Error leyendo el archivo JSON: " + filePath, ex);
        }
    }

    private void writeAllProducts(List<ProductoCatalogo> products) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < products.size(); i++) {
            ProductoCatalogo product = products.get(i);
            json.append("{")
                .append("\"id\":\"").append(escape(product.getId())).append("\",")
                .append("\"nombre\":\"").append(escape(product.getNombre())).append("\",")
                .append("\"descripcion\":\"").append(escape(product.getDescripcion())).append("\",")
                .append("\"precio\":").append(product.getPrecio())
                .append("}");
            if (i < products.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        try {
            Files.writeString(filePath, json.toString(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException("Error escribiendo el archivo JSON: " + filePath, ex);
        }
    }

    private static String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static List<String> splitJsonObjects(String json) {
        List<String> items = new ArrayList<>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < json.length(); i++) {
            char current = json.charAt(i);
            if (current == '{') {
                if (depth == 0) {
                    start = i;
                }
                depth++;
            } else if (current == '}') {
                depth--;
                if (depth == 0) {
                    items.add(json.substring(start, i + 1));
                }
            }
        }
        return items;
    }

    private static String readJsonString(String json, String key) {
        String raw = readJsonValue(json, key);
        if (raw == null) {
            return null;
        }
        if (raw.startsWith("\"") && raw.endsWith("\"")) {
            return raw.substring(1, raw.length() - 1).replace("\\\"", "\"").replace("\\\\", "\\");
        }
        return raw;
    }

    private static String readJsonValue(String json, String key) {
        String token = "\"" + key + "\"";
        int index = json.indexOf(token);
        if (index < 0) {
            return null;
        }
        int colon = json.indexOf(':', index + token.length());
        if (colon < 0) {
            return null;
        }
        int pos = colon + 1;
        while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
            pos++;
        }
        if (pos >= json.length()) {
            return null;
        }
        char start = json.charAt(pos);
        if (start == '"') {
            int end = pos + 1;
            StringBuilder value = new StringBuilder();
            while (end < json.length()) {
                char c = json.charAt(end);
                if (c == '"' && json.charAt(end - 1) != '\\') {
                    break;
                }
                value.append(c);
                end++;
            }
            return "\"" + value.toString() + "\"";
        }
        int end = pos;
        while (end < json.length() && ",}]".indexOf(json.charAt(end)) == -1) {
            end++;
        }
        return json.substring(pos, end).trim();
    }

    private static ProductoCatalogo findById(String id, List<ProductoCatalogo> products) {
        for (ProductoCatalogo product : products) {
            if (id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    private static int indexOfId(String id, List<ProductoCatalogo> products) {
        for (int i = 0; i < products.size(); i++) {
            if (id.equals(products.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
}
