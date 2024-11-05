import java.io.*;

public class Arquivo {
    public static void escrita(File nomeDoArquivo, String conteudo) throws IOException {
        if (nomeDoArquivo.exists()) {
            String conteudoLido = leitura(nomeDoArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nomeDoArquivo));
            bufferedWriter.write(conteudoLido + System.lineSeparator() + conteudo);
            bufferedWriter.close();
        } else {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nomeDoArquivo));
            bufferedWriter.write(conteudo);
            bufferedWriter.close();
        }
    }

    public static String leitura(File nomeDoArquivo) throws IOException {
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
}
