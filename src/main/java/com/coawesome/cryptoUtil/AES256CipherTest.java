package com.coawesome.cryptoUtil;


import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AES256CipherTest {
    String password ;

    public AES256CipherTest(String pass)
    {
        this.password =pass;
    }
    @Test public String encPass() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        AES256Cipher a256 = AES256Cipher.getInstance();
        String enPassword = a256.AES_Encode(password);
        return enPassword;

    }
    @Test public String desPass() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        AES256Cipher a256 = AES256Cipher.getInstance();
        String desPassword = a256.AES_Decode(password);
        return desPassword;

    }
}