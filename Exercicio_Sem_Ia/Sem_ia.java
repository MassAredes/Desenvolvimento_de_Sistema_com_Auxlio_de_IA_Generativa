import java.io.*;
import java.util.Scanner;

public class Sem_ia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Receber informações do trabalhador
        System.out.println("Digite o nome do trabalhador:");
        String nome = scanner.nextLine();

        System.out.println("Digite o salário bruto:");
        double salarioBruto = Double.parseDouble(scanner.nextLine());

        System.out.println("Digite o desconto do INSS:");
        double descontoINSS = Double.parseDouble(scanner.nextLine());

        System.out.println("Digite o número de dependentes:");
        int numeroDependentes = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o valor total de descontos cabíveis para dedução de IRRF:");
        double totalDescontosIRRF = Double.parseDouble(scanner.nextLine());

        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();

        // Validação do CPF
        if (!validarCPF(cpf)) {
            System.out.println("CPF inválido.");
            return;
        }

        System.out.println("Digite o CEP:");
        String cep = scanner.nextLine();

        // Aqui esta simulando a obtenção do endereço pelo CEP
        String enderecoCompleto = "Endereço fictício para o CEP " + cep;

        // Calcular IRRF
        double irrf = calcularIRRF(salarioBruto, descontoINSS, numeroDependentes, totalDescontosIRRF);

        // Calcular salário líquido
        double salarioLiquido = salarioBruto - descontoINSS - irrf;

        // Exibir informações
        System.out.println("Nome: " + nome);
        System.out.println("Salário Bruto: " + salarioBruto);
        System.out.println("Desconto INSS: " + descontoINSS);
        System.out.println("IRRF: " + irrf);
        System.out.println("Salário Líquido: " + salarioLiquido);
        System.out.println("Endereço: " + enderecoCompleto);

        // Armazenar dados em arquivo de texto
        String dadosTrabalhador = nome + ";" + salarioBruto + ";" + descontoINSS + ";" + numeroDependentes + ";" + totalDescontosIRRF + ";" + cpf + ";" + cep + ";" + enderecoCompleto + ";" + salarioLiquido + "\n";

        armazenarDados(cpf, dadosTrabalhador);
    }

    static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf.length() != 11)
            return false;

        boolean todosDigitosIguais = true;
        for (int i = 1; i < 11 && todosDigitosIguais; i++) {
            if (cpf.charAt(i) != cpf.charAt(0))
                todosDigitosIguais = false;
        }

        if (todosDigitosIguais || cpf.equals("12345678909"))
            return false;

        int[] multiplicador1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicador2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        String tempCpf = cpf.substring(0, 9);
        int soma = 0;

        for (int i = 0; i < 9; i++)
            soma += Integer.parseInt(String.valueOf(tempCpf.charAt(i))) * multiplicador1[i];

        int resto = soma % 11;
        resto = resto < 2 ? 0 : 11 - resto;

        String digito = String.valueOf(resto);
        tempCpf += digito;
        soma = 0;

        for (int i = 0; i < 10; i++)
            soma += Integer.parseInt(String.valueOf(tempCpf.charAt(i))) * multiplicador2[i];

        resto = soma % 11;
        resto = resto < 2 ? 0 : 11 - resto;

        digito += String.valueOf(resto);

        return cpf.endsWith(digito);
    }

    static double calcularIRRF(double salarioBruto, double descontoINSS, int numeroDependentes, double totalDescontosIRRF) {
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

    static void armazenarDados(String cpf, String dados) {
        String caminhoArquivo = "dados_trabalhadores.txt";
        File arquivo = new File(caminhoArquivo);
        StringBuilder conteudo = new StringBuilder();

        try {
            if (arquivo.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(arquivo));
                String linha;
                boolean trabalhadorExiste = false;

                while ((linha = br.readLine()) != null) {
                    if (linha.contains(cpf)) {
                        conteudo.append(dados);
                        trabalhadorExiste = true;
                    } else {
                        conteudo.append(linha).append("\n");
                    }
                }
                br.close();

                if (!trabalhadorExiste) {
                    conteudo.append(dados);
                }
            } else {
                conteudo.append(dados);
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
            bw.write(conteudo.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}