# InvestAI RA3 - Sistema Desktop em JavaFX

## Sobre o projeto

O **InvestAI RA3** é uma aplicação desktop desenvolvida em **Java com JavaFX**, baseada no projeto **InvestAI** da disciplina de Experiência Criativa.

O InvestAI original é um sistema voltado para organização financeira pessoal, permitindo o controle de ganhos, despesas, metas, categorias, orçamento e sugestões de economia. Para a avaliação RA3, o projeto foi adaptado para uma aplicação desktop com foco na aplicação dos conceitos de **Programação Orientada a Objetos** e desenvolvimento de **CRUDs completos**.

O sistema permite cadastrar, consultar, atualizar e excluir informações financeiras relacionadas ao usuário, simulando um ambiente de controle financeiro pessoal.

---

## Objetivo da RA3

O objetivo deste projeto é consolidar os principais conceitos de Orientação a Objetos utilizando Java, como:

* Classes e objetos;
* Encapsulamento;
* Herança de estrutura entre telas;
* Polimorfismo através de componentes genéricos;
* Tratamento de exceções;
* Persistência de dados em arquivos;
* Separação entre classes de domínio, persistência e interface gráfica;
* Desenvolvimento de interface gráfica com JavaFX.

---

## Especificações atendidas

O projeto foi desenvolvido seguindo as orientações da avaliação RA3:

* Aplicação desktop em Java;
* Interface gráfica utilizando JavaFX;
* Não utiliza FXML;
* Não utiliza SceneBuilder;
* Componentes gráficos criados e configurados diretamente pelo código;
* Cada CRUD possui inserção, consulta, atualização e exclusão;
* Cada classe Model possui pelo menos 3 atributos;
* Persistência de dados feita em arquivos `.dat`;
* Uso de tratamento de exceções;
* Classes de domínio separadas das classes de interface;
* Todos os CRUDs integrados em um único programa;
* Tela principal com navegação entre os módulos por abas.

---

## Tecnologias utilizadas

* Java 17+
* JavaFX
* Maven
* Persistência em arquivos
* Programação Orientada a Objetos

---

## Estrutura do projeto

```txt
InvestAI_RA3_JavaFX
├── pom.xml
├── README.md
├── docs
│   ├── DOCUMENTACAO_CLASSES.md
│   └── DISTRIBUICAO_CRUDS_POR_ALUNO.md
└── src
    └── main
        └── java
            └── br
                └── com
                    └── investai
                        ├── App.java
                        ├── model
                        ├── repository
                        └── ui
```

---

## Organização das pastas

### `model`

Contém as classes de domínio do sistema, ou seja, as classes que representam os dados principais da aplicação.

Exemplos:

* `Usuario`
* `Ganho`
* `Despesa`
* `Categoria`
* `MetaFinanceira`

Essas classes representam as entidades do sistema e possuem atributos, construtores, getters, setters e métodos auxiliares.

---

### `repository`

Contém as classes responsáveis pela persistência dos dados em arquivos.

O projeto utiliza uma estrutura genérica de repositório para salvar, listar, atualizar e excluir os objetos.

A persistência é feita em arquivos `.dat`, atendendo à exigência da RA3 de armazenamento em arquivo.

---

### `ui`

Contém as classes responsáveis pela interface gráfica do sistema.

As telas foram criadas usando JavaFX diretamente por código, sem uso de FXML ou SceneBuilder.

---

### `App.java`

Classe principal da aplicação.

É responsável por iniciar o JavaFX e carregar a janela principal do sistema com os módulos de CRUD.

---

## CRUDs implementados

O sistema possui 10 CRUDs completos:

| Nº | Classe               | Finalidade                                       |
| -- | -------------------- | ------------------------------------------------ |
| 1  | `Usuario`            | Cadastro dos usuários do sistema                 |
| 2  | `PerfilFinanceiro`   | Informações financeiras gerais do usuário        |
| 3  | `Categoria`          | Organização de ganhos e despesas por categoria   |
| 4  | `Ganho`              | Registro de entradas financeiras                 |
| 5  | `Despesa`            | Registro de saídas financeiras                   |
| 6  | `OrcamentoCategoria` | Controle de orçamento por categoria              |
| 7  | `MetaFinanceira`     | Cadastro de metas financeiras                    |
| 8  | `Aporte`             | Registro de aportes em metas                     |
| 9  | `NoticiaFinanceira`  | Cadastro de notícias ou conteúdos financeiros    |
| 10 | `SugestaoEconomia`   | Cadastro de sugestões de economia para o usuário |

Cada CRUD permite:

* Inserir registros;
* Consultar registros cadastrados;
* Atualizar registros existentes;
* Excluir registros;
* Validar campos obrigatórios;
* Tratar erros de preenchimento;
* Persistir os dados em arquivo.

---

## Divisão dos CRUDs por aluno

A equipe possui 5 alunos, sendo cada aluno responsável por 2 classes Model e seus respectivos CRUDs.

| Aluno   | CRUD 1              | CRUD 2               | Área                               |
| ------- | ------------------- | -------------------- | ---------------------------------- |
| Aluno 1 | `Usuario`           | `PerfilFinanceiro`   | Cadastro e perfil do usuário       |
| Aluno 2 | `Ganho`             | `Despesa`            | Controle financeiro principal      |
| Aluno 3 | `Categoria`         | `OrcamentoCategoria` | Organização financeira             |
| Aluno 4 | `MetaFinanceira`    | `Aporte`             | Metas e evolução financeira        |
| Aluno 5 | `NoticiaFinanceira` | `SugestaoEconomia`   | Conteúdo e inteligência financeira |

---

## Descrição dos módulos

### Usuário

Responsável pelo cadastro dos usuários do sistema.

Exemplo de atributos:

* Nome;
* E-mail;
* Cidade.

Esse módulo representa a base do sistema, pois as informações financeiras pertencem a um usuário.

---

### Perfil Financeiro

Responsável por armazenar informações gerais sobre o perfil financeiro do usuário.

Exemplo de atributos:

* Renda mensal;
* Objetivo financeiro;
* Perfil de risco.

Esse módulo ajuda a contextualizar a situação financeira do usuário.

---

### Categoria

Responsável por organizar ganhos e despesas.

Exemplo de atributos:

* Nome;
* Tipo;
* Descrição.

As categorias permitem classificar melhor os registros financeiros.

---

### Ganho

Responsável pelo cadastro das entradas financeiras do usuário.

Exemplo de atributos:

* Descrição;
* Valor;
* Data;
* Categoria.

Esse módulo representa salários, vendas, rendas extras e outras entradas.

---

### Despesa

Responsável pelo cadastro das saídas financeiras.

Exemplo de atributos:

* Descrição;
* Valor;
* Data;
* Categoria.

Esse módulo representa gastos fixos, variáveis, compras, contas e outras despesas.

---

### Orçamento por Categoria

Responsável por definir limites de gastos por categoria.

Exemplo de atributos:

* Categoria;
* Valor limite;
* Mês de referência.

Esse módulo permite acompanhar se os gastos estão dentro do orçamento planejado.

---

### Meta Financeira

Responsável pelo cadastro de objetivos financeiros.

Exemplo de atributos:

* Nome da meta;
* Valor alvo;
* Prazo.

Esse módulo permite controlar objetivos como reserva de emergência, compra de produtos ou investimentos.

---

### Aporte

Responsável pelo registro de valores adicionados às metas financeiras.

Exemplo de atributos:

* Meta relacionada;
* Valor do aporte;
* Data.

Esse módulo permite acompanhar a evolução das metas.

---

### Notícia Financeira

Responsável pelo cadastro de conteúdos ou notícias relacionadas a finanças.

Exemplo de atributos:

* Título;
* Fonte;
* Data;
* Resumo.

Esse módulo representa a parte informativa do InvestAI.

---

### Sugestão de Economia

Responsável pelo cadastro de dicas e recomendações financeiras.

Exemplo de atributos:

* Título;
* Descrição;
* Categoria relacionada.

Esse módulo representa sugestões que poderiam ser usadas para orientar o usuário a economizar melhor.

---

## Como executar o projeto

### Pré-requisitos

Antes de executar, é necessário ter instalado:

* JDK 17 ou superior;
* Maven configurado no PATH.

Para verificar se o Java está instalado, execute no CMD:

```bat
java -version
```

Para verificar se o Maven está instalado, execute:

```bat
mvn -version
```

---

## Executando pelo CMD

Acesse a pasta onde está o arquivo `pom.xml`.

Exemplo:

```bat
cd /d "C:\apache-maven-3.9.16\InvestAI_RA3_JavaFX_10_CRUDs\InvestAI_RA3_JavaFX"
```

Depois execute:

```bat
mvn clean javafx:run
```

Se tudo estiver correto, a aplicação JavaFX será aberta.

---

## Observação sobre a pasta correta

O Maven precisa ser executado na pasta onde está o arquivo `pom.xml`.

Caso apareça o erro:

```txt
The goal you specified requires a project to execute but there is no POM in this directory
```

significa que o comando foi executado na pasta errada.

Nesse caso, entre na subpasta correta do projeto e rode novamente:

```bat
cd InvestAI_RA3_JavaFX
mvn clean javafx:run
```

---

## Persistência dos dados

Os dados cadastrados no sistema são armazenados em arquivos `.dat`.

Isso permite que os registros continuem salvos mesmo após fechar a aplicação.

A persistência em arquivo foi utilizada para atender à especificação da RA3.

---

## Tratamento de exceções

O projeto utiliza tratamento de exceções para evitar falhas durante o uso do sistema.

Exemplos de situações tratadas:

* Campos obrigatórios vazios;
* Valores numéricos inválidos;
* Erros ao salvar dados em arquivo;
* Erros ao carregar dados salvos;
* Tentativa de atualizar ou excluir item não selecionado.

---

## Relação com o projeto InvestAI original

O projeto original InvestAI era voltado para controle financeiro pessoal, incluindo funcionalidades como:

* Cadastro de ganhos;
* Cadastro de despesas;
* Visualização de saldo;
* Organização por categorias;
* Metas financeiras;
* Planejamento financeiro;
* Sugestões de economia;
* Informações financeiras.

A versão RA3 adapta essa ideia para uma aplicação desktop em JavaFX, mantendo o contexto financeiro e criando CRUDs compatíveis com a proposta do professor.

---

## Regras importantes da RA3 consideradas

Durante o desenvolvimento, foram consideradas as seguintes regras:

* Cada aluno deve implementar pelo menos 2 classes Model;
* Cada classe deve possuir CRUD completo;
* Cada classe deve ter pelo menos 3 atributos;
* O sistema deve usar JavaFX;
* A interface deve ser criada por código;
* Não é permitido usar FXML;
* Não é permitido usar SceneBuilder;
* Os dados devem ser persistidos em arquivos;
* Deve haver tratamento de exceções;
* O projeto deve ser entregue em um único programa funcional.

---

## Possíveis melhorias futuras

Algumas melhorias que poderiam ser adicionadas futuramente:

* Relatórios gráficos de ganhos e despesas;
* Filtros por período;
* Login de usuário;
* Vinculação real entre usuário e registros financeiros;
* Dashboard com saldo total;
* Cálculo automático de progresso das metas;
* Exportação de dados;
* Melhorias visuais na interface;
* Integração com banco de dados;
* Integração com inteligência artificial para recomendações financeiras.

---

## Conclusão

O InvestAI RA3 foi desenvolvido como uma aplicação desktop em JavaFX, com foco na aplicação prática dos conceitos de Programação Orientada a Objetos.

O sistema mantém relação direta com a proposta original do InvestAI, adaptando suas principais funcionalidades para um conjunto de CRUDs organizados, funcionais e persistidos em arquivo.

O projeto atende às especificações da avaliação RA3, permitindo que cada aluno apresente suas classes, explique sua implementação e demonstre o funcionamento da aplicação.
