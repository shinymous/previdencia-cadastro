package com.previdencia.cadastro.exception;

public class EntidadeNaoEcontradaException extends RuntimeException {
    public EntidadeNaoEcontradaException() {
        super("Entidade não encontrado");
    }

    public EntidadeNaoEcontradaException(String entidade, Long id) {
        super(String.format("Entidade %s não encontrada com id: %s ", entidade,  id));
    }

    public EntidadeNaoEcontradaException(String msg) {
        super(msg);
    }

    public EntidadeNaoEcontradaException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public EntidadeNaoEcontradaException(Throwable cause) {
        super("Usuário não encontrado", cause);
    }
}
