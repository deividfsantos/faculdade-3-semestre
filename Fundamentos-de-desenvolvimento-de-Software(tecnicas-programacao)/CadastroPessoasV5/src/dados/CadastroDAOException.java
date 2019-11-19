/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

/**
 *
 * @author Júlio
 */
public class CadastroDAOException extends Exception {

    /**
     * Creates a new instance of
     * <code>CadastroDAOException</code> without detail message.
     */
    public CadastroDAOException() {
    }

    /**
     * Constructs an instance of
     * <code>CadastroDAOException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CadastroDAOException(String msg) {
        super(msg);
    }

    public CadastroDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
