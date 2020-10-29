package br.edu.ifrn.pi.javaofpserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO {
	public List<String> buscarLinks(int conteudoId) {	

		List<String> links = new ArrayList<String>();

		String query = "SELECT link FROM Link WHERE Conteudo_id = ?";

		Connection con = Conexao.conectar();

		try {


			PreparedStatement comando = con.prepareStatement(query);
			comando.setInt(1, conteudoId);

			ResultSet rSet = comando.executeQuery();
			
			while(rSet.next()){
								
				links.add(rSet.getString("link"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return links;
	}

}
