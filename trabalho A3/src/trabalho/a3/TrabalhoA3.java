/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package trabalho.a3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author mateu
 */
public class TrabalhoA3 {
private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static ArrayList<CartelaBingo> cartelasVendidas = new ArrayList<>();
    
    // GESTÃO FINANCEIRA GLOBAL E DIÁRIA
    private static double totalVendas = 0;
    private static double totalPremiosPagos = 0;
    private static double saldoDoDia = 0; // <-- NOVA VARIÁVEL ACRESCENTADA
    private static double valorPremioRodada = 50.0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        int opcao = 0;

        

        do {
            // ADICIONADO: O Saldo do Dia agora aparece em destaque no topo do Menu!
            System.out.println("\n=======================================");
            System.out.println("   SALDO ATUAL DO DIA: R$" + saldoDoDia);
            System.out.println("=======================================");
            System.out.println("--- GESTÃO DE BINGO A3 ---");
            System.out.println("1 - Gestão de Clientes (Cadastrar/Consultar/Editar/Excluir)");
            System.out.println("2 - Venda de Cartelas (Contratação)");
            System.out.println("3 - Iniciar Rodada de Bingo (Sorteio)");
            System.out.println("4 - Relatório Financeiro Detalhado");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: menuClientes(sc); break;
                case 2: venderCartela(sc); break;
                case 3: realizarRodada(); break;
                case 4: exibirFinanceiro(); break;
            }
        } while (opcao != 5);
    }

    // --- GESTÃO DE CLIENTES (CRUD) ---
    private static void menuClientes(Scanner sc) {
        System.out.println("1-Cadastrar | 2-Listar | 3-Editar | 4-Deletar");
        int sub = sc.nextInt(); sc.nextLine();
        if (sub == 1) {
            System.out.print("Nome: "); String n = sc.nextLine();
            System.out.print("CPF: "); String c = sc.nextLine();
            listaClientes.add(new Cliente(n, c, 100.0));
            System.out.println("Cliente cadastrado!");
        } else if (sub == 2) {
            for (Cliente c : listaClientes) System.out.println(c.getNome() + " - CPF: " + c.getCpf() + " - Saldo: R$" + c.getSaldo());
        } else if (sub == 3) {
            System.out.print("CPF para editar: "); String c = sc.nextLine();
            for (Cliente cl : listaClientes) if (cl.getCpf().equals(c)) { System.out.print("Novo Nome: "); cl.setNome(sc.nextLine()); }
        } else if (sub == 4) {
            System.out.print("CPF para deletar: "); String c = sc.nextLine();
            listaClientes.removeIf(cl -> cl.getCpf().equals(c));
        }
    }

    // --- GESTÃO DE VENDAS ---
    private static void venderCartela(Scanner sc) {
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                CartelaBingo nova = new CartelaBingo(cartelasVendidas.size() + 1, cpf);
                if (c.descontarSaldo(nova.getPreco())) {
                    cartelasVendidas.add(nova);
                    totalVendas += nova.getPreco();
                    saldoDoDia += nova.getPreco(); // Dinheiro entra no saldo do dia
                    System.out.println("Venda realizada!");
                    nova.exibir();
                    return;
                }
            }
        }
        System.out.println("Erro na venda. Verifique o CPF ou Saldo.");
    }

    // --- GESTÃO DE RODADAS ---
    private static void realizarRodada() {
        if (cartelasVendidas.isEmpty()) {
            System.out.println("Sem cartelas vendidas, sem jogo!");
            return;
        }
        Random r = new Random();
        ArrayList<Integer> sorteados = new ArrayList<>();
        boolean alguemGanhou = false;

        System.out.println("SORTEANDO NÚMEROS...");
        while (!alguemGanhou) {
            int num = r.nextInt(50) + 1;
            if (!sorteados.contains(num)) {
                sorteados.add(num);
                for (CartelaBingo cartela : cartelasVendidas) {
                    cartela.conferirNumero(num);
                    if (cartela.completou()) {
                        System.out.println("\n========================");
                        System.out.println("BINGO! Ganhador CPF: " + cartela.getDonoCpf());
                        System.out.println("========================");
                        pagarPremio(cartela.getDonoCpf());
                        alguemGanhou = true;
                        break;
                    }
                }
            }
        }
        cartelasVendidas.clear(); 
    }

    private static void pagarPremio(String cpf) {
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                c.adicionarSaldo(valorPremioRodada);
                totalPremiosPagos += valorPremioRodada;
                saldoDoDia -= valorPremioRodada; // O prêmio sai do saldo do dia
                System.out.println("Prêmio de R$" + valorPremioRodada + " pago ao cliente!");
            }
        }
    }

    // --- GESTÃO FINANCEIRA COMPLETA ---
    private static void exibirFinanceiro() {
        System.out.println("\n--- RELATÓRIO FINANCEIRO ACUMULADO ---");
        System.out.println("Total de Receita Histórica: R$" + totalVendas);
        System.out.println("Total de Prêmios Pagos Histórico: R$" + totalPremiosPagos);
        System.out.println("Lucro Líquido Geral: R$" + (totalVendas - totalPremiosPagos));
        System.out.println("---------------------------------------");
        System.out.println("SALDO ATUAL DO DIA: R$" + saldoDoDia); 
        System.out.println("---------------------------------------");
    }
}