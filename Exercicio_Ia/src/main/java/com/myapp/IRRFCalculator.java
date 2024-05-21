package com.myapp;

public class IRRFCalculator {
    public static double calcularIRRF(double salarioBruto, double descontoINSS, int numeroDependentes, double totalDescontosIRRF) {
        double baseCalculo = salarioBruto - descontoINSS - (numeroDependentes * 189.59) - totalDescontosIRRF;
        double irrf = 0;

        if (baseCalculo <= 1903.98)
            irrf = 0;
        else if (baseCalculo <= 2826.65)
            irrf = (baseCalculo - 1903.98) * 0.075 - 142.80;
        else if (baseCalculo <= 3751.05)
            irrf = (baseCalculo - 2826.65) * 0.15 - 354.80;
        else if (baseCalculo <= 4664.68)
            irrf = (baseCalculo - 3751.05) * 0.225 - 636.13;
        else
            irrf = (baseCalculo - 4664.68) * 0.275 - 869.36;

        return irrf;
    }
}
