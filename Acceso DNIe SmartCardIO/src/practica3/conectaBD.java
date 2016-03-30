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
import practica3.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class conectaBD {
    





	public  String DRIVER_MYSQL = "com.mysql.jdbc.Driver"; 
	public  String URL_MYSQL = "jdbc:mysql://localhost:3306/dniauth";
	public  Connection conn;
	
		public conectaBD(){
			cargarDriver();
			getConexion();
		}
	
		public void cargarDriver() {
			try {
				Class.forName(DRIVER_MYSQL);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	
		public void getConexion(){
			try {
				conn = DriverManager.getConnection(URL_MYSQL,"root","");
			} catch (SQLException e) {
				e.printStackTrace();
			} 	
		}
		
		public void cerrarconexion(){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public void insertUsuario(String id, String dusuario, String password, String completname) {
				getConexion();
				String sql="INSERT INTO users (id,user,password,dni) VALUES (?,?,?,?);";
				try {
				java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, dusuario);
				stmt.setString(3, password);
				stmt.setString(4, completname);
				stmt.execute();
				stmt.close();
				conn.close();
				}catch (SQLException e) {e.printStackTrace();}
				}
		
	
	/*	public boolean existeUsu(String mail,String pass){
			String aux="";
			boolean res=false;
			ResultSet rs = null;
			String sql="SELECT CONTRASEÑA FROM usuarios WHERE NOMBRE like ?";
			getConexion();
			try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mail);
			rs = stmt.executeQuery();
			while (rs.next()){
			aux=rs.getString(1);
			if(aux.equals(pass))
				res=true;
			else
				res=false;
			}
			rs.close(); stmt.close();
			conn.close();
			} catch (SQLException e) { e.printStackTrace();}
			return res;
			}
		
		
		public boolean pagado(String mail){
			String aux="";
			boolean res=false;
			ResultSet rs = null;
			String sql="SELECT PAGO FROM usuarios WHERE NOMBRE like ?";
			getConexion();
			try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mail);
			rs = stmt.executeQuery();
			while (rs.next()){
			aux=rs.getString(1);
			if(aux.equals("Completed"))
				res=true;
			else
				res=false;
			}
			rs.close(); stmt.close();
			conn.close();
			} catch (SQLException e) { e.printStackTrace();}
			return res;
			}
		
		public void pagar(String mail, String status) {
			getConexion();
			String sql="UPDATE usuarios SET PAGO=? WHERE NOMBRE like ?;";
			try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, status);
			stmt.setString(2, mail);
			stmt.execute();
			stmt.close();
			conn.close();
			}catch (SQLException e) {e.printStackTrace();}
			}
		
		public boolean autenticado(String mail){
			String aux="";
			boolean res=false;
			ResultSet rs = null;
			String sql="SELECT AUTENTICADO FROM usuarios WHERE NOMBRE like ?";
			getConexion();
			try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mail);
			rs = stmt.executeQuery();
			while (rs.next()){
			aux=rs.getString(1);
			if(aux==null||aux.equals("No"))
				res=false;
			else
				res=true;
			}
			rs.close(); stmt.close();
			conn.close();
			} catch (SQLException e) { e.printStackTrace();}
			return res;
			}
		
		public void insertCodigo(String mail,String cod) {
			getConexion();
			String sql="UPDATE usuarios SET CODIGO=? WHERE NOMBRE like ?;";
			try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cod);
			stmt.setString(2, mail);
			stmt.execute();
			stmt.close();
			conn.close();
			}catch (SQLException e) {e.printStackTrace();}
			}
		
		public void autenticar(String mail) {
			getConexion();
			String sql="UPDATE usuarios SET AUTENTICADO='SI' where NOMBRE like ?;";
			try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mail);
			
			stmt.execute();
			stmt.close();
			conn.close();
			}catch (SQLException e) {e.printStackTrace();}
			}
		
		public boolean autenticacion(String mail, String cod){
			String aux="";
			boolean res=false;
			ResultSet rs = null;
			String sql="SELECT CODIGO FROM usuarios WHERE NOMBRE like ?";
			getConexion();
			try {
			java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, mail);
			rs = stmt.executeQuery();
			while (rs.next()){
			aux=rs.getString(1);
			if(aux.equals(cod))
				res=true;
			else
				res=false;
			}
			rs.close(); stmt.close();
			conn.close();
			} catch (SQLException e) { e.printStackTrace();}
			return res;
			}
		
	*/
}
