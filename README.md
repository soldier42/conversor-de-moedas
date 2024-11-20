<h1 align="center">Challenge Conversor de Moedas</h1>

<p align="center">
<img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=NAO%20FINALIZADO&color=FF0000&style=for-the-badge"/>
</p>

# Índice 

* [Descrição do Projeto](#descrição-do-projeto)
* [Requisitos](#requisitos)
* [Funcionalidade](#funcionalidade)
* [Demonstração da Aplicação](#demonstração-da-aplicação)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Acesso ao Projeto](#acesso-ao-projeto)

# Descrição do Projeto
Este projeto é um desafio proposto pela equipe da Alura em parceria com a Oracle One. O objetivo é desenvolver um **conversor de moedas dinâmico**, que possibilita a conversão entre diferentes moedas utilizando dados atualizados de uma API externa.

O projeto também tem em vista colocar em prática parte do conteúdo aprendido durante a formação de **Java e Orientação a Objetos**.

# Requisitos
## Gson v2.11.0
Gson é uma biblioteca do Java que pode ser usava para converter Objetos Java em formatos JSON e vice-versa.
- A versão utilizada neste projeto foi a **gson-2.11.0** e pode ser encontrado no site da [**MVN Repository**](https://mvnrepository.com/artifact/com.google.code.gson/gson) ou no [**repositório**](https://github.com/google/gson?tab=readme-ov-file) Github.

## JDK v17.0.12
O JDK é um ambiente de desenvolvimento para construção de aplicações que usa a linguagem Java.
- A versão que foi utilizada neste projeto foi a **jdk-17.0.12** e pode ser encontrado no site da [**Oracle**](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

## Intellij Community Edition (Opcional)
O projeto foi feito inteiramente no [**Intellij Community Edition IDE**](https://www.jetbrains.com/pt-br/idea/download/?section=windows), mas seu é totalmente opcional.

# Funcionalidades
- **Conversão dinâmica de moedas**: Com o 
uso da [**Exchange Rate - API**](https://www.exchangerate-api.com/) para base de dados, as informações sobre as cotações são frequentemente atualizadas;
- **Log que armazena histórico de conversões**: Todas as conversões são armazenada em um arquivo *txt* que pode ser lido e consultado dentro do próprio programa.

- **Moedas Suportadas**: Por padrão, há oito moedas disponíveis para conversão, mas o usuário pode extender leque de opções, trazendo todas as moedas que possuem o suporte da API.

    É possível conferir as moedas que tem suporte através da [**documentação**](https://www.exchangerate-api.com/docs/supported-currencies) da API.

# Demonstração da Aplicação

## Observações
Para bom funcionamento do código, é recomendado que:
- Utilize-se apenas valores **reais** nas **conversões** entres moedas;
- Utilize-se apenas valores **inteiros** nas **seleções de ações**;

O código possui alguns tratamentos de erros, mas não é garantido que ele funcione como deva em qualquer situação.

Ademais, basta seguir as instruções do terminal.

# Tecnologias Utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

# Uso da Aplicação
1. Clone este repositório:
```
git clone https://github.com/soldier42/conversor-de-moedas
```

2. Certifique de que todas os [requisitos](#requisitos) estejam instalados.

3. Execute e compile o código.
