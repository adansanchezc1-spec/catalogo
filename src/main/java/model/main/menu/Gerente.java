/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.main.menu;
import java.util.Scanner;
public class Gerente {
    
    private String id;
    private String nombre;
    private String idCliente;
    private String nombreCliente;
    private String info;
    Scanner sc= new Scanner(System.in);

    public void setCliente(){
        System.out.println("Introduzca el nombre del cliente");
        nombreCliente=sc.nextLine();
        System.out.println("Introduzca el id del cliente");
        idCliente=sc.nextLine();
        Cliente miCliente= new Cliente(nombreCliente,idCliente);

    }
    public String realizarReunion(Cliente c){

        return info;
    
    }
public Gerente(String id,String nombre){
    this.id=id;
    this.nombre=nombre;
}
}
