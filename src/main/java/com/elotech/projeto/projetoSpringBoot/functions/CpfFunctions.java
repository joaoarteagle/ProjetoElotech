package com.elotech.projeto.projetoSpringBoot.functions;

public class CpfFunctions {


    public static boolean cpfValido(String cpf) {

        cpf = cpf.replaceAll("[^0-9]", "");


        if (cpf.length() != 11) {
            return false;
        }


        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = cpf.charAt(i) - '0';
            soma += digito * (10 - i);
        }
        int restante = soma % 11;
        int first = (restante < 2) ? 0 : (11 - restante);

        if (cpf.charAt(9) - '0' != first) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            int digito = cpf.charAt(i) - '0';
            soma += digito * (11 - i);
        }
        restante = soma % 11;
        int second = (restante < 2) ? 0 : (11 - restante);


        return cpf.charAt(10) - '0' == second;
    }

}
