package com.catalogo.repository;

import com.catalogo.model.ProductoCatalogo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CatalogoRepositoryJSON implements ICatalogoRepository {

    private final String filePath;

    public CatalogoRepositoryJSON(String filePath) {
        this.filePath = filePath;
        inicializarArchivo();
    }

    @Override
    public void save(ProductoCatalogo producto) {
        List<ProductoCatalogo> productos = findAll();
        productos.add(producto);
        escribirJSON(productos);
    }

    @Override
    public List<ProductoCatalogo> findAll() {
        String contenido = leerArchivo();
        return parsearJSON(contenido);
    }

    @Override
    public void update(ProductoCatalogo actualizado) {
        List<ProductoCatalogo> productos = findAll();
        boolean encontrado = false;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(actualizado.getId())) {
                productos.set(i, actualizado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new IllegalArgumentException(
                    "Producto con ID '" + actualizado.getId() + "' no encontrado.");
        }
        escribirJSON(productos);
    }

    @Override
    public void delete(String id) {
        List<ProductoCatalogo> productos = findAll();
        boolean removido = productos.removeIf(p -> p.getId().equals(id));
        if (!removido) {
            throw new IllegalArgumentException("Producto con ID '" + id + "' no existe.");
        }
        escribirJSON(productos);
    }

    private void inicializarArchivo() {
        File archivo = new File(filePath);
        File directorio = archivo.getParentFile();
        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }
        if (!archivo.exists()) {
            try {
                Files.writeString(Path.of(filePath), "[]");
            } catch (IOException e) {
                throw new RuntimeException("No se pudo inicializar el archivo JSON: " + filePath, e);
            }
        }
    }

    private String leerArchivo() {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo JSON.", e);
        }
    }

    private void escribirJSON(List<ProductoCatalogo> productos) {
        try {
            Files.writeString(Path.of(filePath), serializarJSON(productos));
        } catch (IOException e) {
            throw new RuntimeException("Error escribiendo en el archivo JSON.", e);
        }
    }

    public String serializarJSON(List<ProductoCatalogo> productos) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < productos.size(); i++) {
            ProductoCatalogo p = productos.get(i);
            sb.append("  {\n");
            sb.append("    \"id\": \"").append(escapar(p.getId())).append("\",\n");
            sb.append("    \"nombre\": \"").append(escapar(p.getNombre())).append("\",\n");
            sb.append("    \"descripcion\": \"").append(escapar(p.getDescripcion())).append("\",\n");
            sb.append("    \"precio\": ").append(p.getPrecio()).append(",\n");
            sb.append("    \"categoria\": \"").append(escapar(p.getCategoria())).append("\"\n");
            sb.append("  }");
            if (i < productos.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    public List<ProductoCatalogo> parsearJSON(String json) {
        List<ProductoCatalogo> lista = new ArrayList<>();
        if (json == null || json.isBlank() || "[]".equals(json.trim())) {
            return lista;
        }

        int inicio = 0;
        while ((inicio = json.indexOf('{', inicio)) != -1) {
            int fin = json.indexOf('}', inicio);
            if (fin == -1) {
                break;
            }
            String bloque = json.substring(inicio, fin + 1);
            lista.add(parsearProducto(bloque));
            inicio = fin + 1;
        }
        return lista;
    }

    private ProductoCatalogo parsearProducto(String bloque) {
        String id = extraerTexto(bloque, "id");
        String nombre = extraerTexto(bloque, "nombre");
        String descripcion = extraerTexto(bloque, "descripcion");
        double precio = Double.parseDouble(extraerNumero(bloque, "precio"));
        String categoria = extraerTexto(bloque, "categoria");
        return new ProductoCatalogo(id, nombre, descripcion, precio, categoria);
    }

    private String extraerTexto(String bloque, String clave) {
        String patron = "\"" + clave + "\"";
        int idx = bloque.indexOf(patron);
        if (idx == -1) {
            return "";
        }
        int dosPuntos = bloque.indexOf(':', idx + patron.length());
        int inicioComilla = bloque.indexOf('"', dosPuntos + 1);
        if (inicioComilla == -1) {
            return "";
        }

        StringBuilder valor = new StringBuilder();
        boolean escape = false;
        for (int i = inicioComilla + 1; i < bloque.length(); i++) {
            char actual = bloque.charAt(i);
            if (escape) {
                valor.append(actual);
                escape = false;
                continue;
            }
            if (actual == '\\') {
                escape = true;
                continue;
            }
            if (actual == '"') {
                return valor.toString();
            }
            valor.append(actual);
        }
        return valor.toString();
    }

    private String extraerNumero(String bloque, String clave) {
        String patron = "\"" + clave + "\"";
        int idx = bloque.indexOf(patron);
        if (idx == -1) {
            return "0";
        }
        int dosPuntos = bloque.indexOf(':', idx + patron.length());
        int inicio = dosPuntos + 1;
        while (inicio < bloque.length() && Character.isWhitespace(bloque.charAt(inicio))) {
            inicio++;
        }
        int fin = inicio;
        while (fin < bloque.length() && ",}\n\r".indexOf(bloque.charAt(fin)) == -1) {
            fin++;
        }
        return bloque.substring(inicio, fin).trim();
    }

    private String escapar(String valor) {
        if (valor == null) {
            return "";
        }
        return valor.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public String getFilePath() {
        return filePath;
    }
}
