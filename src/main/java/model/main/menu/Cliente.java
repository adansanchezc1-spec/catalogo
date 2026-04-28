/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.menu;

/**
 *
 * @author ADAN
 */
public class Cliente {

    private  String nombre;
    private  String id;
    
    public Cliente(String nombre,String id){
        this.nombre=nombre;
        this.id=id;
    }
        public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }
}
