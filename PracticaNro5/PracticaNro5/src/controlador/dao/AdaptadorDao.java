/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author Usuario Asus
 */
public class AdaptadorDao<E> implements InterfazDao<E> {

    private Conexion conexion;
    private Class clazz;
    private String url;
    
    public static Integer ASCENDENTE = 0;
    public static Integer DESCENDENTE = 1;

    public AdaptadorDao(Class clazz) {
        this.conexion = new Conexion();
        this.clazz = clazz;
        this.url = Conexion.URL + clazz.getSimpleName().toLowerCase() + ".json";
    }
    
    @Override
    public void modificar(E obj, Integer pos) throws VacioException, PosicionException, IOException {
        ListaEnlazada<E> lista = listar();
        lista.update(pos, obj);
        conexion.getXstream().alias(lista.getClass().getName(), ListaEnlazada.class);
        conexion.getXstream().toXML(lista, new FileWriter(url));
    }

    @Override
    public void guardar(E obj) throws IOException {
        ListaEnlazada<E> lista = listar();
        lista.insertar(obj);
        conexion.getXstream().alias(lista.getClass().getName(), ListaEnlazada.class);
        conexion.getXstream().toXML(lista, new FileWriter(url));
    }

    @Override
    public ListaEnlazada<E> listar() {
        ListaEnlazada<E> lista = new ListaEnlazada<>();
        try {
            lista = (ListaEnlazada<E>) conexion.getXstream().fromXML(new File(url));
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    @Override
    public E obtener(Integer id) {
        E obj = null;
        ListaEnlazada<E> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            try {
                E dato = lista.get(i);
                if (id.intValue() == ((Integer) getValueCampo(dato)).intValue()) {
                    obj = dato;
                }
            } catch (Exception e) {
                System.out.println("Error en metodo " + e);
            }
        }
        return obj;
    }

    private Object getValueCampo(E dato) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method metodo = null;
        for (Method aux : this.clazz.getDeclaredMethods()) {

            if (aux.getName().toLowerCase().equalsIgnoreCase("getId")) {
                metodo = aux;
            }
        }
        if (metodo == null) {

            for (Method aux : this.clazz.getSuperclass().getDeclaredMethods()) {
                if (aux.getName().toLowerCase().equalsIgnoreCase("getId")) {
                    metodo = aux;
                }
                
            }
        }
        return metodo.invoke(dato);
        
    }

    public Integer generarId() {
        
        return listar().size() + 1;
        
    }
    
}
