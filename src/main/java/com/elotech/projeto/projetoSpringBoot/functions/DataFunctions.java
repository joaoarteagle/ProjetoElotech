package com.elotech.projeto.projetoSpringBoot.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFunctions {

    public static boolean dataNascValida(String inputDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Não permitir datas inválidas (por exemplo, 31 de fevereiro)

        try {

            Date date = dateFormat.parse(inputDate);

            // Verifica se a data analisada não é maior que a data atual (opcional)
            Date currentDate = new Date();
            if (date.after(currentDate)) {
                return false; // Data futura não é uma data de nascimento válida
            }

            return true; // A data é válida
        } catch (ParseException e) {
            // A data não pôde ser analisada corretamente
            return false;
        }
    }

}
