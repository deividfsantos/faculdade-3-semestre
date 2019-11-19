/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import dados.CadastroDAOException;
import dados.CadastroDAOJavaDb;
import java.util.List;

/**
 *
 * @author Julio
 */
public class CadastroFachada {
    private CadastroDAO dao;
    
    public CadastroFachada() throws CadastroException {
        try {
            dao = CadastroDAOJavaDb.getInstance();
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha de criação da fachada!", e);
        }
    }
    
    public Pessoa adicionarPessoa(String nome, String telefone, boolean masculino) throws CadastroException{
        if(!ValidadorPessoa.validaNome(nome)) {
            throw new CadastroException("Nome de pessoa inválido!");
        }
        if(!ValidadorPessoa.validaTelefone(telefone)) {
            throw new CadastroException("Número de telefone inválido!");
        }
        Pessoa p = new Pessoa(nome, telefone, masculino);
        try {
            boolean ok = dao.adicionar(p);
            if(ok) {
                return p;
            }
            return null;
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha ao adicionar pessoa!", e);
        }
    }

    public List<Pessoa> buscarHomens() throws CadastroException{
        try {
            return dao.getHomens();
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha ao buscar homens!", e);
        }
    }

    public List<Pessoa> buscarMulheres() throws CadastroException{
        try {
            return dao.getMulheres();
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha ao buscar mulheres!", e);
        }
    }

    public List<Pessoa> buscarTodos() throws CadastroException{
        try {
            return dao.getTodos();
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha ao buscar pessoas!", e);
        }
    }

    public Pessoa buscarPessoaPorNome(String n) throws CadastroException{
        try{
            return dao.getPessoaPorNome(n);
        } catch(CadastroDAOException e) {
            throw new CadastroException("Falha ao buscar pessoa", e);
        }
    }
}
