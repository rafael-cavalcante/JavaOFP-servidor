package br.edu.ifrn.pi.javaofpserver.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
	/*private static final String URL = "jdbc:mysql://localhost:3306/javaofpDB";
	private static final String USER = "javaofp";
	private static final String SENHA = "#@!javaofp";
	private static final String DRIVER = "com.mysql.jdbc.Driver"; 
	*/ 
	private static final String URL = "jdbc:mysql://localhost:3306/javaOFPdb";
	private static final String USER = "root";
	private static final String SENHA = "root";
	private static final String DRIVER = "com.mysql.jdbc.Driver"; 
 	
	private static Connection connection;
	
	public static Connection conectar(){
		
		try { 
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, SENHA);
			return connection;
		} catch (ClassNotFoundException e1) {
			System.out.println("Erro aqui: " + e1.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
			
	public static void desconectar(){
		
		try{
			if(connection != null){
				connection.close();
				connection = null;
			}
		}
		
		catch(SQLException ex){
			System.out.println(ex.getMessage());
			
		}
		
	}
}
