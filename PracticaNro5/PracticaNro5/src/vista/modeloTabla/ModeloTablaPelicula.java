/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTabla;

import controlador.dao.GeneroDao;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Pelicula;

/**
 *
 * @author Usuario Asus
 */
public class ModeloTablaPelicula extends AbstractTableModel {

    ListaEnlazada<Pelicula> dato;

    public ListaEnlazada<Pelicula> getDato() {
        return dato;
    }

    public void setDato(ListaEnlazada<Pelicula> dato) {
        this.dato = dato;
    }

    @Override
    public int getRowCount() {
        return dato.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Pelicula ju = null;

        try {
            ju = dato.get(i);
        } catch (Exception ex) {
        }
        switch (i1) {
            case 0:
                return (ju != null) ? ju.getId() : "No definido";
            case 1:
                return (ju != null) ? ju.getNombre() : "No definido";
            case 2:
                return (ju != null) ? ju.getRating() : "No definido";
            case 3:
                return (ju != null) ? new GeneroDao().obtener(ju.getId_genero()) : "No definido";

            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Titulo";
            case 2:
                return "Rating";
            case 3:
                return "Genero";

            default:
                return null;
        }
    }
}
