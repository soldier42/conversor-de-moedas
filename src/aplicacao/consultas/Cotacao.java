package aplicacao.consultas;

import aplicacao.leitores.Arquivo;
import com.google.gson.*;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Cotacao {
    // Métodos
    public static String getCodigoApiPorId(int id) throws IOException, InterruptedException {
        return String.valueOf(getMoedasApi().get(id - 1).get(0));
    }

    public static String getCodigoLocalPorId(int id) throws IOException {
        return String.valueOf(Arquivo.getJsonData("currencies.json").get(String.valueOf(id))).substring(2, 5);
    }

    public static ArrayList<ArrayList<String>> getMoedasApi() throws IOException, InterruptedException {
        String apiKey = "a680026a55c17a0e0eec7a75";
        String link = "https://v6.exchangerate-api.com/v6/%s/codes".formatted(apiKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        return gson.fromJson(jsonObject.get("supported_codes"), ArrayList.class);
    }

    public static void printMoedasApi() throws IOException, InterruptedException {
        int cont = 1;
        for (ArrayList<String> a: getMoedasApi()) {
            System.out.printf("%d) %s%n", cont, a);
            cont++;
        }
    }

    public static String solicitarCotacao(String siglaDaMoeda) throws IOException, InterruptedException {
        String apiKey = "a680026a55c17a0e0eec7a75";
        String link = "https://v6.exchangerate-api.com/v6/%s/latest/%s".formatted(apiKey, siglaDaMoeda);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

