package com.myapp;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do trabalhador:");
        String nome = scanner.nextLine();

        System.out.println("Digite o salario bruto:");
        double salarioBruto = Double.parseDouble(scanner.nextLine());

        System.out.println("Digite o desconto do INSS:");
        double descontoINSS = Double.parseDouble(scanner.nextLine());

        System.out.println("Digite o numero de dependentes:");
        int numeroDependentes = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o valor total de descontos cabiveis para deducao de IRRF:");
        double totalDescontosIRRF = Double.parseDouble(scanner.nextLine());

        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();

        if (!CPFValidator.validarCPF(cpf)) {
            System.out.println("CPF invalido.");
            return;
        }

        System.out.println("Digite o CEP:");
        String cep = scanner.nextLine();

        String enderecoCompleto = AddressService.obterEndereco(cep);
        if (enderecoCompleto == null) {
            System.out.println("CEP inválido.");
            return;
        }

        double irrf = IRRFCalculator.calcularIRRF(salarioBruto, descontoINSS, numeroDependentes, totalDescontosIRRF);
        double salarioLiquido = salarioBruto - descontoINSS - irrf;

        Worker worker = new Worker(nome, salarioBruto, descontoINSS, numeroDependentes, totalDescontosIRRF, cpf, cep, enderecoCompleto, salarioLiquido);

        System.out.println(worker);

        try {
            FileManager.armazenarDados(worker);
        } catch (IOException e) {
            System.err.println("Erro ao armazenar dados: " + e.getMessage());
        }
    }
}
