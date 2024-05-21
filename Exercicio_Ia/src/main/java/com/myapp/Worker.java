package com.myapp;

public class Worker {
    private String nome;
    private double salarioBruto;
    private double descontoINSS;
    private int numeroDependentes;
    private double totalDescontosIRRF;
    private String cpf;
    private String cep;
    private String enderecoCompleto;
    private double salarioLiquido;

    public Worker(String nome, double salarioBruto, double descontoINSS, int numeroDependentes,
                  double totalDescontosIRRF, String cpf, String cep, String enderecoCompleto, double salarioLiquido) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
        this.descontoINSS = descontoINSS;
        this.numeroDependentes = numeroDependentes;
        this.totalDescontosIRRF = totalDescontosIRRF;
        this.cpf = cpf;
        this.cep = cep;
        this.enderecoCompleto = enderecoCompleto;
        this.salarioLiquido = salarioLiquido;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Salário Bruto: " + salarioBruto + "\n" +
               "Desconto INSS: " + descontoINSS + "\n" +
               "IRRF: " + IRRFCalculator.calcularIRRF(salarioBruto, descontoINSS, numeroDependentes, totalDescontosIRRF) + "\n" +
               "Salário Líquido: " + salarioLiquido + "\n" +
               "Endereço: " + enderecoCompleto;
    }

    public String toFileString() {
        return nome + ";" + salarioBruto + ";" + descontoINSS + ";" + numeroDependentes + ";" + totalDescontosIRRF + ";" + cpf + ";" + cep + ";" + enderecoCompleto + ";" + salarioLiquido;
    }
}
