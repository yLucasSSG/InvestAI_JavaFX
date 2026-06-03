package br.pucpr.investai.data;

import br.pucpr.investai.model.Identificavel;
import br.pucpr.investai.model.Validavel;

import java.io.*;
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
        return lerDados();
    }

    public synchronized T inserir(T objeto) {
        validarSePossivel(objeto);
        List<T> dados = lerDados();
        objeto.setId(proximoId(dados));
        dados.add(objeto);
        salvarDados(dados);
        return objeto;
    }

    public synchronized void atualizar(T objeto) {
        validarSePossivel(objeto);
        List<T> dados = lerDados();
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == objeto.getId()) {
                dados.set(i, objeto);
                salvarDados(dados);
                return;
            }
        }
        throw new RepositoryException("Registro não encontrado para atualização.");
    }

    public synchronized void excluir(int id) {
        List<T> dados = lerDados();
        boolean removido = dados.removeIf(item -> item.getId() == id);
        if (!removido) {
            throw new RepositoryException("Registro não encontrado para exclusão.");
        }
        salvarDados(dados);
    }

    public synchronized Optional<T> buscarPorId(int id) {
        return lerDados().stream().filter(item -> item.getId() == id).findFirst();
    }

    @SuppressWarnings("unchecked")
    private List<T> lerDados() {
        if (!Files.exists(arquivo)) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(arquivo))) {
            Object obj = in.readObject();
            return new ArrayList<>((List<T>) obj);
        } catch (EOFException ignored) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException("Erro ao ler o arquivo: " + arquivo, e);
        }
    }

    private void salvarDados(List<T> dados) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(arquivo))) {
            out.writeObject(dados);
        } catch (IOException e) {
            throw new RepositoryException("Erro ao salvar o arquivo: " + arquivo, e);
        }
    }

    private int proximoId(List<T> dados) {
        return dados.stream().map(Identificavel::getId).max(Comparator.naturalOrder()).orElse(0) + 1;
    }

    private void validarSePossivel(T objeto) {
        if (objeto instanceof Validavel validavel) {
            validavel.validar();
        }
    }
}
