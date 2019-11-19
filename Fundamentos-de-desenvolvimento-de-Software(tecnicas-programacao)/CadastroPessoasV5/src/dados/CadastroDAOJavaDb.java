/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import negocio.CadastroDAO;
import negocio.Pessoa;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Júlio
 */
public class CadastroDAOJavaDb implements CadastroDAO{
    private static CadastroDAOJavaDb ref;
    
    public static CadastroDAOJavaDb getInstance() throws CadastroDAOException {
        if (ref == null)
            ref = new CadastroDAOJavaDb();
        return ref;
    }
    
    private CadastroDAOJavaDb() throws CadastroDAOException {
        try {
             Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new CadastroDAOException("JdbcOdbDriver not found!!");
        }
        // Cria o banco de dados vazio
        // Retirar do comentário se necessário
        /*
        try {
            createDB();
        } catch (Exception ex) {
            System.out.println("Problemas para criar o banco: "+ex.getMessage());
            System.exit(0);
        }
        */
    }
    
    private static void createDB() throws CadastroDAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE Pessoas ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "NOME VARCHAR(100) NOT NULL,"
                    + "TELEFONE CHAR(8) NOT NULL,"
                    + "SEXO CHAR(1) NOT NULL"
                    + ")";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException ex) {
            throw new CadastroDAOException(ex.getMessage());
        }
    }
    
    private static Connection getConnection() throws SQLException {
        //derbyDB sera o nome do diretorio criado localmente
        return DriverManager.getConnection("jdbc:derby:derbyDB");
    }
    
    @Override
    public boolean adicionar(Pessoa p) throws CadastroDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO PESSOAS (NOME, TELEFONE, SEXO) VALUES (?,?,?)" //                             1        2         3            4          5             6
                    );
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setString(3, Character.toString(p.getSexo()));
            int ret = stmt.executeUpdate();
            con.close();
            return (ret>0);
        } catch (SQLException ex) {
            throw new CadastroDAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public Pessoa getPessoaPorNome(String n) throws CadastroDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM PESSOAS WHERE NOME=?"
                    );
            stmt.setString(1, n);
            ResultSet resultado = stmt.executeQuery();
            Pessoa p = null;
            if(resultado.next()) {
                String nome = resultado.getString("NOME");
                String telefone = resultado.getString("TELEFONE");
                String sexo = resultado.getString("SEXO");
                p = new Pessoa(nome, telefone, sexo.equals("M"));
            }
            return p;
        } catch (SQLException ex) {
            throw new CadastroDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Pessoa> getHomens() throws CadastroDAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM PESSOAS WHERE SEXO='M'");
            List<Pessoa> lista = new ArrayList<Pessoa>();
            while(resultado.next()) {
                String nome = resultado.getString("NOME");
                String telefone = resultado.getString("TELEFONE");
                String sexo = resultado.getString("SEXO");
                Pessoa p = new Pessoa(nome, telefone, sexo.equals("M"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            throw new CadastroDAOException("Falha ao buscar.", ex);
        }
    }

    @Override
    public List<Pessoa> getMulheres() throws CadastroDAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM PESSOAS WHERE SEXO='F'");
            List<Pessoa> lista = new ArrayList<Pessoa>();
            while(resultado.next()) {
                String nome = resultado.getString("NOME");
                String telefone = resultado.getString("TELEFONE");
                String sexo = resultado.getString("SEXO");
                Pessoa p = new Pessoa(nome, telefone, sexo.equals("M"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            throw new CadastroDAOException("Falha ao buscar.", ex);
        }    }

    @Override
    public List<Pessoa> getTodos() throws CadastroDAOException {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM PESSOAS");
            List<Pessoa> lista = new ArrayList<Pessoa>();
            while(resultado.next()) {
                String nome = resultado.getString("NOME");
                String telefone = resultado.getString("TELEFONE");
                String sexo = resultado.getString("SEXO");
                Pessoa p = new Pessoa(nome, telefone, sexo.equals("M"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            throw new CadastroDAOException("Falha ao buscar.", ex);
        }    }
    
}
