/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package model.main.catalogo;
import model.main.menu.Gerente;

/**
 *
 * @author ADAN
 */
public class Catalogo {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Gerente c = new Gerente("1","Adan");
        c.realizarReunion(new model.main.menu.Cliente("Cliente1","123"));
        
    }
}
