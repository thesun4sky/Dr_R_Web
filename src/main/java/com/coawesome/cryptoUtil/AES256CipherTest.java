package com.coawesome.cryptoUtil;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class AES256CipherTest {
    String password ;

    public AES256CipherTest(String pass)
    {
        this.password =pass;
    }
     public String encPass() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        AES256Cipher a256 = AES256Cipher.getInstance();
        String enPassword = a256.AES_Encode(password);
        return enPassword;
    }
     public String desPass() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        AES256Cipher a256 = AES256Cipher.getInstance();
        String desPassword = a256.AES_Decode(password);
        return desPassword;
    }
}