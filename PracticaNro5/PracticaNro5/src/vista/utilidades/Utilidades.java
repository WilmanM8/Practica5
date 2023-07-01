/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.utilidades;

import controlador.dao.AdaptadorDao;
import controlador.dao.GeneroDao;
import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import javax.swing.JComboBox;
import modelo.Genero;

/**
 *
 * @author Usuario Asus
 */
public class Utilidades {
    public static void cargarGenero(JComboBox cbx, GeneroDao ed) throws VacioException, PosicionException {
        cbx.removeAllItems();
        ListaEnlazada<Genero> lista = ed.ordenarTitulo(ed.listar(), AdaptadorDao.ASCENDENTE);
        for(int i = 0; i < lista.size(); i++){
            cbx.addItem(lista.get(i).getClaseGenero());
        }
        
    }
}
