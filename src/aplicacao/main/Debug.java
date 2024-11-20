package aplicacao.main;

import aplicacao.arquivos.Arquivo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Debug {
    public static void main(String[] args) throws IOException {
        System.out.println(Arquivo.lerArquivo(new File("askdaskd")));
    }
}
