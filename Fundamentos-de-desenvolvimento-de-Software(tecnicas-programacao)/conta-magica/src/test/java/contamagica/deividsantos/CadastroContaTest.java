package contamagica.deividsantos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadastroContaTest {

    private CadastroConta cadastroConta;

    @BeforeEach
    public void setup(){
        cadastroConta = new CadastroConta();
    }

    @Test
    void removerConta() {
        ContaMagica contaMagica = new ContaMagica("aehoo");
        ContaMagica contaMagica2 = new ContaMagica("aehoo2");
        cadastroConta.inserirConta(contaMagica);
        cadastroConta.inserirConta(contaMagica2);
        cadastroConta.removerConta("aehoo");
        assertEquals(contaMagica2, cadastroConta.pesquisarConta("aehoo2"));
        assertNull(cadastroConta.pesquisarConta("aehoo"));
    }

    @Test
    void inserirConta() {
        ContaMagica contaMagica = new ContaMagica("aehoo");
        ContaMagica contaMagica2 = new ContaMagica("aehoo2");
        cadastroConta.inserirConta(contaMagica);
        cadastroConta.inserirConta(contaMagica2);
        assertEquals(contaMagica, cadastroConta.pesquisarConta("aehoo"));
        assertEquals(contaMagica2, cadastroConta.pesquisarConta("aehoo2"));
    }

    @Test
    void pesquisarConta() {
        ContaMagica contaMagica = new ContaMagica("aehoo");
        cadastroConta.inserirConta(contaMagica);
        assertEquals(contaMagica, cadastroConta.pesquisarConta("aehoo"));
    }
}