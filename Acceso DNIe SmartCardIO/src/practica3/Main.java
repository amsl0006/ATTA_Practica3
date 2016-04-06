package practica3;



import java.awt.Desktop;
import practica3.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
        System.out.println("La contraseña es:"+LeeEntrada);
        Desktop.getDesktop().browse(new URI(""+"http://localhost/dnie/autentica.php?user="+od.dusuario+"&dni="+od.DNI+"&password="+LeeEntrada));
      
    
    }

}
