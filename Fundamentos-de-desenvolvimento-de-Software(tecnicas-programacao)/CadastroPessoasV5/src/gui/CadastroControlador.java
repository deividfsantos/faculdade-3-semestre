/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import negocio.*;

/**
 *
 * @author Julio
 */
public class CadastroControlador {
    private CadastroFachada fachada;
    private ListaPessoasModel modelSaidaTexto;

    public CadastroControlador() throws CadastroException {
        fachada = new CadastroFachada();
        modelSaidaTexto = new ListaPessoasModel(toListString(fachada.buscarTodos()));
    }

    public ListModel<String> getListaPessoasModel(){
        return modelSaidaTexto;
    }
    
    private List<String> toListString(List<Pessoa> listaOrigem) {
        List<String> listaDestino = new ArrayList<String>(listaOrigem.size());
        for(Pessoa p : listaOrigem) {
            listaDestino.add(p.toString());
        }
        return listaDestino;
    }
    
    public boolean adicionarPessoa(String nome, String telefone, boolean masculino) throws CadastroException {
        Pessoa p = fachada.adicionarPessoa(nome, telefone, masculino);
        if(p != null){
            modelSaidaTexto.add(p.toString());
            return true;
        }
        return false;
    }

    public List<String> buscarHomens() throws CadastroException {
        return toListString(fachada.buscarHomens());
    }

    public List<String> buscarMulheres() throws CadastroException {
        return toListString(fachada.buscarMulheres());
    }

    public List<String> getTodos() throws CadastroException {
        return toListString(fachada.buscarTodos());
    }
}
