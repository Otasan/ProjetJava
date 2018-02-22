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
public class ScoreException extends Exception {

    /**
     * Creates a new instance of <code>ScoreException</code> without detail
     * message.
     */
    public ScoreException() {
    }

    /**
     * Constructs an instance of <code>ScoreException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ScoreException(String msg) {
        super(msg);
    }
}
