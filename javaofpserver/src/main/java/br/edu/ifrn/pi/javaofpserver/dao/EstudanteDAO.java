package br.edu.ifrn.pi.javaofpserver.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifrn.pi.javaofpserver.dominio.Estudante;

public class EstudanteDAO {
	public boolean solicitarRegistro(Estudante estudante) {
		String queryInserir = "INSERT INTO Estudante (nickname, senha, foto, ultimoacesso, email, pontuacao) VALUES (?,?,?,?,?,?);";

		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(queryInserir);
			
			comando.setString(1, estudante.getNickname());
			comando.setString(2, estudante.getSenha());
			comando.setInt(3, estudante.getFoto());
			comando.setDate(4, new Date (System.currentTimeMillis()));
			comando.setString(5, estudante.getEmail());
			comando.setDouble(6, estudante.getPontuacao());

			comando.executeUpdate();

			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {

			Conexao.desconectar();
		}

		return false;
	}
	
	public Estudante solicitarLogin(String nickname, String senha) {
		Estudante estudante = new Estudante();
				
		String query = "SELECT nickname, senha, email, foto, pontuacao, ultimoacesso FROM Estudante WHERE nickname = ? AND senha = ?;";
		
		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(query);

			comando.setString(1, nickname);
			comando.setString(2, senha);

			ResultSet rSet = comando.executeQuery();

			if (rSet.next()) {
				estudante.setNickname(rSet.getString("nickname"));
				estudante.setSenha(rSet.getString("senha"));
				estudante.setEmail(rSet.getString("email"));
				estudante.setFoto(rSet.getInt("foto"));
				estudante.setPontuacao(rSet.getDouble("pontuacao"));
								
				return estudante;
			}
			

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {

			Conexao.desconectar();
		}
		
		return null;
		
	}
	
	public void updateUltimoAcesso(String nickname) {				
		String queryInserir = "UPDATE Estudante SET ultimoacesso = ? WHERE nickname = ?;";

		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(queryInserir);
			
			comando.setDate(1, new Date (System.currentTimeMillis()));
			comando.setString(2, nickname);

			comando.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {

			Conexao.desconectar();
		}	
	}
	
	public boolean updatePerfil(String nickname, int perfil) {
		String queryInserir = "UPDATE Estudante SET foto = ? WHERE nickname = ?;";

		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(queryInserir);
			
			comando.setInt(1, perfil);
			comando.setString(2, nickname);

			comando.executeUpdate();

			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {

			Conexao.desconectar();
		}
		
		return false;
	}
	
}
