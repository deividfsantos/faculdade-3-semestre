package contamagica.deividsantos;

import java.util.ArrayList;
import java.util.List;

public class CadastroConta {

    private List<ContaMagica> contasMagicas;

    public CadastroConta() {
        contasMagicas = new ArrayList<>();
    }

    public CadastroConta(List<ContaMagica> contasMagicas) {
        this.contasMagicas = contasMagicas;
    }

    public void removerConta(String nomeTitular){
        for (ContaMagica contasMagica : contasMagicas) {
            if (contasMagica.getNomeCliente().equalsIgnoreCase(nomeTitular)) {
                contasMagicas.remove(contasMagica);
            }
        }
    }

    public void inserirConta(ContaMagica contaMagica){
        contasMagicas.add(contaMagica);
    }

    public ContaMagica pesquisarConta(String nomeTitular){
        for (ContaMagica contasMagica : contasMagicas) {
            if (contasMagica.getNomeCliente().equalsIgnoreCase(nomeTitular)) {
                return contasMagica;
            }
        }
        return null;
    }

}
