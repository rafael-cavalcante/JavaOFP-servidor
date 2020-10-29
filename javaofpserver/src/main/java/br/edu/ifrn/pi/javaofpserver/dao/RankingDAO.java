package br.edu.ifrn.pi.javaofpserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifrn.pi.javaofpserver.dominio.Ranking;
import br.edu.ifrn.pi.javaofpserver.dominio.Estudante;

public class RankingDAO {
	public Ranking buscarRanking() {	

		Ranking ranking = new Ranking();
		List<Estudante> estudantes = new ArrayList<Estudante>();

		String query = "SELECT nickname, pontuacao, foto FROM Estudante ORDER BY pontuacao DESC";


		Connection con = Conexao.conectar();


		try {


			PreparedStatement comando = con.prepareStatement(query);
			

			ResultSet rSet = comando.executeQuery();

			while(rSet.next()){
				
				Estudante estudante = new Estudante();
				
				estudante.setNickname(rSet.getString("nickname"));
				estudante.setPontuacao(rSet.getDouble("pontuacao"));
				estudante.setFoto(rSet.getInt("foto"));
				
				estudantes.add(estudante);

			}
			ranking.setLista(estudantes);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return ranking;
	}

}
