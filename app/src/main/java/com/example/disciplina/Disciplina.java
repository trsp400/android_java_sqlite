package com.example.disciplina;

public class Disciplina {
    // Definição dos atributos
    // O atributo id não existia na versão anterior
    private long id;
    private String nome;
    private double a1, a2, a3;
    // Métodos novos, de acesso do atributo id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    // Métodos de acesso (setters & getters)
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (!nome.isEmpty()) {
            this.nome = nome;
        }
    }
    public double getA1() {
        return a1;
    }
    public void setA1(double a1) {
        if (a1 >= 0) {
            this.a1 = a1;
        }
    }
    public double getA2() {
        return a2;
    }
    public void setA2(double a2) {
        if (a2 >= 0) {
            this.a2 = a2;
        }
    }
    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        if (a3 >= 0) {
            this.a3 = a3;
        }
    }
    // Método construtor com definição dos valores-padrão
    public Disciplina() {
        nome = "Nome Disciplina";
        a1 = 0.0;
        a2 = 0.0;
        a3 = 0.0;
    }
    // Método de formatação dos dados para exibição na lista
    public String textoLista() {
        String item;
        item = getNome();
        item += "\nA1: " + String.format("%3.1f", getA1());
        item += "\tA2: " + String.format("%3.1f", getA2());
        item += "\tA3: " + String.format("%3.1f", getA3());
        return item;
    }
}