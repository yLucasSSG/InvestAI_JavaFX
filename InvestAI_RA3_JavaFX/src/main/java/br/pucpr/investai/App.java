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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.List;

public class App extends Application {
    private static final String CAMPO_ID_USUARIO = "ID Usuário";
    private static final String CAMPO_ID_CATEGORIA = "ID Categoria";
    private static final String CAMPO_ID_META = "ID Meta";
    private static final String CAMPO_NOME = "Nome";
    private static final String CAMPO_EMAIL = "E-mail";
    private static final String CAMPO_TELEFONE = "Telefone";
    private static final String CAMPO_OBJETIVO = "Objetivo";
    private static final String CAMPO_PERFIL = "Perfil";
    private static final String CAMPO_TIPO = "Tipo";
    private static final String CAMPO_DESCRICAO = "Descrição";
    private static final String CAMPO_TITULO = "Título";
    private static final String CAMPO_FONTE = "Fonte";
    private static final String CAMPO_URL = "URL";
    private static final String CAMPO_CATEGORIA = "Categoria";
    private static final String CAMPO_IMPACTO = "Impacto";
    private static final String CAMPO_VALOR = "Valor";
    private static final String CAMPO_RENDA_MENSAL = "Renda Mensal";
    private static final String CAMPO_SALDO_INICIAL = "Saldo Inicial";
    private static final String CAMPO_VALOR_TOTAL = "Valor Total";
    private static final String CAMPO_VALOR_GUARDADO = "Valor Guardado";
    private static final String CAMPO_LIMITE_MENSAL = "Limite Mensal";
    private static final String CAMPO_DATA = "Data";
    private static final String CAMPO_PUBLICACAO = "Publicação";
    private static final String CAMPO_PRAZO = "Prazo";
    private static final String CAMPO_MES = "Mês";
    private static final String CAMPO_ANO = "Ano";
    private static final String CAMPO_OBSERVACAO = "Observação";
    private static final String CAMPO_ATIVO = "Ativo";
    private static final String CAMPO_FIXO = "Fixo";

    private static final String PLACEHOLDER_DDMMYYYY = "DD/MM/AAAA";
    private static final String PLACEHOLDER_SIM_NAO = "sim/não";

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

    private <T extends Identificavel> CrudPane<T> crud(String titulo, String arquivo, Supplier<T> factory, List<FieldConfig<T>> campos) {
        return new CrudPane<>(titulo, new FileRepository<>(arquivo), factory, campos);
    }

    private <T> FieldConfig<T> texto(String rotulo, Function<T, String> getter, BiConsumer<T, String> setter, String placeholder) {
        return new FieldConfig<>(rotulo, getter, setter, placeholder);
    }

    private <T> FieldConfig<T> textoTrim(String rotulo, Function<T, String> getter, BiConsumer<T, String> setter, String placeholder) {
        return texto(rotulo, getter, (objeto, valor) -> setter.accept(objeto, valor == null ? "" : valor.trim()), placeholder);
    }

    private <T> FieldConfig<T> textoObrigatorio(String rotulo, Function<T, String> getter, BiConsumer<T, String> setter, String placeholder, String campo) {
        return texto(rotulo, getter, (objeto, valor) -> setter.accept(objeto, Formatador.textoObrigatorio(valor, campo)), placeholder);
    }

    private <T> FieldConfig<T> inteiro(String rotulo, Function<T, String> getter, ObjIntConsumer<T> setter, String placeholder, String campo) {
        return texto(rotulo, getter, (objeto, valor) -> setter.accept(objeto, Formatador.inteiro(valor, campo)), placeholder);
    }

    private <T> FieldConfig<T> dinheiro(String rotulo, Function<T, String> getter, BiConsumer<T, BigDecimal> setter, String placeholder, String campo) {
        return texto(rotulo, getter, (objeto, valor) -> setter.accept(objeto, Formatador.dinheiro(valor, campo)), placeholder);
    }

    private <T> FieldConfig<T> data(String rotulo, Function<T, String> getter, BiConsumer<T, LocalDate> setter, String placeholder, String campo) {
        return texto(rotulo, getter, (objeto, valor) -> setter.accept(objeto, Formatador.dataBr(valor, campo)), placeholder);
    }

    private <T> FieldConfig<T> booleano(String rotulo, Function<T, String> getter, BiConsumer<T, Boolean> setter, String placeholder) {
        return texto(rotulo, getter, (objeto, valor) -> setter.accept(objeto, Formatador.booleano(valor)), placeholder);
    }

    private CrudPane<Usuario> crudUsuarios() {
        return crud("CRUD 01 - Usuário", "usuarios", Usuario::new,
                List.of(
                        textoObrigatorio(CAMPO_NOME, Usuario::getNome, Usuario::setNome, "Lucas Alfaro", CAMPO_NOME),
                        textoObrigatorio(CAMPO_EMAIL, Usuario::getEmail, Usuario::setEmail, "email@exemplo.com", CAMPO_EMAIL),
                        textoObrigatorio(CAMPO_TELEFONE, Usuario::getTelefone, Usuario::setTelefone, "41999999999", CAMPO_TELEFONE),
                        booleano(CAMPO_ATIVO, u -> u.isAtivo() ? "sim" : "não", Usuario::setAtivo, PLACEHOLDER_SIM_NAO)
                ));
    }

    private CrudPane<PerfilFinanceiro> crudPerfilFinanceiro() {
        return crud("CRUD 02 - Perfil Financeiro", "perfis_financeiros", PerfilFinanceiro::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, p -> String.valueOf(p.getUsuarioId()), PerfilFinanceiro::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        dinheiro(CAMPO_RENDA_MENSAL, p -> Formatador.dinheiroTexto(p.getRendaMensal()), PerfilFinanceiro::setRendaMensal, "3500,00", CAMPO_RENDA_MENSAL),
                        dinheiro(CAMPO_SALDO_INICIAL, p -> Formatador.dinheiroTexto(p.getSaldoInicial()), PerfilFinanceiro::setSaldoInicial, "1000,00", CAMPO_SALDO_INICIAL),
                        textoObrigatorio(CAMPO_OBJETIVO, PerfilFinanceiro::getObjetivoFinanceiro, PerfilFinanceiro::setObjetivoFinanceiro, "Montar reserva", CAMPO_OBJETIVO),
                        textoObrigatorio(CAMPO_PERFIL, PerfilFinanceiro::getPerfilComportamento, PerfilFinanceiro::setPerfilComportamento, "conservador/moderado/gastador", CAMPO_PERFIL)
                ));
    }

    private CrudPane<Categoria> crudCategorias() {
        return crud("CRUD 03 - Categoria", "categorias", Categoria::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, c -> String.valueOf(c.getUsuarioId()), Categoria::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        textoObrigatorio(CAMPO_NOME, Categoria::getNome, Categoria::setNome, "Alimentação", CAMPO_NOME),
                        textoObrigatorio(CAMPO_TIPO, Categoria::getTipo, Categoria::setTipo, "ganho/despesa", CAMPO_TIPO),
                        textoTrim(CAMPO_DESCRICAO, Categoria::getDescricao, Categoria::setDescricao, "Gastos com mercado")
                ));
    }

    private CrudPane<Ganho> crudGanhos() {
        return crud("CRUD 04 - Ganho", "ganhos", Ganho::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, g -> String.valueOf(g.getUsuarioId()), Ganho::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        textoObrigatorio(CAMPO_DESCRICAO, Ganho::getDescricao, Ganho::setDescricao, "Salário", CAMPO_DESCRICAO),
                        dinheiro(CAMPO_VALOR, g -> Formatador.dinheiroTexto(g.getValor()), Ganho::setValor, "2500,00", CAMPO_VALOR),
                        data(CAMPO_DATA, g -> Formatador.dataTexto(g.getDataGanho()), Ganho::setDataGanho, PLACEHOLDER_DDMMYYYY, CAMPO_DATA),
                        booleano(CAMPO_FIXO, g -> g.isFixo() ? "sim" : "não", Ganho::setFixo, PLACEHOLDER_SIM_NAO),
                        inteiro(CAMPO_ID_CATEGORIA, g -> String.valueOf(g.getCategoriaId()), Ganho::setCategoriaId, "1", CAMPO_ID_CATEGORIA)
                ));
    }

    private CrudPane<Despesa> crudDespesas() {
        return crud("CRUD 05 - Despesa", "despesas", Despesa::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, d -> String.valueOf(d.getUsuarioId()), Despesa::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        textoObrigatorio(CAMPO_DESCRICAO, Despesa::getDescricao, Despesa::setDescricao, "Mercado", CAMPO_DESCRICAO),
                        dinheiro(CAMPO_VALOR, d -> Formatador.dinheiroTexto(d.getValor()), Despesa::setValor, "250,00", CAMPO_VALOR),
                        data(CAMPO_DATA, d -> Formatador.dataTexto(d.getDataDespesa()), Despesa::setDataDespesa, PLACEHOLDER_DDMMYYYY, CAMPO_DATA),
                        booleano(CAMPO_FIXO, d -> d.isFixo() ? "sim" : "não", Despesa::setFixo, PLACEHOLDER_SIM_NAO),
                        inteiro(CAMPO_ID_CATEGORIA, d -> String.valueOf(d.getCategoriaId()), Despesa::setCategoriaId, "1", CAMPO_ID_CATEGORIA)
                ));
    }

    private CrudPane<OrcamentoCategoria> crudOrcamentos() {
        return crud("CRUD 06 - Orçamento por Categoria", "orcamentos", OrcamentoCategoria::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, o -> String.valueOf(o.getUsuarioId()), OrcamentoCategoria::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        inteiro(CAMPO_ID_CATEGORIA, o -> String.valueOf(o.getCategoriaId()), OrcamentoCategoria::setCategoriaId, "2", CAMPO_ID_CATEGORIA),
                        dinheiro(CAMPO_LIMITE_MENSAL, o -> Formatador.dinheiroTexto(o.getLimiteMensal()), OrcamentoCategoria::setLimiteMensal, "600,00", CAMPO_LIMITE_MENSAL),
                        inteiro(CAMPO_MES, o -> String.valueOf(o.getMes()), OrcamentoCategoria::setMes, "6", CAMPO_MES),
                        inteiro(CAMPO_ANO, o -> String.valueOf(o.getAno()), OrcamentoCategoria::setAno, "2026", CAMPO_ANO)
                ));
    }

    private CrudPane<MetaFinanceira> crudMetas() {
        return crud("CRUD 07 - Meta Financeira", "metas", MetaFinanceira::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, m -> String.valueOf(m.getUsuarioId()), MetaFinanceira::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        textoObrigatorio(CAMPO_NOME, MetaFinanceira::getNome, MetaFinanceira::setNome, "Reserva de emergência", CAMPO_NOME),
                        dinheiro(CAMPO_VALOR_TOTAL, m -> Formatador.dinheiroTexto(m.getValorTotal()), MetaFinanceira::setValorTotal, "10000,00", CAMPO_VALOR_TOTAL),
                        dinheiro(CAMPO_VALOR_GUARDADO, m -> Formatador.dinheiroTexto(m.getValorGuardado()), MetaFinanceira::setValorGuardado, "1500,00", CAMPO_VALOR_GUARDADO),
                        data(CAMPO_PRAZO, m -> Formatador.dataTexto(m.getPrazo()), MetaFinanceira::setPrazo, PLACEHOLDER_DDMMYYYY, CAMPO_PRAZO),
                        booleano(CAMPO_ATIVO, m -> m.isAtivo() ? "sim" : "não", MetaFinanceira::setAtivo, PLACEHOLDER_SIM_NAO)
                ));
    }

    private CrudPane<Aporte> crudAportes() {
        return crud("CRUD 08 - Aporte em Meta", "aportes", Aporte::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, a -> String.valueOf(a.getUsuarioId()), Aporte::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        inteiro(CAMPO_ID_META, a -> String.valueOf(a.getMetaId()), Aporte::setMetaId, "1", CAMPO_ID_META),
                        dinheiro(CAMPO_VALOR, a -> Formatador.dinheiroTexto(a.getValor()), Aporte::setValor, "200,00", CAMPO_VALOR),
                        data(CAMPO_DATA, a -> Formatador.dataTexto(a.getDataAporte()), Aporte::setDataAporte, PLACEHOLDER_DDMMYYYY, CAMPO_DATA),
                        textoTrim(CAMPO_OBSERVACAO, Aporte::getObservacao, Aporte::setObservacao, "Depósito mensal")
                ));
    }

    private CrudPane<NoticiaFinanceira> crudNoticias() {
        return crud("CRUD 09 - Notícia Financeira", "noticias", NoticiaFinanceira::new,
                List.of(
                        textoObrigatorio(CAMPO_TITULO, NoticiaFinanceira::getTitulo, NoticiaFinanceira::setTitulo, "Selic permanece estável", CAMPO_TITULO),
                        textoObrigatorio(CAMPO_FONTE, NoticiaFinanceira::getFonte, NoticiaFinanceira::setFonte, "Banco Central", CAMPO_FONTE),
                        textoObrigatorio(CAMPO_URL, NoticiaFinanceira::getUrl, NoticiaFinanceira::setUrl, "https://exemplo.com/noticia", CAMPO_URL),
                        textoObrigatorio(CAMPO_CATEGORIA, NoticiaFinanceira::getCategoria, NoticiaFinanceira::setCategoria, "Economia", CAMPO_CATEGORIA),
                        textoObrigatorio(CAMPO_IMPACTO, NoticiaFinanceira::getNivelImpacto, NoticiaFinanceira::setNivelImpacto, "baixo/médio/alto", CAMPO_IMPACTO),
                        data(CAMPO_PUBLICACAO, n -> Formatador.dataTexto(n.getDataPublicacao()), NoticiaFinanceira::setDataPublicacao, PLACEHOLDER_DDMMYYYY, CAMPO_PUBLICACAO)
                ));
    }

    private CrudPane<SugestaoEconomia> crudSugestoes() {
        return crud("CRUD 10 - Sugestão de Economia", "sugestoes", SugestaoEconomia::new,
                List.of(
                        inteiro(CAMPO_ID_USUARIO, s -> String.valueOf(s.getUsuarioId()), SugestaoEconomia::setUsuarioId, "1", CAMPO_ID_USUARIO),
                        textoObrigatorio(CAMPO_TITULO, SugestaoEconomia::getTitulo, SugestaoEconomia::setTitulo, "Reduzir delivery", CAMPO_TITULO),
                        textoObrigatorio(CAMPO_DESCRICAO, SugestaoEconomia::getDescricao, SugestaoEconomia::setDescricao, "Diminuir pedidos semanais", CAMPO_DESCRICAO),
                        textoObrigatorio(CAMPO_FONTE, SugestaoEconomia::getFonte, SugestaoEconomia::setFonte, "Análise IA", CAMPO_FONTE),
                        textoTrim(CAMPO_CATEGORIA, SugestaoEconomia::getCategoriaNome, SugestaoEconomia::setCategoriaNome, "Alimentação"),
                        inteiro(CAMPO_MES, s -> String.valueOf(s.getMes()), SugestaoEconomia::setMes, "6", CAMPO_MES),
                        inteiro(CAMPO_ANO, s -> String.valueOf(s.getAno()), SugestaoEconomia::setAno, "2026", CAMPO_ANO)
                ));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
