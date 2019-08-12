package com.previdencia.cadastro.exception;

public class UsuarioValidacaoException extends RuntimeException{

    public UsuarioValidacaoException() {
        super("Dados inválidos");
    }

    public UsuarioValidacaoException(String msg) {
        super(msg);
    }

    public UsuarioValidacaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public UsuarioValidacaoException(Throwable cause) {
        super("Dados inválidos", cause);
    }
}
