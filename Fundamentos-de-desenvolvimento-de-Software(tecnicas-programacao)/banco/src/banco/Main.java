/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 18203069
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        Cadastro cad = new Cadastro();
        for (;;) {
            cad.menu(clientes);
        }
    }

}
