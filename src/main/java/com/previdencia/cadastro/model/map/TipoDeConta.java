package com.previdencia.cadastro.model.map;

public enum TipoDeConta {

    COMUM("Comum"),
    EVENTUAL("Eventual");

    private String parametro;

    TipoDeConta(String parametro){this.parametro = parametro;}

    public String parametro() {
        return parametro;
    }

    public static String get(String key){
        for(TipoDeConta n : TipoDeConta.values()){
            if(key.equals(n.parametro()))
                return n.name();
        }
        return null;
    }

}
