package com.elotech.projeto.projetoSpringBoot.functions;

public class TelefoneFunctions {

    public static Boolean TelefoneValido(String fone){
        fone = fone.replaceAll("[^0-9]","");
        if (fone.length()!=11){
            return false;
        }
        return true;
    }
}
