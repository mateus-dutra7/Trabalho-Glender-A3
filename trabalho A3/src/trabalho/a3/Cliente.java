/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabalho.a3;

/**
 *
 * @author mateu
 */
public class Cliente {
 private String nome;
    private String cpf;
    private double saldo;

   public Cliente(String nome, String cpf, double saldoInicial) {
    this.nome = nome;
    this.cpf = cpf;
    this.saldo = saldoInicial;
}
    // Métodos para gestão financeira do cliente
    public void adicionarSaldo(double valor) {
        this.saldo += valor;
    }

    public boolean descontarSaldo(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public double getSaldo() { return saldo; }
}
