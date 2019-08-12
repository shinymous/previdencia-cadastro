package com.previdencia.cadastro.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado");
    }

    public UsuarioNaoEncontradoException(Long id) {
        super(String.format("%s%s", "Usuário não encontrado com id: ", id));
    }

    public UsuarioNaoEncontradoException(String msg) {
        super(msg);
    }

    public UsuarioNaoEncontradoException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public UsuarioNaoEncontradoException(Throwable cause) {
        super("Usuário não encontrado", cause);
    }
}
