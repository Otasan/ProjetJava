/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Echecs;

/**
 *
 * @author Utilisateur
 */
public class EchecsException extends Exception {

    /**
     * Creates a new instance of <code>EchecsException</code> without detail
     * message.
     */
    public EchecsException() {
    }

    /**
     * Constructs an instance of <code>EchecsException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EchecsException(String msg) {
        super(msg);
    }
}
