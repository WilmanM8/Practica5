/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vista;

import controlador.dao.GeneroDao;

/**
 *
 * @author Usuario Asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            GeneroDao g = new GeneroDao();
            
            g.getGenero().setClaseGenero("Terror");
            g.getGenero().setEstado(true);
            g.guardar();
            g.listar().imprimir();
            g.getGenero().setClaseGenero("Comedia");
            g.getGenero().setEstado(true);
            g.guardar();
            g.listar().imprimir();
            g.getGenero().setClaseGenero("Accion");
            g.getGenero().setEstado(true);
            g.guardar();
            g.listar().imprimir();
            g.getGenero().setClaseGenero("Ciencia Ficcion");
            g.getGenero().setEstado(true);
            g.guardar();
            g.listar().imprimir();
            
            
        } catch (Exception e) {
        }
    }
    
}
