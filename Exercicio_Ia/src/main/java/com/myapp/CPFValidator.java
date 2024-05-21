package com.myapp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CPFValidator {
    private static final String API_URL = "https://api.receitaws.com.br/v1/cpf/";

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");
        if (cpf.length() != 11) return false;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + cpf))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            return jsonResponse.get("status").getAsString().equals("OK");
        } catch (Exception e) {
            System.err.println("Erro na validacao de CPF: " + e.getMessage());
            return false;
        }
    }
}
