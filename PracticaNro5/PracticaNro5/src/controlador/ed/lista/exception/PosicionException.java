/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package controlador.ed.lista.exception;

/**
 *
 * @author Usuario Asus
 */
public class PosicionException extends Exception{

    /**
     * Creates a new instance of <code>VacioException</code> without detail
     * message.
     */
    public PosicionException(){
        super("Posicion no valida");
    }

    /**
     * Constructs an instance of <code>VacioException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PosicionException(String msg) {
        super(msg);
    }
}
