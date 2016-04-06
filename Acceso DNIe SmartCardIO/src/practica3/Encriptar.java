package practica3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ángela
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Encriptar {
    


public class SHA1 {
    private MessageDigest md;
    private byte[] buffer, digest;
    private String hash = "";

    public String getHash(String message) throws NoSuchAlgorithmException {
        buffer = message.getBytes();
        md = MessageDigest.getInstance("SHA1");
        md.update(buffer);
        digest = md.digest();

        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
     return hash;}
}




public String cript(String password){
  SHA1 s = new SHA1();
  String basura="rwr24t5yt25y543td32ty6";
        try {
            return s.getHash(s.getHash(password+basura)+basura);
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    
    return "0";
}
    }




