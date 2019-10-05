/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 18203069
 */
public class Cadastro {

    public void menu(List<Cliente> clientes) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Chamar proximo cliente");
        System.out.println("2 - Registrar novo cliente");
        System.out.println("3 - Sair");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                if (clientes.isEmpty()) {
                    System.out.println("Não há clientes.");
                } else {
                    System.out.println("Numero do caixa");
                    int numeroCaixa = sc.nextInt();
                    if (numeroCaixa > 0 && numeroCaixa < 6) {
                        for (int i = 0; i < clientes.size(); i++) {
                            if (clientes.get(i).getIdade() >= 65) {
                                System.out.println("Removido " + clientes.get(i));
                                clientes.remove(i);
                                break;
                            }
                            System.out.println("Removido cliente " + clientes.get(0));
                            clientes.remove(clientes.get(0));
                        }
                    } else {
                        System.out.println("Removido cliente " + clientes.get(0));
                        clientes.remove(clientes.get(0));
                    }
                }
                break;
            case 2:
                cadastrarCliente(sc, clientes);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Opção incorreta.");
        }
    }

    public void cadastrarCliente(Scanner sc, List<Cliente> clientes) {
        System.out.println("Digite o nome do cliente: ");
        String nome = sc.next();
        System.out.println("Digite a idade do cliente: ");
        int idade = sc.nextInt();
        clientes.add(new Cliente(nome, idade));
    }
}
