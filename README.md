# Guia de Instalação e Testes

Este guia fornece instruções sobre como instalar as dependências do Maven, executar os testes unitários, gerar relatórios de cobertura usando Jacoco e Pitest para o projeto XYZ.

## Pré-requisitos
- Java Development Kit (JDK) 8 ou superior instalado
- Maven instalado (versão 3.x)

## Instalação das Dependências do Maven
Para instalar as dependências do Maven, execute o seguinte comando no diretório raiz do projeto:

``` bash
mvn clean install
```
Este comando baixará todas as dependências definidas no arquivo pom.xml e as instalará localmente.

## Execução dos Testes Unitários
Para executar os testes unitários, utilize o seguinte comando Maven:

``` bash
mvn test
```

## Geração de Relatórios de Cobertura usando Jacoco
Para gerar relatórios de cobertura usando Jacoco, execute:

``` bash
mvn clean test jacoco:report
```
Os relatórios de cobertura serão gerados no diretório target/site/jacoco.

### Link para o Relatório de Cobertura do Jacoco:
[Cobertura de Código](./target/site/jacoco/index.html)

## Execução de Testes com Pitest
Para executar testes com Pitest (mutation testing), utilize o seguinte comando:

``` bash
mvn clean test org.pitest:pitest-maven:mutationCoverage
```

Os relatórios do Pitest serão gerados no diretório target/pit-reports.

### Link para o Relatório de Testes do Pitest:
[Cobertura de Teste Mutante](./target/pit-reports/index.html)

## Observações
- Certifique-se de que o JDK e o Maven estão configurados corretamente no seu sistema.
- Certifique-se de verificar os relatórios gerados nos diretórios mencionados acima para obter detalhes sobre a cobertura de código e os resultados dos testes de mutação.
- O Pitest pode levar um tempo significativo para ser concluído, dependendo do tamanho do seu projeto e do número de mutações geradas.

Este guia oferece uma visão geral básica. Certifique-se de consultar a documentação oficial do Maven, Jacoco e Pitest para obter informações mais detalhadas sobre configurações e personalizações adicionais.