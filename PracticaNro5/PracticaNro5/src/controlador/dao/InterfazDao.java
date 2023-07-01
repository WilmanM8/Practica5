/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador.dao;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.io.IOException;

/**
 *
 * @author Usuario Asus
 */
public interface InterfazDao<E> {
    public void guardar(E obj) throws IOException;
    
    public void modificar(E obj, Integer pos) throws VacioException, PosicionException, IOException;
    
    public ListaEnlazada<E> listar();
    
    public E obtener(Integer id);
}
