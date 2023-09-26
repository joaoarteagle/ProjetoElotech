package com.elotech.projeto.projetoSpringBoot.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFunctions {
    public static boolean emailValido(String email) {
        // Definir uma expressão regular para validar endereços de e-mail
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        // Compilar a expressão regular em um objeto Pattern
        Pattern pattern = Pattern.compile(regex);

        // Criar um objeto Matcher para realizar a correspondência
        Matcher matcher = pattern.matcher(email);

        // Verificar se o e-mail corresponde ao padrão
        return matcher.matches();
    }

}
