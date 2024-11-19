package aplicacao.main;

import aplicacao.consultas.Cotacao;
import aplicacao.leitores.Arquivo;
import aplicacao.modelos.Moeda;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int selectOption;
        boolean breakWhile = false;
        Moeda moedaBase = new Moeda();
        Moeda moedaFinal = new Moeda();
        Gson gson = new Gson();
        String nomeMoedaBase = "";
        String nomeMoedaFinal = "";

        System.out.print("""
        ------------------------------------------------------
        *        Bem vind@ ao Conversor de Moedas =]         *
        ------------------------------------------------------
        """);

        while (!breakWhile) {
            Thread.sleep(1000);

            System.out.printf("""
            Digite o índice da ação que deseja fazer:

            1) Converter moeda
            2) Listar as últimas conversões
            0) Sair
            ------------------------------------------------------%n
            """);

            try {
                System.out.print(">>> ");
                selectOption = input.nextInt();

                switch (selectOption) {
                    // Caso base
                    case 0:
                        breakWhile = true;
                        break;

                    // Conversão de moedas
                    case 1:
                        Thread.sleep(1000);
                        // Etapa 1
                        boolean breakWhileEtapa1 = true;
                        while (breakWhileEtapa1) {
                            try {
                                System.out.printf("""
                                        ------------------------------------------------------
                                        *                      Etapa 1                       *
                                        ------------------------------------------------------
                                        Digite o índice da moeda que deverá ser convertida:%n
                                        """);

                                // Printando as moedas contidas do arquivos currencies.json
                                JsonObject jsonObject = Arquivo.getJsonData("currencies.json");
                                for(String i:jsonObject.keySet()){
                                    System.out.printf("""
                                                    %s) %s
                                                    """.formatted(i, String.valueOf(jsonObject.get(i)).replace("\"", ""))
                                                    );
                                }

                                System.out.printf("""
                                        %n0) Listar todas as moedas que possuem suporte da API
                                        ------------------------------------------------------%n
                                        """);

                                System.out.print(">>> ");
                                int idMoedaBase = input.nextInt(); // Lê do a opção escolhida do usuário.

                                // Condicional para o usuário deseja solicitar uma moeda da API.
                                if (idMoedaBase == 0) {
                                    Cotacao.printMoedasApi();
                                    System.out.printf("""
                                            %nAgora escolha qual moeda acima deverá ser convertida:
                                            ------------------------------------------------------%n
                                            """);
                                    System.out.print(">>> ");
                                    idMoedaBase = input.nextInt();

                                    // Instanciando um Objeto Moeda com base na conversão de informações obtidas da API.
                                    moedaBase = gson.fromJson(Cotacao.solicitarCotacao(Cotacao.getCodigoApiPorId(idMoedaBase)), Moeda.class);
                                    nomeMoedaBase = moedaBase.getNomeDaMoeda();
                                    System.out.printf("%s selecionado!%n", nomeMoedaBase);
                                    breakWhileEtapa1 = false;

                                // Caso onde o usuário solicita uma moeda do arquivo JSON local.
                                } else if (idMoedaBase > 0 && idMoedaBase <= Arquivo.getJsonSize("currencies.json")) {
                                    // Instanciando um Objeto Moeda com base na conversão de informações obtidas da API.
                                    moedaBase = gson.fromJson(Cotacao.solicitarCotacao(Cotacao.getCodigoLocalPorId(idMoedaBase)), Moeda.class);
                                    nomeMoedaBase = moedaBase.getNomeDaMoeda();
                                    System.out.printf("%s selecionado!%n", nomeMoedaBase);
                                    breakWhileEtapa1 = false;

                                } else {
                                    System.out.println("[erro] Digite apenas um dos índices listados.");
                                    Thread.sleep(1000);
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("[erro] O índice deve ser um número inteiro.");
                                input.next();
                                Thread.sleep(1000);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("[erro] Digite apenas um dos índices listados.");
                                Thread.sleep(1000);
                            }
                        }

                        // Etapa 2
                        boolean breakWhileEtapa2 = true;
                        while (breakWhileEtapa2) {
                            try {
                                System.out.printf("""
                                        ------------------------------------------------------
                                        *                      Etapa 2                       *
                                        ------------------------------------------------------
                                        Agora digite para qual moeda o valor deverá convertido: %n
                                        """);

                                // Printando as moedas contidas do arquivos currencies.json
                                JsonObject jsonObject = Arquivo.getJsonData("currencies.json");
                                for(String i:jsonObject.keySet()){
                                    System.out.printf("""
                                                    %s) %s
                                                    """.formatted(i, String.valueOf(jsonObject.get(i)).replace("\"", ""))
                                    );
                                }

                                System.out.printf("""
                                        %n0) Mostrar todas as moedas que possui suporte da API
                                        ------------------------------------------------------%n
                                        """);

                                System.out.print(">>> ");
                                int idMoedaFinal = input.nextInt();

                                // Condicional para o usuário deseja solicitar uma moeda da API.
                                if (idMoedaFinal == 0) {
                                    Cotacao.printMoedasApi(); // Printando todas as moedas suportadas pela API
                                    System.out.printf("""
                                            %nAgora escolha a moeda final: %n%n
                                            ------------------------------------------------------%n
                                            """);
                                    System.out.print(">>> ");
                                    idMoedaFinal = input.nextInt();

                                    // Instanciando um Objeto Moeda com base na conversão de informações obtidas da API.
                                    moedaFinal = gson.fromJson(Cotacao.solicitarCotacao(Cotacao.getCodigoApiPorId(idMoedaFinal)), Moeda.class);
                                    nomeMoedaFinal = moedaFinal.getNomeDaMoeda();
                                    System.out.printf("%s selecionado!%n", nomeMoedaFinal);

                                    breakWhileEtapa2 = false;

                                // Caso onde o usuário solicita uma moeda do arquivo JSON local.
                                } else if (idMoedaFinal > 0 && idMoedaFinal <= Arquivo.getJsonSize("currencies.json")) {
                                    // Instanciando um Objeto Moeda com base na conversão de informações obtidas da API.
                                    moedaFinal = gson.fromJson(Cotacao.solicitarCotacao(Cotacao.getCodigoLocalPorId(idMoedaFinal)), Moeda.class);
                                    nomeMoedaFinal = moedaFinal.getNomeDaMoeda();
                                    System.out.printf("%s selecionado!%n", nomeMoedaFinal);

                                    breakWhileEtapa2 = false;
                                }
                                else {
                                    System.out.println("[erro] Digite apenas um dos índices listados.");
                                    Thread.sleep(1000);
                                }
                            } catch (InputMismatchException e) { // Exceção para caso de input diferente de um inteiro.
                                System.out.println("[erro] O índice deve ser um número inteiro.");
                                input.next();
                                Thread.sleep(1000);
                            } catch (IndexOutOfBoundsException e) { // Exceção para caso do input não estar no intervalo dos índices mostrados.
                                System.out.println("[erro] Digite apenas um dos índices listados.");
                                Thread.sleep(1000);
                            }
                        }

                        // Etapa 3
                        double valorAConverter = 0;
                        boolean breakWhileEtapa3 = true;
                        while(breakWhileEtapa3) {
                            // Tratamento de exceção para caso do input ser diferente de um número real
                            try {
                                System.out.printf("""
                                        ------------------------------------------------------
                                        *                      Etapa 3                       *
                                        ------------------------------------------------------
                                        Digite a quantia que você deseja converter:
                                        ------------------------------------------------------%n
                                        """);
                                System.out.print(">>> ");
                                valorAConverter = input.nextDouble();
                                breakWhileEtapa3 = false;
                            } catch (InputMismatchException e) {
                                System.out.println("[erro] A quantia deve ser um número real.");
                                input.next();
                                Thread.sleep(1000);
                            }
                        }


                        // Etapa 4
                        String conversaoFinal = """
                                        ------------------------------------------------------
                                        *                      Etapa 4                       *
                                        ------------------------------------------------------
                                        Conversão de %s para %s
                                        %.2f %s ===============> %.2f %s
                                        
                                        Última atualização: %s
                                        ------------------------------------------------------
                                        """.formatted(
                                                nomeMoedaBase,
                                                nomeMoedaFinal,
                                                valorAConverter,
                                                moedaBase.getCodigo(),
                                                moedaBase.compararMoedas(moedaFinal, valorAConverter),
                                                moedaFinal.getCodigo(),
                                                moedaBase.getUltimaAtualizacao()
                                                );
                        // Arquivando a conversão em um arquivo de log txt.
                        Arquivo.escreverArquivo(new File("log.txt"), conversaoFinal.substring(110));
                        System.out.println(conversaoFinal);

                        Thread.sleep(1000);
                        break;

                    case 2:
                        System.out.println("Histórico de conversão!!!!!!!!!!!!!!");
                        Arquivo.lerArquivo(new File("log.txt")).substring(60, -1);
                        break;


                    default:
                        System.out.print("""
                                [erro] Digite apenas um dos índices listados.
                                ------------------------------------------------------
                                """);
                        break;
                }
            // Exceção que cobre erros de input objetos String na variavel que armazena a opção de ação do usuário: converter moeda ou sair.
            } catch (InputMismatchException e) {
                System.out.print("""
                        [erro] O índice deve ser um número inteiro.
                        ------------------------------------------------------
                        """);
                input.next();
            // Exceção que cobre todos os outros erros que não foram tratados. IMPORTANTE tratar TODAS as exceções que não foram pensadas durante a implementação do código.
            } catch (Exception e) {
                System.out.println("O seguinte erro abaixo não foi tratado, portanto, o programa irá se encerrar.");
                System.out.println(e);
            }
        }
    }
}
