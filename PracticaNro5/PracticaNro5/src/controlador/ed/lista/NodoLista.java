/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ed.lista;

/**
 *
 * @author Usuario Asus
 */
public class NodoLista<E> {
    private E dato; 
    private NodoLista siguiente; 

    public NodoLista() {
        dato = null;
        siguiente = null;
    }

    public NodoLista(E info, NodoLista sig) {
        this.dato = info;
        this.siguiente = sig;
    }
    
    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }
}
