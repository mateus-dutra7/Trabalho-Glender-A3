/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho.a3;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mateu
 */
public class TrabalhoA3 {
private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static ArrayList<CartelaBingo> listaCartelasContratadas = new ArrayList<>();
    private static double caixaDoBingo = 0.0;
    private static int contadorCartela = 1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        // Dados iniciais para testar rápido
        listaClientes.add(new Cliente("Joao Silva", "123", 50.0));

        do {
            System.out.println("\n--- SISTEMA DE BINGO ---");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Consultar Clientes");
            System.out.println("3 - Editar Cliente");
            System.out.println("4 - Excluir Cliente");
            System.out.println("5 - Comprar Cartela (Contratar Servico)");
            System.out.println("6 - Ver Caixa do Sistema (Gestao Financeira)");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Saldo Inicial (R$): ");
                    double saldo = scanner.nextDouble();
                    
                    listaClientes.add(new Cliente(nome, cpf, saldo));
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE CLIENTES ---");
                    if (listaClientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente c : listaClientes) {
                            System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf() + " | Saldo: R$" + c.getSaldo());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o CPF do cliente que deseja editar: ");
                    String cpfEditar = scanner.nextLine();
                    Cliente clienteEditar = buscarCliente(cpfEditar);
                    
                    if (clienteEditar != null) {
                        System.out.print("Novo Nome: ");
                        clienteEditar.setNome(scanner.nextLine());
                        System.out.println("Dados atualizados!");
                    } else {
                        System.out.println("Cliente nao encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o CPF do cliente que deseja excluir: ");
                    String cpfExcluir = scanner.nextLine();
                    Cliente clienteExcluir = buscarCliente(cpfExcluir);
                    
                    if (clienteExcluir != null) {
                        listaClientes.remove(clienteExcluir);
                        System.out.println("Cliente removido com sucesso!");
                    } else {
                        System.out.println("Cliente nao encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Digite o CPF do cliente comprador: ");
                    String cpfComprador = scanner.nextLine();
                    Cliente comprador = buscarCliente(cpfComprador);
                    
                    if (comprador != null) {
                        CartelaBingo novaCartela = new CartelaBingo(contadorCartela);
                        
                        if (comprador.descontarSaldo(novaCartela.getPreco())) {
                            listaCartelasContratadas.add(novaCartela);
                            caixaDoBingo += novaCartela.getPreco();
                            contadorCartela++;
                            System.out.println("Venda realizada com sucesso!");
                            novaCartela.exibirCartela();
                        } else {
                            System.out.println("Saldo insuficiente! A cartela custa R$" + novaCartela.getPreco());
                        }
                    } else {
                        System.out.println("Cliente nao cadastrado.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- GESTAO FINANCEIRA ---");
                    System.out.println("Total arrecadado no caixa: R$" + caixaDoBingo);
                    System.out.println("Quantidade de servicos/cartelas vendidos: " + listaCartelasContratadas.size());
                    break;

                case 7:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 7);
        
        scanner.close();
    }

    private static Cliente buscarCliente(String cpf) {
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
}