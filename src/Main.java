import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int selectOption;
        boolean breakWhile = false;
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

            1) Converter Moeda
            0) Sair
            ------------------------------------------------------%n
            """);

            try {
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
                                Cotacoes.printJsonLocal(); // Printando as moedas contidas do arquivos currencies.json
                                System.out.printf("""
                                        %n0) Listar todas as moedas que possuem suporte da API
                                        ------------------------------------------------------%n
                                        """);

                                int idMoedaBase = input.nextInt(); // Lê do a opção escolhida do usuário.

                                // Caso onde o usuário solcita uma moeda do arquivo JSON local.
                                if (idMoedaBase > 0 && idMoedaBase <= Cotacoes.getJsonLocal().size()) {
                                    nomeMoedaBase = Cotacoes.getNomeDaMoedaJsonLocal(idMoedaBase);
                                    System.out.printf("%s selecionado!%n", nomeMoedaBase);

                                    breakWhileEtapa1 = false;
                                }

                                // Caso onde o usuário deseja solicitar o nome de uma moeda na API.
                                else if (idMoedaBase == 0) { // Condicionais para caso o usuário queira ver todas as moedas fornecidas pela API.
                                    Cotacoes.printMoedasApi();
                                    System.out.printf("""
                                            %nAgora escolha qual moeda acima deverá ser convertida:
                                            ------------------------------------------------------%n
                                            """);
                                    idMoedaBase = input.nextInt();
                                    nomeMoedaBase = Cotacoes.getNomeDaMoedaAPI(idMoedaBase);
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

                        Thread.sleep(1000);

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
                                Cotacoes.printJsonLocal(); // Printando as moedas contidas do arquivos currencies.json
                                System.out.printf("""
                                        %n0) Mostrar todas as moedas que possui suporte da API
                                        ------------------------------------------------------%n
                                        """);

                                int idMoedaFinal = input.nextInt();

                                if (idMoedaFinal > 0 && idMoedaFinal <= Cotacoes.getJsonLocal().size()) {
                                    nomeMoedaFinal = Cotacoes.getNomeDaMoedaJsonLocal(idMoedaFinal);
                                    System.out.printf("%s selecionado!%n", nomeMoedaFinal);

                                    breakWhileEtapa2 = false;
                                } else if (idMoedaFinal == 0) {
                                    Cotacoes.printMoedasApi(); // Printando todas as moedas suportadas pela API
                                    System.out.printf("""
                                            %nAgora escolha a moeda final: %n%n
                                            ------------------------------------------------------%n
                                            """);
                                    idMoedaFinal = input.nextInt();
                                    nomeMoedaFinal = Cotacoes.getNomeDaMoedaAPI(idMoedaFinal);
                                    System.out.printf("%s selecionado!%n", nomeMoedaFinal);

                                    breakWhileEtapa2 = false;
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

                        Thread.sleep(1000);

                        // Etapa 3
                        double valorAConverter = 0;
                        boolean breakWhileEtapa3 = true;
                        while(breakWhileEtapa3) {
                            try {
                                System.out.printf("""
                                        ------------------------------------------------------
                                        *                      Etapa 3                       *
                                        ------------------------------------------------------
                                        Digite a quantia que você deseja converter:
                                        ------------------------------------------------------%n
                                        """);
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
                                                Cotacoes.getAbrDaMoeda(nomeMoedaBase),
                                                Cotacoes.compararMoedas(
                                                        Cotacoes.getAbrDaMoeda(nomeMoedaBase),
                                                        Cotacoes.getAbrDaMoeda(nomeMoedaFinal),
                                                        valorAConverter),
                                                Cotacoes.getAbrDaMoeda(nomeMoedaFinal),
                                                Cotacoes.getLastUpdate(Cotacoes.getAbrDaMoeda(nomeMoedaFinal))
                                                );
                        // Arquivando a conversão em um arquivo de log txt.
                        Arquivo.escrita(new File("log.txt"), conversaoFinal.substring(110));
                        System.out.println(conversaoFinal);

                        Thread.sleep(1000);
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
            }
        }
    }
}
