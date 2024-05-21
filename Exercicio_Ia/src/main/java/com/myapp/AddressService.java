package com.myapp;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AddressService {
    private static final String API_URL = "https://viacep.com.br/ws/";

    public static String obterEndereco(String cep) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + cep + "/json"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (jsonResponse.has("erro")) {
                return null;
            }

            return jsonResponse.get("logradouro").getAsString() + ", " +
                    jsonResponse.get("bairro").getAsString() + ", " +
                    jsonResponse.get("localidade").getAsString() + ", " +
                    jsonResponse.get("uf").getAsString();
        } catch (Exception e) {
            System.err.println("Erro na obtencao de endereço: " + e.getMessage());
            return null;
        }
    }
}
