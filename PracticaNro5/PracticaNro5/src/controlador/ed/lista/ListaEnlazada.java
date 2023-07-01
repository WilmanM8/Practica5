/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ed.lista;

import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;

/**
 *
 * @author Usuario Asus
 */
public class ListaEnlazada<E> {

    private NodoLista<E> cabeza;
    private Integer size = 0;

    public NodoLista getCabecera() {
        return cabeza;
    }

    public void setCabecera(NodoLista cabecera) {
        this.cabeza = cabecera;
    }

    public boolean isEmpty() {
        return cabeza == null;
    }

    public boolean insertar(E info) {
        NodoLista<E> nuevo = new NodoLista<>(info, null);
        if (isEmpty()) {
            this.cabeza = nuevo;
            this.size++;
        } else {
            NodoLista<E> aux = cabeza;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            this.size++;
        }
        return true;
    }

    public void imprimir() throws VacioException {
        if (isEmpty()) {
            throw new VacioException();
        } else {
            NodoLista<E> aux = cabeza;
            for (int i = 0; i < size(); i++) {
                System.out.println(aux.getDato() + " ");
                aux = aux.getSiguiente();
            }
        }
    }

    public void deleteAll() {
        this.cabeza = null;
        this.size = 0;
    }

    public void insertarInicio(E info) {

        if (isEmpty()) {
            insertar(info);
        } else {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
            size++;
        }
    }

    public void insertarPosicion(E info, Integer pos) throws PosicionException {
        if (isEmpty()) {
            insertar(info);
        } else if (pos >= 0 && pos < size() && pos == 0) {
            insertarInicio(info);
        } else if (pos >= 0 && pos < size()) {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            NodoLista<E> aux = cabeza;
            for (int i = 0; i < (pos - 1); i++) {
                aux = aux.getSiguiente();
            }
            NodoLista<E> sig = aux.getSiguiente();
            aux.setSiguiente(nuevo);
            nuevo.setSiguiente(sig);
            size++;
        } else {
            throw new PosicionException();
        }
    }

    public E get(Integer pos) throws VacioException, PosicionException {

        if (isEmpty()) {
            throw new VacioException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabeza.getDato();
                } else {
                    NodoLista<E> aux = cabeza;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                }
            } else {
                throw new PosicionException();
            }
            return dato;
        }

    }

    public E delete(Integer pos) throws VacioException, PosicionException {

        if (isEmpty()) {
            throw new VacioException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabeza.getDato();
                    cabeza = cabeza.getSiguiente();
                    size--;
                } else {
                    NodoLista<E> aux = cabeza;
                    for (int i = 0; i < (pos - 1); i++) {
                        aux = aux.getSiguiente();
                    }
                    NodoLista<E> aux1 = aux.getSiguiente();
                    dato = aux1.getDato();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    size--;
                }
            } else {
                throw new PosicionException();
            }
            return dato;
        }
    }

    public E[] toArray() {
        E[] arreglo = null;
        if (this.size > 0) {
            arreglo = (E[]) java.lang.reflect.Array.newInstance(cabeza.getDato().getClass(), this.size);
            NodoLista<E> aux = cabeza;
            for (int i = 0; i < this.size; i++) {
                arreglo[i] = aux.getDato();
                aux = aux.getSiguiente();
            }
        }
        return arreglo;
    }

    public ListaEnlazada<E> toList(E[] arreglo) {
        this.deleteAll();
        for (int i = 0; i < arreglo.length; i++) {
            this.insertar(arreglo[i]);
        }
        return this;
    }

    public Integer size() {
        return size;
    }
    
    public void update(Integer pos, E dato) throws VacioException, PosicionException {
        if (isEmpty()) {
            throw new VacioException();
        } else {
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabeza.getDato();

                } else {
                    NodoLista<E> aux = cabeza;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    aux.setDato(dato);
                }
            } else {
                throw new PosicionException();
            }
        }
    }
}
