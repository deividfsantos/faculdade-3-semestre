package contamagica.deividsantos;

import java.math.BigDecimal;
import java.util.Scanner;

public class Banco {

    private Scanner scanner;
    private CadastroConta cadastroConta;

    public Banco() {
        this.scanner = new Scanner(System.in);
        this.cadastroConta = new CadastroConta();
    }

    public void menu(){
        while (true) {
            System.out.println("\nDigite a opção desejada\n" +
                    "1 - Criar uma conta\n" +
                    "2 - Depositar dinheiro em uma conta\n" +
                    "3 - Sacar dinheiro de uma conta\n" +
                    "4 - Consultar o saldo de uma conta\n" +
                    "5 - Encerrar uma conta\n" +
                    "Opção: ");
            int op = scanner.nextInt();
            switch (op) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    buscarSaldo();
                    break;
                case 5:
                    encerrarConta();
                    break;
                default:
                    System.out.println("Opção inválida, digite novamente.");
                    break;
            }
        }
    }

    private void encerrarConta() {
        System.out.println("Digite o nome do titular da conta: ");
        String nomeEncerrar = scanner.next();
        cadastroConta.removerConta(nomeEncerrar);
        System.out.println("Conta encerrada com sucesso.");
    }

    private void buscarSaldo() {
        System.out.println("Digite o nome do titular da conta: ");
        String nomeSaldo = scanner.next();
        ContaMagica contaSaldo = cadastroConta.pesquisarConta(nomeSaldo);
        if(contaSaldo == null){
            System.out.println("Conta inexistente.");
        }else {
            System.out.println("O saldo em conta é: " + contaSaldo.getSaldo());
        }
    }

    private void sacar() {
        System.out.println("Digite o nome do titular da conta: ");
        String nome = scanner.next();
        System.out.println("Digite o valor que deseja sacar: ");
        double valorSaque = scanner.nextDouble();
        ContaMagica contaSaque = cadastroConta.pesquisarConta(nome);
        if(contaSaque == null){
            System.out.println("Conta inexistente.");
        }else {
            contaSaque.retirada(BigDecimal.valueOf(valorSaque));
        }
    }

    private void depositar() {
        System.out.println("Digite o nome do titular da conta: ");
        String nomeTitular = scanner.next();
        System.out.println("Digite o valor que deseja depositar: ");
        double valor = scanner.nextDouble();
        ContaMagica contaMagica = cadastroConta.pesquisarConta(nomeTitular);
        if(contaMagica == null){
            System.out.println("Conta inexistente.");
        }else {
            contaMagica.deposito(BigDecimal.valueOf(valor));
        }
    }

    private void cadastrarConta() {
        System.out.println("Digite o nome do cliente: ");
        cadastroConta.inserirConta(new ContaMagica(scanner.next()));
    }

}
