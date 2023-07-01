/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.io.IOException;
import modelo.Genero;

/**
 *
 * @author Usuario Asus
 */
public class GeneroDao extends AdaptadorDao<Genero> {

    private Genero genero;

    public GeneroDao() {
        super(Genero.class);
    }

    public Genero getGenero() {
        if (this.genero == null) {
            this.genero = new Genero();
        }
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(genero, pos);
    }

    public void guardar() {
        
        genero.setId(generateID());
        try {
            
            this.guardar(genero);
        } catch (IOException ex) {
            
        }
    }

    private Integer generateID() {
        
        return listar().size() + 1;
    }

    public ListaEnlazada<Genero> ordenarTitulo(ListaEnlazada<Genero> lista, Integer tipo) {
        try {
            Genero[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Genero key = matriz[i];
                int j = i - 1;
                switch (tipo) {
                    case 0:

                }
                while (j >= 0 && (matriz[j].getClaseGenero().compareToIgnoreCase(key.getClaseGenero())) > 0) {
                    matriz[j + 1] = matriz[j];
                    j = j - 1;
                }
                matriz[j + 1] = key;
            }
            lista.toList(matriz);
        } catch (Exception e) {
        }
        return lista;
    }

    public ListaEnlazada<Genero> ordenarID(ListaEnlazada<Genero> lista, Integer tipo) {
        try {
            Genero[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Genero key = matriz[i];
                int j = i - 1;
                switch (tipo) {
                    case 0:
                        while (j >= 0 && (matriz[j].getId() > key.getId())) {
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }
                        break;
                    case 1:
                        while (j >= 0 && (matriz[j].getId() < key.getId())) {
                            matriz[j + 1] = matriz[j];
                            j = j - 1;
                        }
                        break;
                }
            }
            lista.toList(matriz);
        } catch (Exception e) {
            
        }
        return lista;
    }

    public Genero buscarPorTituloTodo(String dato) throws VacioException, PosicionException {
        Genero resultado = null;
        ListaEnlazada<Genero> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Genero aux = lista.get(i);
            if (aux.getClaseGenero().toLowerCase().startsWith(dato.toLowerCase())) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }

    public Genero buscarPorTitulo(String dato) throws VacioException, PosicionException {
        Genero resultado = null;
        ListaEnlazada<Genero> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Genero aux = lista.get(i);
            if (aux.getClaseGenero().toLowerCase().startsWith(dato.toLowerCase())) {
                resultado = aux;
                break;
            }
        }
        return resultado;
    }
}
