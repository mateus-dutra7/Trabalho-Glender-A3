/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.a3;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author mateu
 */
public class CartelaBingo {
    private int id;
    private ArrayList<Integer> numeros;
    private double preco;

    public CartelaBingo(int id) {
        this.id = id;
        this.preco = 10.0; // Preço fixo da cartela/serviço de bingo
        this.numeros = new ArrayList<>();
        gerarNumerosAleatorios();
    }

    private void gerarNumerosAleatorios() {
        Random rand = new Random();
        while (numeros.size() < 5) { // Cartela simples de 5 números para iniciantes
            int num = rand.nextInt(50) + 1; // Números de 1 a 50
            if (!numeros.contains(num)) {
                numeros.add(num);
            }
        }
    }

    public void exibirCartela() {
        System.out.print("Cartela ID " + id + " -> Números: ");
        for (int num : numeros) {
            System.out.print("[" + num + "] ");
        }
        System.out.println();
    }

    // Getters
    public int getId() { return id; }
    public double getPreco() { return preco; }
    public ArrayList<Integer> getNumeros() { return numeros; }
}
