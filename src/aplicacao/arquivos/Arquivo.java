package aplicacao.arquivos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
    public static void escreverArquivo(File nomeDoArquivo, String conteudo) throws IOException {
        if (nomeDoArquivo.exists()) {
            String conteudoLido = lerArquivo(nomeDoArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nomeDoArquivo));
            bufferedWriter.write(conteudo + System.lineSeparator() + conteudoLido);
            bufferedWriter.close();
        } else {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nomeDoArquivo));
            bufferedWriter.write(conteudo);
            bufferedWriter.close();
        }
    }

    public static String lerArquivo(File nomeDoArquivo) throws IOException {
        if (nomeDoArquivo.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeDoArquivo));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder
                        .append(line)
                        .append(System.lineSeparator());
            }
            bufferedReader.close();

            return String.valueOf(stringBuilder).trim();
        }

        return "O arquivo não existe!";
    }

    public static JsonObject getJsonData(String nomeDoArquivo) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(new FileReader(nomeDoArquivo), JsonObject.class);
    }

    public static int getJsonSize(String nomeDoArquivo) throws IOException {
        return getJsonData(nomeDoArquivo).size();
    }

    // método feito apenas para função de histórico de conversões
    public static List<String> lerArquivoParaLista(File nomeDoArquivo) throws FileNotFoundException {
        if (nomeDoArquivo.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeDoArquivo));
            return bufferedReader.lines().toList();
        }

        // retorna uma arraylist vazia caso não exista arquivo log.txt(significa que o usuario não fez nenhuma conversão ainda)
        return new ArrayList<>();
    }
}
