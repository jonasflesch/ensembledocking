# Ensemble Docking
Trabalho de conclusão do aluno Jonas Flesch

## Instruções para importação
Este é um projeto Maven. Sempre importe como tal na sua IDE. Passo a passo para importação:

### 1) Instalação das ferramentas de Bioinformática
A aplicação se integra com várias ferramentas, a maioria delas instaláveis através do gerenciados de pacotes da distribuição:
- Gromacs 4.6.5
- Autodock 4.2.5.1
- MGLTools 1.5.6
- Pymol 1.7

### 2) Importar o código fonte
Para importar o código fonte, será necessário ter um cliente git instalado.
Para importar à partir do cliente linha de comando, execute o seguinte comando dentro da pasta em que deseja realizar a importação:
```
git clone https://github.com/jonasflesch/ensembledocking.git
```

### 3) Importar para a IDE de Desenvolvimento
#### 3.1) Eclipse
A sua versão do Eclipse deve conter o plugin do Maven e de desenvolvimento Web. Para isso, ao realizar o download do site selecione a opção compatível com Java EE.
Após abrir o Eclipse Java EE, importe o projeto como um projeto Maven:

File -> Import -> Maven -> Existing Maven Projects -> Busque o diretório dos fontes criado no passo 2.

#### 3.2) IntelliJ
Para abrir o projeto no IntelliJ, basta ir em Open Project e buscar o diretório onde o projeto foi importado no passo 2.

### 4) Rodar a aplicação
A aplicação foi desenvolvida utilizando o framework Spring Boot, fazendo uso do servidor de aplicação embarcado Jetty. Desta maneira, para rodar a aplicação basta rodar a classe Application, pois ela tem um método main que irá subir a aplicação.

A porta padrão é a 8080, então após subido o servidor basta acessá-la pela url abaixo:

http://localhost:8080

## Ambiente operacional
A aplicação foi desenvolvida utilizando:
- Ubuntu 14.04 x64
- Java 1.8
- Maven 3.0.5
- IntelliJ IDEA 14
- Gromacs 4.6.5
- Autodock 4.2.5.1
- MGLTools 1.5.6
- Pymol 1.7
