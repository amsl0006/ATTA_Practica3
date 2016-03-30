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

       //ejemplo funcion enviar
   /*     function enviar() {
   //Recoger datos del formulario:
   reemail=document.datos.miemail.value; //Email escrito por el usuario
   recontra1=document.datos.micontra1.value; //Contraseña primera
   recontra2=document.datos.micontra2.value; //Contraseña segunda
   //Escribir la url para enviar los datos anteriores:
   ruta="ejemplo9.php" //ruta del archivo
   envio1="envioEmail="+reemail; //datos email
   envio2="envioContra1="+recontra1; //datos contraseña 1ª
   envio3="envioContra2="+recontra2; //datos contraseña 2ª
   url=ruta+"?"+envio1+"&"+envio2+"&"+envio3; //url para enviar
   ajax1=new ObjetoAjax; //instanciar objeto ObjetoAjax;
   ajax1.pedirTexto(url,"comp"); //método que devuelve texto en un id.
   }*/
        
       
       //TODO: introducir URI 
     //   Desktop.getDesktop().browse(new URI());
    
    }

}
