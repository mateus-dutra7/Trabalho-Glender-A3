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
    private int marcados; 
    private double preco = 10.0;
    private String donoCpf;

    public CartelaBingo(int id, String cpf) {
        this.id = id;
        this.donoCpf = cpf;
        this.numeros = new ArrayList<>();
        this.marcados = 0;
        gerarNumeros();
    }

    private void gerarNumeros() {
        Random rand = new Random();
        while (numeros.size() < 5) {
            int n = rand.nextInt(50) + 1;
            if (!numeros.contains(n)) numeros.add(n);
        }
    }

    public void conferirNumero(int sorteado) {
        if (numeros.contains(sorteado)) {
            marcados++;
        }
    }

    public boolean completou() { return marcados == 5; }

    public void exibir() {
        System.out.println("Cartela ID: " + id + " | Dono CPF: " + donoCpf + " | Numeros: " + numeros);
    }

    public double getPreco() { return preco; }
    public String getDonoCpf() { return donoCpf; }
}