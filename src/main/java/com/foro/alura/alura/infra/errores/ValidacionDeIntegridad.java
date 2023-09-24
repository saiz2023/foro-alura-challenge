package com.foro.alura.alura.infra.errores;

public class ValidacionDeIntegridad extends Throwable {
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
