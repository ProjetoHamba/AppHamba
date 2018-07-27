package com.apphamba.hamba.infra;

public class HambaAppException extends Exception{
    public HambaAppException(String mensagem){
        super(mensagem);
    }
    public HambaAppException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
