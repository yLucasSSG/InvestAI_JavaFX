# InvestAI RA3 - 10 CRUDs em JavaFX

Este projeto é uma versão desktop em JavaFX baseada no projeto **InvestAI** de Experiência Criativa. A proposta transforma as principais entidades do sistema web original em telas desktop com CRUD completo.

## Requisitos atendidos da RA3

- Aplicação desktop Java usando JavaFX.
- 10 classes Model com CRUD completo.
- Cada classe possui pelo menos 3 atributos.
- Interface gráfica criada apenas por código Java.
- Não usa FXML, SceneBuilder nem FXMLLoader.
- Persistência em arquivos `.dat`, dentro da pasta `data/` criada automaticamente na execução.
- Tratamento de exceções para campos obrigatórios, números, valores monetários e datas.
- Datas no formato brasileiro `DD/MM/AAAA`.
- Classes de domínio separadas das classes de interface.

## Como executar

Pré-requisitos:

- JDK 17 ou superior.
- Maven instalado.

Comandos:

```bash
cd InvestAI_RA3_JavaFX
mvn clean javafx:run
```

## Os 10 CRUDs implementados

| Nº | Classe Model | Relação com o InvestAI original | Arquivo de persistência |
|---:|---|---|---|
| 1 | `Usuario` | Cadastro, login e perfil do usuário | `data/usuarios.dat` |
| 2 | `PerfilFinanceiro` | Perfil financeiro usado para análise e sugestões | `data/perfis_financeiros.dat` |
| 3 | `Categoria` | Categorias de ganhos e despesas | `data/categorias.dat` |
| 4 | `Ganho` | Controle de entradas financeiras | `data/ganhos.dat` |
| 5 | `Despesa` | Controle de saídas financeiras | `data/despesas.dat` |
| 6 | `OrcamentoCategoria` | Limite mensal por categoria | `data/orcamentos.dat` |
| 7 | `MetaFinanceira` | Metas financeiras do usuário | `data/metas.dat` |
| 8 | `Aporte` | Depósitos feitos em metas | `data/aportes.dat` |
| 9 | `NoticiaFinanceira` | Notícias financeiras exibidas ao usuário | `data/noticias.dat` |
| 10 | `SugestaoEconomia` | Sugestões geradas/registradas para economia | `data/sugestoes.dat` |

## Observações para apresentação/autoria

A tela principal usa abas, uma para cada CRUD. Em cada aba existe:

- Tabela para consulta/listagem.
- Formulário de inclusão.
- Seleção de item da tabela para alteração.
- Botão de exclusão com confirmação.
- Botão de recarregar dados do arquivo.

A persistência é feita pela classe genérica `FileRepository<T>`, usando `ObjectOutputStream` e `ObjectInputStream`. Todas as Models implementam `Identificavel`, e as que precisam de validação implementam `Validavel`.

## Por que esses CRUDs foram escolhidos

O projeto web InvestAI possui controle de usuários, perfil financeiro, categorias, ganhos, despesas, orçamento, metas, aportes, notícias e sugestões. Esses módulos são os mais coerentes para uma versão desktop porque representam dados principais manipulados pelo usuário ou pelo sistema. Tabelas auxiliares como cache de IA e logs foram deixadas de fora porque não são CRUDs centrais para uso do usuário final.
