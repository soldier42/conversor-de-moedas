package aplicacao.main;

import aplicacao.leitores.Arquivo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Debug {
    public static void main(String[] args) throws IOException {
        List<String> l = Arquivo.lerArquivoParaLista(new File("log.txt"));

        if (l.size() >= 77) {
            for (int i = 0; i < 77; i++) {
                System.out.println(l.get(i));
            }
        } else {
            for (String s : l) {
                System.out.println(s);
            }
        }
    }
}
