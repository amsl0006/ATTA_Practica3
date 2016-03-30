package practica3;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Desktop;
import java.io.BufferedReader;
import practica3.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.smartcardio.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * La clase ObtenerDatos implementa cuatro métodos públicos que permiten obtener
 * determinados datos de los certificados de tarjetas DNIe, Izenpe y Ona.
 *
 * @author tbc
 */
public class ObtenerDatos {
public String Apellido1 =null;
public String letraNombre =null;
public String letraApellido2 =null;
public String dusuario =null;
public String DNI=null;
public String contrasena=null;
    private static final byte[] dnie_v_1_0_Atr = {
        (byte) 0x3B, (byte) 0x7F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x6A, (byte) 0x44,
        (byte) 0x4E, (byte) 0x49, (byte) 0x65, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00, (byte) 0x90, (byte) 0x00};
    private static final byte[] dnie_v_1_0_Mask = {
        (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
        (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF};

    public ObtenerDatos() {
    }

    public String LeerNIF() {
        String nif = null;
        try {
            Card c = ConexionTarjeta();
            if (c == null) {
                throw new Exception("No se ha encontrado ninguna tarjeta");
            }
            byte[] atr = c.getATR().getBytes();
            CardChannel ch = c.getBasicChannel();

            if (esDNIe(atr)) {
                nif = leerDeCertificado(ch);
            }
            c.disconnect(false);

        } catch (Exception ex) {
            Logger.getLogger(ObtenerDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return nif;
    }

  

    public String leerDeCertificado(CardChannel ch) throws CardException {
        int offset = 0;
        String usuario =null;
        String completName = null;
        String Apellido1 =null;
        String letraNombre =null;
        String letraApellido2 =null;

        byte[] command = new byte[]{(byte) 0x00, (byte) 0xa4, (byte) 0x04, (byte) 0x00, (byte) 0x0b, (byte) 0x4D, (byte) 0x61, (byte) 0x73, (byte) 0x74, (byte) 0x65, (byte) 0x72, (byte) 0x2E, (byte) 0x46, (byte) 0x69, (byte) 0x6C, (byte) 0x65};
        ResponseAPDU r = ch.transmit(new CommandAPDU(command));
        if ((byte) r.getSW() != (byte) 0x9000) {
            System.out.println("SW incorrecto");
            return null;
        }

        //Seleccionamos el directorio PKCS#15 5015
        command = new byte[]{(byte) 0x00, (byte) 0xA4, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x50, (byte) 0x15};
        r = ch.transmit(new CommandAPDU(command));

        if ((byte) r.getSW() != (byte) 0x9000) {
            System.out.println("SW incorrecto");
            return null;
        }

        //Seleccionamos el Certificate Directory File (CDF) del DNIe 6004
        command = new byte[]{(byte) 0x00, (byte) 0xA4, (byte) 0x00, (byte) 0x00, (byte) 0x02, (byte) 0x60, (byte) 0x04};
        r = ch.transmit(new CommandAPDU(command));

        if ((byte) r.getSW() != (byte) 0x9000) {
            System.out.println("SW incorrecto");
            return null;
        }

        //Leemos FF bytes del archivo
        command = new byte[]{(byte) 0x00, (byte) 0xB0, (byte) 0x00, (byte) 0x00, (byte) 0xFF};
        r = ch.transmit(new CommandAPDU(command));

        if ((byte) r.getSW() == (byte) 0x9000) {
            byte[] datos = r.getData();

            if (datos[4] == 0x30) {
                offset = 4;
                offset += datos[offset + 1] + 2; //Obviamos la seccion del Label
            }

            if (datos[offset] == 0x30) {
                offset += datos[offset + 1] + 2; //Obviamos la seccion de la informacion sobre la fecha de expedición etc
            }

            if ((byte) datos[offset] == (byte) 0xA1) {
                //El certificado empieza aquí
                byte[] r3 = new byte[9];

                
                
                
                //Nos posicionamos en el byte donde empieza el NIF y leemos sus 9 bytes
                for (int z = 0; z < 9; z++) {
                    r3[z] = datos[112 + z];
                }
                completName = new String(r3);
                DNI=completName;
            }
           // System.out.println(completName);
            //para los apellidos
            if ((byte) datos[offset] == (byte) 0xA1) {
                //El certificado empieza aquí
                byte[] rapellido1 = new byte[20];

                
                
                
                //Nos posicionamos en el byte donde empieza el NIF y leemos sus 9 bytes
                for (int z = 0; z < rapellido1.length; z++) {
                    rapellido1[z] = datos[132 + z];
                }
                Apellido1 = new String(rapellido1);
            }
            //System.out.println("Apellido 1:"+Apellido1);
           
            if ((byte) datos[offset] == (byte) 0xA1) {
                //El certificado empieza aquí
                byte[] rnombre = new byte[14];

                
                
                
                //Nos posicionamos en el byte donde empieza el NIF y leemos sus 9 bytes
                for (int z = 0; z < rnombre.length; z++) {
                    rnombre[z] = datos[217 + z];
                }
                letraNombre = new String(rnombre);
            }
            //System.out.println("Nombre:"+letraNombre);
            
            
            if ((byte) datos[offset] == (byte) 0xA1) {
                //El certificado empieza aquí
                byte[] rapellido2 = new byte[6];

                
                
                
                //Nos posicionamos en el byte donde empieza el NIF y leemos sus 9 bytes
                for (int z = 0; z < rapellido2.length; z++) {
                    rapellido2[z] = datos[209 + z];
                }
                letraApellido2 = new String(rapellido2);
            }
            //System.out.println("Apellido 2:"+letraApellido2);
           usuario=letraNombre+Apellido1+letraApellido2; 
           
        String susuario = usuario.substring(0,1)+usuario.substring(12,19)+usuario.substring(30,31);
        //System.out.println("Usuario: "+susuario);
        dusuario=susuario;
           
        }
        return completName;
        
    }

   

    /**
     * Este método establece la conexión con la tarjeta. La función busca el
     * Terminal que contenga una tarjeta, independientemente del tipo de tarjeta
     * que sea.
     *
     * @return objeto Card con conexión establecida
     * @throws Exception
     */
    private Card ConexionTarjeta() throws Exception {

        Card card = null;
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();
        //System.out.println("Terminals: " + terminals);

        for (int i = 0; i < terminals.size(); i++) {

            // get terminal
            CardTerminal terminal = terminals.get(i);

            try {
                if (terminal.isCardPresent()) {
                    card = terminal.connect("*"); //T=0, T=1 or T=CL(not needed)
                }
            } catch (CardException e) {

                System.out.println("Exception catched: " + e.getMessage());
                card = null;
            }
        }
        return card;
    }

    /**
     * Este método nos permite saber el tipo de tarjeta que estamos leyendo del
     * Terminal, a partir del ATR de ésta.
     *
     * @param atrCard ATR de la tarjeta que estamos leyendo
     * @return tipo de la tarjeta. 1 si es DNIe, 2 si es Starcos y 0 para los
     * demás tipos
     */
    private boolean esDNIe(byte[] atrCard) {
        int j = 0;
        boolean found = false;

        //Es una tarjeta DNIe?
        if (atrCard.length == dnie_v_1_0_Atr.length) {
            found = true;
            while (j < dnie_v_1_0_Atr.length && found) {
                if ((atrCard[j] & dnie_v_1_0_Mask[j]) != (dnie_v_1_0_Atr[j] & dnie_v_1_0_Mask[j])) {
                    found = false; //No es una tarjeta DNIe
                }
                j++;
            }
        }

        if (found == true) {
            return true;
        } else {
            return false;
        }

    }
    
   /* public  void conectaBD(){
        String contraseña;
        Scanner leer= new Scanner(System.in);
        System.out.println("Escribe la contraseña:");
        contraseña=leer.nextLine();
        contrasena=contraseña;
        Desktop.getDesktop().browse(new URI("http://localhost/dnie/autentica.php?user="dusuario+"password"+contrasena"&dni"+DNI+"password"+contrasena));
    }*/
}
