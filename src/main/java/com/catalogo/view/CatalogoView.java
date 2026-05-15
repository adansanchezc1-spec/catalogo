package com.catalogo.view;

import com.catalogo.model.ProductoCatalogo;

import java.util.List;

public class CatalogoView {

    private static final String LINEA = repetir("-", 72);
    private static final String TITULO = repetir("=", 72);

    public void mostrarProductos(List<ProductoCatalogo> lista) {
        System.out.println("\n" + TITULO);
        System.out.printf("%-8s %-20s %-12s %10s  %-16s%n",
                "ID", "NOMBRE", "CATEGORIA", "PRECIO", "DESCRIPCION");
        System.out.println(LINEA);

        if (lista.isEmpty()) {
            System.out.println("  (Sin productos en el catalogo)");
        } else {
            for (ProductoCatalogo p : lista) {
                System.out.printf("%-8s %-20s %-12s %10.2f  %-16s%n",
                        p.getId(),
                        truncar(p.getNombre(), 20),
                        truncar(p.getCategoria(), 12),
                        p.getPrecio(),
                        truncar(p.getDescripcion(), 16));
            }
        }
        System.out.println(TITULO + "\n");
    }

    public void mostrarMensaje(String msg) {
        System.out.println("  > " + msg);
    }

    public void mostrarError(String msg) {
        System.out.println("  X ERROR: " + msg);
    }

    private String truncar(String texto, int max) {
        if (texto == null) {
            return "";
        }
        return texto.length() <= max ? texto : texto.substring(0, max - 3) + "...";
    }

    private static String repetir(String texto, int veces) {
        StringBuilder sb = new StringBuilder(texto.length() * veces);
        for (int i = 0; i < veces; i++) {
            sb.append(texto);
        }
        return sb.toString();
    }
}
