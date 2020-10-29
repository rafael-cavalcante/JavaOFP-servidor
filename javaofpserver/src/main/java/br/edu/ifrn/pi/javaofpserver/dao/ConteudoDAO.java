package br.edu.ifrn.pi.javaofpserver.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.edu.ifrn.pi.javaofpserver.dominio.Conteudo;
import br.edu.ifrn.pi.javaofpserver.dao.LinkDAO;

public class ConteudoDAO {

	public Conteudo buscarConteudo(int id) {	

		Conteudo conteudo = new Conteudo();  

		String query = "SELECT id, nome, descricao FROM Conteudo WHERE Conteudo.id = ?";

		Connection con = Conexao.conectar();

		try {

			PreparedStatement comando = con.prepareStatement(query);
			comando.setInt(1, id);

			ResultSet rSet = comando.executeQuery();
			
			if(rSet.next()){
								
				conteudo.setId(rSet.getInt("id"));
				conteudo.setNome(rSet.getString("nome"));
				conteudo.setDescricao(rSet.getString("descricao"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return conteudo;
	}
	
	public List<Conteudo> buscarConteudos() {	

		List<Conteudo> conteudos = new ArrayList<Conteudo>();
		LinkDAO links = new LinkDAO();

		String query = "SELECT id, nome, descricao FROM Conteudo";


		Connection con = Conexao.conectar();


		try {


			PreparedStatement comando = con.prepareStatement(query);

			ResultSet rSet = comando.executeQuery();
			
			while(rSet.next()){
				
				Conteudo conteudo = new Conteudo();
				
				conteudo.setId(rSet.getInt("id"));
				conteudo.setNome(rSet.getString("nome"));
				conteudo.setDescricao(rSet.getString("descricao"));
				conteudo.setLinks(links.buscarLinks(conteudo.getId()));
				
				conteudos.add(conteudo);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return conteudos;
	}


}
