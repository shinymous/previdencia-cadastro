package com.previdencia.cadastro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilTelefone {

    public static boolean isTelefone(String numeroTelefone){
        numeroTelefone = numeroTelefone.replaceAll(" ","");
        String regex = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numeroTelefone);
        return matcher.find();
    }
}
