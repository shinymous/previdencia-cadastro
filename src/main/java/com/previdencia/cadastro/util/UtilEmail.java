package com.previdencia.cadastro.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class UtilEmail {

    public static boolean isEmailValido(String email){
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }
}
