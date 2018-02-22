/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs;

/**
 *
 * @author Dobby
 */
public class ConnexionException extends Exception {

    /**
     * Creates a new instance of <code>ConnexionException</code> without detail
     * message.
     */
    public ConnexionException() {
    }

    /**
     * Constructs an instance of <code>ConnexionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ConnexionException(String msg) {
        super(msg);
    }
}
