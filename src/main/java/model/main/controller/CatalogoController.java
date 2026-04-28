/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.controller;
import model.main.menu.Gerente;


/**
 *
 * @author ADAN
 */
public class CatalogoController {
    
    public void ejecutarGerente(Gerente c){
        Gerente c1 = new Gerente("1","Adan");
        c1.realizarReunion(new model.main.menu.Cliente("Cliente1","123"));
        c1.setProveedorComercial();
        c1.proveerInformacion();
    }
}
