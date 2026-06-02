package br.pucpr.investai.ui;

import br.pucpr.investai.data.FileRepository;
import br.pucpr.investai.model.Identificavel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CrudPane<T extends Identificavel> extends BorderPane {
    private final String titulo;
    private final FileRepository<T> repository;
    private final Supplier<T> factory;
    private final List<FieldConfig<T>> campos;
    private final ObservableList<T> dados = FXCollections.observableArrayList();
    private final TableView<T> tabela = new TableView<>();
    private final Map<FieldConfig<T>, TextField> inputs = new LinkedHashMap<>();

    public CrudPane(String titulo, FileRepository<T> repository, Supplier<T> factory, List<FieldConfig<T>> campos) {
        this.titulo = titulo;
        this.repository = repository;
        this.factory = factory;
        this.campos = campos;
        montarTela();
        recarregar();
    }

    private void montarTela() {
        setPadding(new Insets(16));
        setTop(cabecalho());
        setCenter(tabela());
        setRight(formulario());
    }

    private VBox cabecalho() {
        Label title = new Label(titulo);
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label subtitle = new Label("CRUD completo: inclusão, consulta, atualização e exclusão com persistência em arquivo.");
        subtitle.setStyle("-fx-text-fill: #555;");
        VBox box = new VBox(4, title, subtitle);
        box.setPadding(new Insets(0, 0, 12, 0));
        return box;
    }

    private TableView<T> tabela() {
        TableColumn<T, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        idCol.setPrefWidth(60);
        tabela.getColumns().add(idCol);

        for (FieldConfig<T> campo : campos) {
            TableColumn<T, String> col = new TableColumn<>(campo.getRotulo());
            col.setCellValueFactory(data -> new SimpleStringProperty(campo.getGetter().apply(data.getValue())));
            col.setPrefWidth(150);
            tabela.getColumns().add(col);
        }

        tabela.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tabela.setItems(dados);
        tabela.getSelectionModel().selectedItemProperty().addListener((obs, antigo, selecionado) -> preencherFormulario(selecionado));
        return tabela;
    }

    private VBox formulario() {
        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(0, 0, 12, 16));

        int linha = 0;
        for (FieldConfig<T> campo : campos) {
            Label label = new Label(campo.getRotulo() + ":");
            TextField input = new TextField();
            input.setPromptText(campo.getPlaceholder());
            input.setPrefWidth(230);
            inputs.put(campo, input);
            grid.add(label, 0, linha);
            grid.add(input, 1, linha);
            linha++;
        }

        Button btnNovo = new Button("Limpar");
        Button btnSalvar = new Button("Inserir");
        Button btnAtualizar = new Button("Atualizar");
        Button btnExcluir = new Button("Excluir");
        Button btnRecarregar = new Button("Recarregar");

        btnNovo.setMaxWidth(Double.MAX_VALUE);
        btnSalvar.setMaxWidth(Double.MAX_VALUE);
        btnAtualizar.setMaxWidth(Double.MAX_VALUE);
        btnExcluir.setMaxWidth(Double.MAX_VALUE);
        btnRecarregar.setMaxWidth(Double.MAX_VALUE);

        btnNovo.setOnAction(e -> limparFormulario());
        btnSalvar.setOnAction(e -> inserir());
        btnAtualizar.setOnAction(e -> atualizar());
        btnExcluir.setOnAction(e -> excluir());
        btnRecarregar.setOnAction(e -> recarregar());

        HBox linha1 = new HBox(8, btnNovo, btnSalvar);
        HBox linha2 = new HBox(8, btnAtualizar, btnExcluir);
        HBox linha3 = new HBox(8, btnRecarregar);
        linha1.setAlignment(Pos.CENTER_RIGHT);
        linha2.setAlignment(Pos.CENTER_RIGHT);
        linha3.setAlignment(Pos.CENTER_RIGHT);

        VBox box = new VBox(10, grid, linha1, linha2, linha3);
        box.setPadding(new Insets(0, 0, 0, 12));
        box.setMinWidth(390);
        return box;
    }

    private void inserir() {
        try {
            T novo = factory.get();
            aplicarFormulario(novo);
            repository.inserir(novo);
            recarregar();
            limparFormulario();
            Alertas.info("Sucesso", "Registro inserido com sucesso.");
        } catch (Exception ex) {
            Alertas.erro("Erro ao inserir", ex.getMessage());
        }
    }

    private void atualizar() {
        T selecionado = tabela.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            Alertas.erro("Nenhum registro selecionado", "Selecione um item da tabela para atualizar.");
            return;
        }
        try {
            aplicarFormulario(selecionado);
            repository.atualizar(selecionado);
            recarregar();
            Alertas.info("Sucesso", "Registro atualizado com sucesso.");
        } catch (Exception ex) {
            Alertas.erro("Erro ao atualizar", ex.getMessage());
        }
    }

    private void excluir() {
        T selecionado = tabela.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            Alertas.erro("Nenhum registro selecionado", "Selecione um item da tabela para excluir.");
            return;
        }
        if (!Alertas.confirmar("Deseja realmente excluir o registro ID " + selecionado.getId() + "?")) {
            return;
        }
        try {
            repository.excluir(selecionado.getId());
            recarregar();
            limparFormulario();
            Alertas.info("Sucesso", "Registro excluído com sucesso.");
        } catch (Exception ex) {
            Alertas.erro("Erro ao excluir", ex.getMessage());
        }
    }

    private void aplicarFormulario(T objeto) {
        for (FieldConfig<T> campo : campos) {
            campo.getSetter().accept(objeto, inputs.get(campo).getText());
        }
    }

    private void preencherFormulario(T objeto) {
        if (objeto == null) return;
        for (FieldConfig<T> campo : campos) {
            inputs.get(campo).setText(campo.getGetter().apply(objeto));
        }
    }

    private void limparFormulario() {
        tabela.getSelectionModel().clearSelection();
        inputs.values().forEach(TextInputControl::clear);
    }

    private void recarregar() {
        dados.setAll(repository.listarTodos());
        tabela.refresh();
    }
}
