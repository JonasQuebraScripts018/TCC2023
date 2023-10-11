package com.agenda.Master.Service;
import org.springframework.stereotype.Service;

import java.util.regex.*;

public class S_Generico {

        public static boolean validarEmail(String email) {
            // Define a expressão regular para validar o formato do e-mail
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

            // Compila a expressão regular em um objeto Pattern
            Pattern pattern = Pattern.compile(regex);

            // Cria um objeto Matcher para o e-mail fornecido
            Matcher matcher = pattern.matcher(email);

            // Retorna true se o e-mail corresponder à expressão regular, caso contrário, retorna false
            return matcher.matches();
        }
        public static boolean textoEstaVazio(String texto){
            return texto == null || texto.trim().equals("");
        }

        public static String cleanerNumber(String number){
            return number.replaceAll("[^0-9]", "");
        }

    }
