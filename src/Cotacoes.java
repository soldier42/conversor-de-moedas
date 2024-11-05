import com.google.gson.*;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Cotacoes {
    public static String getNomeDaMoedaJsonLocal(int idMoeda) throws IOException {
        return String.valueOf(getJsonLocal().get(String.valueOf(idMoeda))).replace("\"", "");
    }

    public static String getNomeDaMoedaAPI(int idMoeda) throws IOException, InterruptedException {
        return String.valueOf(getMoedasApi().get(idMoeda - 1));
    }

    public static String getLastUpdate(String AbrDaMoeda) throws IOException, InterruptedException {
        Gson gson = new Gson();
        return String.valueOf(gson.fromJson(solicitarCotacao(AbrDaMoeda), JsonObject.class).get("time_last_update_utc"));
    }

    public static String getAbrDaMoeda(String nomeDaMoeda) throws IOException {
        return nomeDaMoeda.substring(1, 4);
    }

    public static void printJsonLocal() throws IOException {
        JsonObject jsonObject = getJsonLocal();
        for(String i:jsonObject.keySet()){
            System.out.printf("""
                    %s) %s
                    """.formatted(i, String.valueOf(jsonObject.get(i)).replace("\"", ""))
            );
        }
    }

    public static JsonObject getJsonLocal() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(new FileReader("currencies.json"), JsonObject.class);
    }

    public static Double compararMoedas(String AbrMoedaBase, String AbrMoedaFinal, double valor) throws IOException, InterruptedException {
        Gson gson = new Gson();
        JsonObject generalData = gson.fromJson(solicitarCotacao(AbrMoedaBase), JsonObject.class);
        JsonObject taxasDeConversao = (JsonObject) generalData.get("conversion_rates");

        return valor * taxasDeConversao.get(AbrMoedaFinal).getAsDouble();
    }

    public static void printMoedasApi() throws IOException, InterruptedException {
        int cont = 1;
        for (ArrayList a: getMoedasApi()) {
            System.out.printf("%d) %s%n", cont, a);
            cont++;
        }
    }

    public static ArrayList<ArrayList> getMoedasApi() throws IOException, InterruptedException {
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

