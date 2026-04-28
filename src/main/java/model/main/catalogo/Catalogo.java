/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package model.main.catalogo;
import model.main.controller.CatalogoController;
import model.main.menu.Gerente;
/**
 *
 * @author ADAN
 */
public class Catalogo {

    public static void main(String[] args) {
        CatalogoController controller = new CatalogoController();
        Gerente gerente = new Gerente("1", "Adan");
        controller.ejecutarGerente(gerente);
    }
}
