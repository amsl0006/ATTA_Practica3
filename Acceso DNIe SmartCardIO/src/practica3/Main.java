package practica3;



import java.awt.Desktop;
import practica3.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author toni
 */
public class Main {
    private static String leer;
    private static String read;
    public static String GET_URL = "";
    public static final String USER_AGENT = "Mozilla/5.0";
public String getusuario(String usu){
	   return usu.substring(usu.lastIndexOf("=")+2, usu.length()-1);   
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        String usuario=null;
        //ByteArrayInputStream bais=null;

       /* ByteArrayInputStream bais=null;

       //read("cert.cer");
       
      // FileInputStream fis = new FileInputStream("cert.cer");
      
       

       //byte value[] = new byte[fis.available()];
      //   fis.read(value);
        //bais = new ByteArrayInputStream(value);

       byte value[] = new byte[fis.available()];
         fis.read(value);
        bais = new ByteArrayInputStream(value);
*/


        //TODO: Obtener los datos del DNIe
        ObtenerDatos od = new ObtenerDatos();
         od.LeerNIF();
        
        
        //System.out.println("NIF: "+nif);
        
        //TODO: Autenticarse en el servidor
        System.out.println("usuario: "+od.dusuario);
        System.out.println("Dni: "+od.DNI);
        System.out.println("introduce la contraseña:");
        
        
        String LeeEntrada="";
        //LeeEntrada = "";
        Scanner LeeScanner = new Scanner (System.in);
        LeeEntrada = LeeScanner.nextLine();
        
    
      
    String hash="";
        Encriptar encr = new Encriptar(); 
    hash= encr.cript(LeeEntrada);
    System.out.println("La contraseña es:"+hash);
       // Desktop.getDesktop().browse(new URI(""+"http://localhost/dnie/autentica.php?user="+od.dusuario+"&dni="+od.DNI+"&password="+hash));
    GET_URL = "http://localhost/dnie/autentica.php?user="+od.dusuario+"&dni="+od.DNI+"&password="+LeeEntrada;
   sendGET();
        System.out.println("Proceso finalizado");
 
    
}
 
   
 
    public static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
 
            // print result
            String auxerror = response.substring(610,639);
            String auxautentica = response.substring(610,620)+" "+response.substring(629,638);
            //System.out.println(response.substring(610,615));
            if(auxerror.startsWith("Error")) {
                System.out.println(auxerror);
                System.out.println("ERROR DE AUTENTICACIÓN");
        
            } else {
                System.out.println(auxautentica);
                System.out.println("OK");
            }
{
            //System.out.println(response.substring(610,639));
            
        }
            //String susuario = usuario.substring(0,1)+usuario.substring(15,23)+usuario.substring(23,24);
        } else {
            System.out.println("GET request not worked");
        }
 
    }

       

 
}