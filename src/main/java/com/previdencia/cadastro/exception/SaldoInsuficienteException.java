package com.previdencia.cadastro.exception;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente");
    }

    public SaldoInsuficienteException(BigDecimal valor) {
        super(String.format("Saldo insuficiente para valor %s ", valor));
    }

    public SaldoInsuficienteException(String msg) {
        super(msg);
    }

    public SaldoInsuficienteException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public SaldoInsuficienteException(Throwable cause) {
        super("Saldo insuficiente", cause);
    }
}
