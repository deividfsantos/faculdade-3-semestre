/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Júlio
 */
public class CadastroException extends Exception {

    /**
     * Creates a new instance of
     * <code>CadastroException</code> without detail message.
     */
    public CadastroException() {
    }

    /**
     * Constructs an instance of
     * <code>CadastroException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CadastroException(String msg) {
        super(msg);
    }

    public CadastroException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
