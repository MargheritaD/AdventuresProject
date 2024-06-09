package com.example.adventures;

import com.example.adventures.bean.LoginBean;
import com.example.adventures.exception.EmailFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginBeanTest {

    /*
         Il seguente test verifica se viene lanciata l'eccezione
         EmailFormatException quando viene creata un'istanza di
         LoginBean passando una mail con un formato sintatticamente
         errato
    */

    @Test

    void testValidEmail() {
        int validFormat;
        try {
            new LoginBean("mariorossi@", "abc");
            validFormat = 1;
        } catch (EmailFormatException e) {
            validFormat = 0;
        }
        assertEquals(0, validFormat);

        /*
            il test ha successo perchè l'eccezione
            viene sollevata assegnando il valore 0
            a validFormat
         */
    }

}
