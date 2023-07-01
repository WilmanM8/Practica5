/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.io.IOException;
import modelo.Pelicula;
import modelo.Genero;

/**
 *
 * @author Usuario Asus
 */
public class PeliculaDao extends AdaptadorDao<Pelicula> {
    private Pelicula pelicula;

    public PeliculaDao() {
        super(Pelicula.class);
    }

    public Pelicula getPelicula() {
        if (this.pelicula == null) {
            this.pelicula = new Pelicula();
        }
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void guardar() throws IOException {
        pelicula.setId(generateID());
        this.guardar(pelicula);
    }

    public void modificar(Integer pos) throws VacioException, PosicionException, IOException {
        this.modificar(pelicula, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Pelicula> ordenarTitulo(ListaEnlazada<Pelicula> lista) {
        try {
            Pelicula[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Pelicula key = lista.get(i);
                int j = i - 1;
                while (j >= 0 && (matriz[j].getNombre().compareToIgnoreCase(key.getNombre())) > 0) {
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

    public ListaEnlazada<Pelicula> ordenarID(ListaEnlazada<Pelicula> lista, Integer tipo) {
        try {
            Pelicula[] matriz = lista.toArray();
            for (int i = 1; i < lista.size(); i++) {
                Pelicula key = matriz[i];
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

    public ListaEnlazada<Pelicula> buscarPorTituloTodo(String dato) throws VacioException, PosicionException {
        ListaEnlazada<Pelicula> resultado = new ListaEnlazada<>();
        ListaEnlazada<Pelicula> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Pelicula aux = lista.get(i);
            if (aux.getNombre().toLowerCase().startsWith(dato)) {
                resultado.insertar(aux);
            }
        }
        return resultado;
    }
    
    public ListaEnlazada<Pelicula> busquedaLinealTitulo(String texto) throws Exception {

        ListaEnlazada<Pelicula> r = new ListaEnlazada<>();
        ListaEnlazada<Pelicula> lista = listar();
        for (int i = 0; i < lista.size(); i++) {

            Pelicula aux = lista.get(i);
            if (aux.getNombre().toLowerCase().startsWith(texto.toLowerCase())) {
                r.insertar(aux);
            }            
        }
        return r;
    }
    
    
    public ListaEnlazada<Pelicula> busquedaLinealGenero(String texto) throws Exception {
        
        Genero equipo = new GeneroDao().buscarPorTituloTodo(texto);
        ListaEnlazada<Pelicula> r = new ListaEnlazada<>();
        ListaEnlazada<Pelicula> lista = listar();
        for (int i = 0; i < lista.size(); i++) {

            Pelicula aux = lista.get(i);
            if(equipo != null){
                if (aux.getId_genero().intValue() == equipo.getId().intValue()) {
                    r.insertar(aux);
                    
                }
            }
        }
        return r;
    }
    
    public ListaEnlazada<Pelicula> busquedaLinealRating(String texto) throws Exception {

        ListaEnlazada<Pelicula> r = new ListaEnlazada<>();
        ListaEnlazada<Pelicula> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Pelicula aux = lista.get(i);
            if (aux.getRating().toLowerCase().startsWith(texto.toLowerCase())) {
                r.insertar(aux);
            }
        }
        return r;
    }
    
    public Pelicula buscarBinariaTitulo(String texto) throws VacioException, PosicionException {
        ListaEnlazada<Pelicula> lista = listar();
        ListaEnlazada<Pelicula> listaO =  ordenarTitulo(lista);
        
        Pelicula[] matriz = listaO.toArray();
        
        int derecha = 0;
        int izquierda = listaO.size() - 1;

        while (derecha <= izquierda) {
            int medio = derecha + (izquierda - derecha) / 2;

            Pelicula aux = matriz[medio];            

            if (aux.getNombre().compareToIgnoreCase(texto) == 0) {
                return aux;
            } else {
                if (aux.getNombre().compareToIgnoreCase(texto) < 0) {
                    derecha = medio + 1;
                } else {
                    izquierda = medio - 1; 
                }
            }
        }
        return null; 
    }
    
    public Pelicula buscarBinariaGenero(String texto) throws VacioException, PosicionException {
        Genero g = new GeneroDao().buscarPorTitulo(texto);
        ListaEnlazada<Pelicula> lista = listar();
        ListaEnlazada<Pelicula> listaOrdenada =  ordenarTitulo(lista);
        
        Pelicula[] matriz = listaOrdenada.toArray();
        
        int derecha = 0;
        int izquierda = listaOrdenada.size() - 1;

        while (derecha <= izquierda) {
            int medio = derecha + (izquierda - derecha) / 2;

            Pelicula aux = matriz[medio];                        
            if(g != null){
                if (aux.getId_genero().intValue() == g.getId().intValue()) {
                    return aux;
                } else {
                    if (aux.getId_genero().intValue() < g.getId().intValue()) {
                        derecha = medio + 1; 
                    } else {
                        izquierda = medio - 1; 
                    }
                }
            }
        }
        return null; 
    }
    
    public Pelicula buscarBinariaRating(String texto) throws VacioException, PosicionException {
        ListaEnlazada<Pelicula> lista = listar();
        ListaEnlazada<Pelicula> listaOrdenada =  ordenarTitulo(lista);
        
        Pelicula[] matriz = listaOrdenada.toArray();
        
        int derecha = 0;
        int izquierda = listaOrdenada.size() - 1;

        while (derecha <= izquierda) {
            int medio = derecha + (izquierda - derecha) / 2;

            Pelicula aux = matriz[medio];                        
            if (aux.getRating().compareToIgnoreCase(texto) == 0) {
                return aux;
            } else {
                if (aux.getRating().compareToIgnoreCase(texto) < 0) {
                    derecha = medio + 1; 
                } else {
                    izquierda = medio - 1; 
                }
            }
        }

        return null;
    }
    
}
