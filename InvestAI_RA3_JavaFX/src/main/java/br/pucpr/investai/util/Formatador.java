package br.pucpr.investai.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Formatador {
    public static final DateTimeFormatter DATA_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Formatador() {}

    public static String textoObrigatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo '" + campo + "' é obrigatório.");
        }
        return valor.trim();
    }

    public static BigDecimal dinheiro(String valor, String campo) {
        try {
            String normalizado = textoObrigatorio(valor, campo).replace(".", "").replace(",", ".");
            BigDecimal numero = new BigDecimal(normalizado);
            if (numero.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("O campo '" + campo + "' não pode ser negativo.");
            }
            return numero;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Informe um valor numérico válido para '" + campo + "'. Ex.: 1250,90");
        }
    }

    public static int inteiro(String valor, String campo) {
        try {
            return Integer.parseInt(textoObrigatorio(valor, campo));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Informe um número inteiro válido para '" + campo + "'.");
        }
    }

    public static LocalDate dataBr(String valor, String campo) {
        try {
            return LocalDate.parse(textoObrigatorio(valor, campo), DATA_BR);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Informe uma data válida para '" + campo + "' no formato DD/MM/AAAA.");
        }
    }

    public static boolean booleano(String valor) {
        if (valor == null) return false;
        String v = valor.trim().toLowerCase();
        return v.equals("sim") || v.equals("s") || v.equals("true") || v.equals("1") || v.equals("ativo") || v.equals("fixo");
    }

    public static String dinheiroTexto(BigDecimal valor) {
        return valor == null ? "0,00" : valor.toPlainString().replace('.', ',');
    }

    public static String dataTexto(LocalDate data) {
        return data == null ? "" : data.format(DATA_BR);
    }
}
