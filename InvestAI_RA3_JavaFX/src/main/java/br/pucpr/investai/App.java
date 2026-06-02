package br.pucpr.investai;

import br.pucpr.investai.data.FileRepository;
import br.pucpr.investai.model.*;
import br.pucpr.investai.ui.CrudPane;
import br.pucpr.investai.ui.FieldConfig;
import br.pucpr.investai.util.Formatador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        TabPane tabs = new TabPane();
        tabs.getTabs().add(tab("Usuários", crudUsuarios()));
        tabs.getTabs().add(tab("Perfil Financeiro", crudPerfilFinanceiro()));
        tabs.getTabs().add(tab("Categorias", crudCategorias()));
        tabs.getTabs().add(tab("Ganhos", crudGanhos()));
        tabs.getTabs().add(tab("Despesas", crudDespesas()));
        tabs.getTabs().add(tab("Orçamentos", crudOrcamentos()));
        tabs.getTabs().add(tab("Metas", crudMetas()));
        tabs.getTabs().add(tab("Aportes", crudAportes()));
        tabs.getTabs().add(tab("Notícias", crudNoticias()));
        tabs.getTabs().add(tab("Sugestões", crudSugestoes()));

        Label titulo = new Label("InvestAI RA3 - 10 CRUDs JavaFX");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 14;");

        BorderPane root = new BorderPane(tabs);
        root.setTop(titulo);
        Scene scene = new Scene(root, 1280, 760);
        stage.setTitle("InvestAI RA3 - JavaFX sem FXML");
        stage.setScene(scene);
        stage.show();
    }

    private Tab tab(String nome, BorderPane conteudo) {
        Tab tab = new Tab(nome, conteudo);
        tab.setClosable(false);
        return tab;
    }

    private CrudPane<Usuario> crudUsuarios() {
        return new CrudPane<>("CRUD 01 - Usuário",
                new FileRepository<>("usuarios"), Usuario::new,
                List.of(
                        new FieldConfig<>("Nome", Usuario::getNome, (u, v) -> u.setNome(Formatador.textoObrigatorio(v, "Nome")), "Lucas Alfaro"),
                        new FieldConfig<>("E-mail", Usuario::getEmail, (u, v) -> u.setEmail(Formatador.textoObrigatorio(v, "E-mail")), "email@exemplo.com"),
                        new FieldConfig<>("Telefone", Usuario::getTelefone, (u, v) -> u.setTelefone(Formatador.textoObrigatorio(v, "Telefone")), "41999999999"),
                        new FieldConfig<>("Ativo", u -> u.isAtivo() ? "sim" : "não", (u, v) -> u.setAtivo(Formatador.booleano(v)), "sim/não")
                ));
    }

    private CrudPane<PerfilFinanceiro> crudPerfilFinanceiro() {
        return new CrudPane<>("CRUD 02 - Perfil Financeiro",
                new FileRepository<>("perfis_financeiros"), PerfilFinanceiro::new,
                List.of(
                        new FieldConfig<>("ID Usuário", p -> String.valueOf(p.getUsuarioId()), (p, v) -> p.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("Renda Mensal", p -> Formatador.dinheiroTexto(p.getRendaMensal()), (p, v) -> p.setRendaMensal(Formatador.dinheiro(v, "Renda Mensal")), "3500,00"),
                        new FieldConfig<>("Saldo Inicial", p -> Formatador.dinheiroTexto(p.getSaldoInicial()), (p, v) -> p.setSaldoInicial(Formatador.dinheiro(v, "Saldo Inicial")), "1000,00"),
                        new FieldConfig<>("Objetivo", PerfilFinanceiro::getObjetivoFinanceiro, (p, v) -> p.setObjetivoFinanceiro(Formatador.textoObrigatorio(v, "Objetivo")), "Montar reserva"),
                        new FieldConfig<>("Perfil", PerfilFinanceiro::getPerfilComportamento, (p, v) -> p.setPerfilComportamento(Formatador.textoObrigatorio(v, "Perfil")), "conservador/moderado/gastador")
                ));
    }

    private CrudPane<Categoria> crudCategorias() {
        return new CrudPane<>("CRUD 03 - Categoria",
                new FileRepository<>("categorias"), Categoria::new,
                List.of(
                        new FieldConfig<>("ID Usuário", c -> String.valueOf(c.getUsuarioId()), (c, v) -> c.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("Nome", Categoria::getNome, (c, v) -> c.setNome(Formatador.textoObrigatorio(v, "Nome")), "Alimentação"),
                        new FieldConfig<>("Tipo", Categoria::getTipo, (c, v) -> c.setTipo(Formatador.textoObrigatorio(v, "Tipo")), "ganho/despesa"),
                        new FieldConfig<>("Descrição", Categoria::getDescricao, (c, v) -> c.setDescricao(v == null ? "" : v.trim()), "Gastos com mercado")
                ));
    }

    private CrudPane<Ganho> crudGanhos() {
        return new CrudPane<>("CRUD 04 - Ganho",
                new FileRepository<>("ganhos"), Ganho::new,
                List.of(
                        new FieldConfig<>("ID Usuário", g -> String.valueOf(g.getUsuarioId()), (g, v) -> g.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("Descrição", Ganho::getDescricao, (g, v) -> g.setDescricao(Formatador.textoObrigatorio(v, "Descrição")), "Salário"),
                        new FieldConfig<>("Valor", g -> Formatador.dinheiroTexto(g.getValor()), (g, v) -> g.setValor(Formatador.dinheiro(v, "Valor")), "2500,00"),
                        new FieldConfig<>("Data", g -> Formatador.dataTexto(g.getDataGanho()), (g, v) -> g.setDataGanho(Formatador.dataBr(v, "Data")), "DD/MM/AAAA"),
                        new FieldConfig<>("Fixo", g -> g.isFixo() ? "sim" : "não", (g, v) -> g.setFixo(Formatador.booleano(v)), "sim/não"),
                        new FieldConfig<>("ID Categoria", g -> String.valueOf(g.getCategoriaId()), (g, v) -> g.setCategoriaId(Formatador.inteiro(v, "ID Categoria")), "1")
                ));
    }

    private CrudPane<Despesa> crudDespesas() {
        return new CrudPane<>("CRUD 05 - Despesa",
                new FileRepository<>("despesas"), Despesa::new,
                List.of(
                        new FieldConfig<>("ID Usuário", d -> String.valueOf(d.getUsuarioId()), (d, v) -> d.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("Descrição", Despesa::getDescricao, (d, v) -> d.setDescricao(Formatador.textoObrigatorio(v, "Descrição")), "Mercado"),
                        new FieldConfig<>("Valor", d -> Formatador.dinheiroTexto(d.getValor()), (d, v) -> d.setValor(Formatador.dinheiro(v, "Valor")), "250,00"),
                        new FieldConfig<>("Data", d -> Formatador.dataTexto(d.getDataDespesa()), (d, v) -> d.setDataDespesa(Formatador.dataBr(v, "Data")), "DD/MM/AAAA"),
                        new FieldConfig<>("Fixo", d -> d.isFixo() ? "sim" : "não", (d, v) -> d.setFixo(Formatador.booleano(v)), "sim/não"),
                        new FieldConfig<>("ID Categoria", d -> String.valueOf(d.getCategoriaId()), (d, v) -> d.setCategoriaId(Formatador.inteiro(v, "ID Categoria")), "1")
                ));
    }

    private CrudPane<OrcamentoCategoria> crudOrcamentos() {
        return new CrudPane<>("CRUD 06 - Orçamento por Categoria",
                new FileRepository<>("orcamentos"), OrcamentoCategoria::new,
                List.of(
                        new FieldConfig<>("ID Usuário", o -> String.valueOf(o.getUsuarioId()), (o, v) -> o.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("ID Categoria", o -> String.valueOf(o.getCategoriaId()), (o, v) -> o.setCategoriaId(Formatador.inteiro(v, "ID Categoria")), "2"),
                        new FieldConfig<>("Limite Mensal", o -> Formatador.dinheiroTexto(o.getLimiteMensal()), (o, v) -> o.setLimiteMensal(Formatador.dinheiro(v, "Limite Mensal")), "600,00"),
                        new FieldConfig<>("Mês", o -> String.valueOf(o.getMes()), (o, v) -> o.setMes(Formatador.inteiro(v, "Mês")), "6"),
                        new FieldConfig<>("Ano", o -> String.valueOf(o.getAno()), (o, v) -> o.setAno(Formatador.inteiro(v, "Ano")), "2026")
                ));
    }

    private CrudPane<MetaFinanceira> crudMetas() {
        return new CrudPane<>("CRUD 07 - Meta Financeira",
                new FileRepository<>("metas"), MetaFinanceira::new,
                List.of(
                        new FieldConfig<>("ID Usuário", m -> String.valueOf(m.getUsuarioId()), (m, v) -> m.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("Nome", MetaFinanceira::getNome, (m, v) -> m.setNome(Formatador.textoObrigatorio(v, "Nome")), "Reserva de emergência"),
                        new FieldConfig<>("Valor Total", m -> Formatador.dinheiroTexto(m.getValorTotal()), (m, v) -> m.setValorTotal(Formatador.dinheiro(v, "Valor Total")), "10000,00"),
                        new FieldConfig<>("Valor Guardado", m -> Formatador.dinheiroTexto(m.getValorGuardado()), (m, v) -> m.setValorGuardado(Formatador.dinheiro(v, "Valor Guardado")), "1500,00"),
                        new FieldConfig<>("Prazo", m -> Formatador.dataTexto(m.getPrazo()), (m, v) -> m.setPrazo(Formatador.dataBr(v, "Prazo")), "DD/MM/AAAA"),
                        new FieldConfig<>("Ativo", m -> m.isAtivo() ? "sim" : "não", (m, v) -> m.setAtivo(Formatador.booleano(v)), "sim/não")
                ));
    }

    private CrudPane<Aporte> crudAportes() {
        return new CrudPane<>("CRUD 08 - Aporte em Meta",
                new FileRepository<>("aportes"), Aporte::new,
                List.of(
                        new FieldConfig<>("ID Usuário", a -> String.valueOf(a.getUsuarioId()), (a, v) -> a.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("ID Meta", a -> String.valueOf(a.getMetaId()), (a, v) -> a.setMetaId(Formatador.inteiro(v, "ID Meta")), "1"),
                        new FieldConfig<>("Valor", a -> Formatador.dinheiroTexto(a.getValor()), (a, v) -> a.setValor(Formatador.dinheiro(v, "Valor")), "200,00"),
                        new FieldConfig<>("Data", a -> Formatador.dataTexto(a.getDataAporte()), (a, v) -> a.setDataAporte(Formatador.dataBr(v, "Data")), "DD/MM/AAAA"),
                        new FieldConfig<>("Observação", Aporte::getObservacao, (a, v) -> a.setObservacao(v == null ? "" : v.trim()), "Depósito mensal")
                ));
    }

    private CrudPane<NoticiaFinanceira> crudNoticias() {
        return new CrudPane<>("CRUD 09 - Notícia Financeira",
                new FileRepository<>("noticias"), NoticiaFinanceira::new,
                List.of(
                        new FieldConfig<>("Título", NoticiaFinanceira::getTitulo, (n, v) -> n.setTitulo(Formatador.textoObrigatorio(v, "Título")), "Selic permanece estável"),
                        new FieldConfig<>("Fonte", NoticiaFinanceira::getFonte, (n, v) -> n.setFonte(Formatador.textoObrigatorio(v, "Fonte")), "Banco Central"),
                        new FieldConfig<>("URL", NoticiaFinanceira::getUrl, (n, v) -> n.setUrl(Formatador.textoObrigatorio(v, "URL")), "https://exemplo.com/noticia"),
                        new FieldConfig<>("Categoria", NoticiaFinanceira::getCategoria, (n, v) -> n.setCategoria(Formatador.textoObrigatorio(v, "Categoria")), "Economia"),
                        new FieldConfig<>("Impacto", NoticiaFinanceira::getNivelImpacto, (n, v) -> n.setNivelImpacto(Formatador.textoObrigatorio(v, "Impacto")), "baixo/médio/alto"),
                        new FieldConfig<>("Publicação", n -> Formatador.dataTexto(n.getDataPublicacao()), (n, v) -> n.setDataPublicacao(Formatador.dataBr(v, "Publicação")), "DD/MM/AAAA")
                ));
    }

    private CrudPane<SugestaoEconomia> crudSugestoes() {
        return new CrudPane<>("CRUD 10 - Sugestão de Economia",
                new FileRepository<>("sugestoes"), SugestaoEconomia::new,
                List.of(
                        new FieldConfig<>("ID Usuário", s -> String.valueOf(s.getUsuarioId()), (s, v) -> s.setUsuarioId(Formatador.inteiro(v, "ID Usuário")), "1"),
                        new FieldConfig<>("Título", SugestaoEconomia::getTitulo, (s, v) -> s.setTitulo(Formatador.textoObrigatorio(v, "Título")), "Reduzir delivery"),
                        new FieldConfig<>("Descrição", SugestaoEconomia::getDescricao, (s, v) -> s.setDescricao(Formatador.textoObrigatorio(v, "Descrição")), "Diminuir pedidos semanais"),
                        new FieldConfig<>("Fonte", SugestaoEconomia::getFonte, (s, v) -> s.setFonte(Formatador.textoObrigatorio(v, "Fonte")), "Análise IA"),
                        new FieldConfig<>("Categoria", SugestaoEconomia::getCategoriaNome, (s, v) -> s.setCategoriaNome(v == null ? "" : v.trim()), "Alimentação"),
                        new FieldConfig<>("Mês", s -> String.valueOf(s.getMes()), (s, v) -> s.setMes(Formatador.inteiro(v, "Mês")), "6"),
                        new FieldConfig<>("Ano", s -> String.valueOf(s.getAno()), (s, v) -> s.setAno(Formatador.inteiro(v, "Ano")), "2026")
                ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
