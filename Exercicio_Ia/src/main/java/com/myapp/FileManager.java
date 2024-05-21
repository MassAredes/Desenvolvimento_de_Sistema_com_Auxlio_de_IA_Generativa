package com.myapp;

import java.io.*;

public class FileManager {
    private static final String FILE_PATH = "dados_trabalhadores.txt";

    public static void armazenarDados(Worker worker) throws IOException {
        File arquivo = new File(FILE_PATH);
        StringBuilder conteudo = new StringBuilder();

        if (arquivo.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            String linha;
            boolean trabalhadorExiste = false;

            while ((linha = br.readLine()) != null) {
                if (linha.contains(worker.getCpf())) {
                    conteudo.append(worker.toFileString()).append("\n");
                    trabalhadorExiste = true;
                } else {
                    conteudo.append(linha).append("\n");
                }
            }
            br.close();

            if (!trabalhadorExiste) {
                conteudo.append(worker.toFileString()).append("\n");
            }
        } else {
            conteudo.append(worker.toFileString()).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));
        bw.write(conteudo.toString());
        bw.close();
    }
}
