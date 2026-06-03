package br.pucpr.investai.data;

import br.pucpr.investai.model.Identificavel;
import br.pucpr.investai.model.Validavel;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FileRepository<T extends Identificavel> {

    private final Path arquivo;

    public FileRepository(String nomeArquivo) {
        this.arquivo = Path.of("data", nomeArquivo + ".dat");

        try {
            Files.createDirectories(arquivo.getParent());
        } catch (IOException e) {
            throw new RepositoryException("Não foi possível criar a pasta de dados.", e);
        }
    }

    public synchronized List<T> listarTodos() {
        if (!Files.exists(arquivo)) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(arquivo))) {
            Object obj = in.readObject();

            return new ArrayList<>((List<T>) obj);
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException("Erro ao ler o arquivo: " + arquivo, e);
        }
    }

    public synchronized T inserir(T objeto) {
        validarSePossivel(objeto);

        List<T> dados = listarTodos();

        int proximoId = dados.stream()
                .map(Identificavel::getId)
                .max(Comparator.naturalOrder())
                .orElse(0) + 1;

        objeto.setId(proximoId);
        dados.add(objeto);

        salvarTodos(dados);

        return objeto;
    }

    public synchronized void atualizar(T objeto) {
        validarSePossivel(objeto);

        List<T> dados = listarTodos();

        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == objeto.getId()) {
                dados.set(i, objeto);
                salvarTodos(dados);
                return;
            }
        }

        throw new RepositoryException("Registro não encontrado para atualização.");
    }

    public synchronized void excluir(int id) {
        List<T> dados = listarTodos();

        boolean removido = dados.removeIf(item -> item.getId() == id);

        if (!removido) {
            throw new RepositoryException("Registro não encontrado para exclusão.");
        }

        salvarTodos(dados);
    }

    public synchronized Optional<T> buscarPorId(int id) {
        return listarTodos()
                .stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    private void salvarTodos(List<T> dados) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(arquivo))) {
            out.writeObject(dados);
        } catch (IOException e) {
            throw new RepositoryException("Erro ao salvar o arquivo: " + arquivo, e);
        }
    }

    private void validarSePossivel(T objeto) {
        if (objeto instanceof Validavel validavel) {
            validavel.validar();
        }
    }
}