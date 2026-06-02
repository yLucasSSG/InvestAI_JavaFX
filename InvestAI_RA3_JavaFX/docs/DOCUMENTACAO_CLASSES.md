# Documentação resumida das classes

## Usuario
Representa o usuário do InvestAI. Campos: nome, e-mail, telefone e status ativo.
Métodos principais: getters/setters, `validar()`.

## PerfilFinanceiro
Representa os dados financeiros gerais do usuário. Campos: usuário, renda mensal, saldo inicial, objetivo e perfil comportamental.
Métodos principais: getters/setters, `validar()`.

## Categoria
Representa uma categoria de ganho ou despesa. Campos: usuário, nome, tipo e descrição.
Métodos principais: getters/setters, `validar()`.

## Ganho
Representa uma entrada financeira. Campos: usuário, descrição, valor, data, fixo e categoria.
Métodos principais: getters/setters, `validar()`.

## Despesa
Representa uma saída financeira. Campos: usuário, descrição, valor, data, fixo e categoria.
Métodos principais: getters/setters, `validar()`.

## OrcamentoCategoria
Representa o limite mensal de gastos para uma categoria. Campos: usuário, categoria, limite mensal, mês e ano.
Métodos principais: getters/setters, `validar()`.

## MetaFinanceira
Representa uma meta de economia. Campos: usuário, nome, valor total, valor guardado, prazo e ativo.
Métodos principais: getters/setters, `validar()`.

## Aporte
Representa um depósito feito em uma meta financeira. Campos: usuário, meta, valor, data e observação.
Métodos principais: getters/setters, `validar()`.

## NoticiaFinanceira
Representa uma notícia usada no módulo financeiro do InvestAI. Campos: título, fonte, URL, categoria, impacto e data.
Métodos principais: getters/setters, `validar()`.

## SugestaoEconomia
Representa uma sugestão de economia. Campos: usuário, título, descrição, fonte, categoria, mês e ano.
Métodos principais: getters/setters, `validar()`.

## FileRepository<T>
Classe genérica responsável pela persistência em arquivos. Métodos: `listarTodos()`, `inserir()`, `atualizar()`, `excluir()` e `buscarPorId()`.

## CrudPane<T>
Classe genérica de interface JavaFX. Monta tabela, formulário e botões de CRUD para qualquer Model que implemente `Identificavel`.

## App
Classe principal do programa. Cria as 10 abas de CRUD e configura os campos de cada Model.
