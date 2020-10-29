package br.edu.ifrn.pi.javaofpserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifrn.pi.javaofpserver.dominio.Progresso;
import br.edu.ifrn.pi.javaofpserver.dominio.Questao;
import br.edu.ifrn.pi.javaofpserver.dominio.Status;

public class ProgressoDAO {
	
	public boolean enviarProgresso(Progresso progresso) {
		String queryInserir = "INSERT INTO Progresso (Estudante_nickname, Questao_id, Status_id, tentativas, tempo) VALUES (?,?,?,?,?);";
		  
        Connection con = Conexao.conectar();
  
        try {
  
            PreparedStatement comando = con.prepareStatement(queryInserir);
            comando.setString(1, progresso.getNickname());
            comando.setInt(2, progresso.getQuestaoId());
            comando.setInt(3, progresso.getStatusId());
            comando.setInt(4, progresso.getTentativas());
            comando.setInt(5, progresso.getTempo());
 
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
	
	public List<Progresso> receberProgressos(String nickname) {
		List<Progresso> progressos = new ArrayList<>();
		
		String query = "SELECT Conteudo.id, COUNT(Questao.id) as quantidade FROM Questao, Conteudo WHERE Conteudo.id = Questao.Conteudo_id GROUP BY Questao.Conteudo_id;";
		

		Connection con = Conexao.conectar();


		try {
			PreparedStatement comando = con.prepareStatement(query);
			
			ResultSet rSet = comando.executeQuery();
			
			while(rSet.next()){
				Progresso progresso = new Progresso();
				progresso.setNickname(nickname);
				progresso.setConteudoId(rSet.getInt("id"));
				progresso.setQuantidadeQuestoes(rSet.getInt("quantidade"));
				
				progressos.add(progresso);

			}
			
			for(Progresso progresso: progressos) {
				query = "SELECT Questao.Conteudo_id, Progresso.Estudante_nickname, count(Questao.Conteudo_id) as nQuestao, SUM(Progresso.Status_id) as certas FROM Questao, Progresso WHERE Questao.id = Progresso.Questao_id AND Progresso.Estudante_nickname = ? AND Questao.Conteudo_id = ? group by Questao.Conteudo_id;";

				comando = con.prepareStatement(query);

				comando.setString(1, progresso.getNickname());
				comando.setInt(2, progresso.getConteudoId());
				
				rSet = comando.executeQuery();
							
				if(rSet.next()){
					progresso.setnQuestoesCertas(rSet.getInt("certas"));
					progresso.setnStopQuestao(rSet.getInt("nQuestao"));
					
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}
		return progressos;
	}
	
	public List<Questao> buscarProgressoQuestoes(String nickname, int conteudoId){
		List<Questao> questoes = new ArrayList<>();
		
		String query = "SELECT enunciado, Status_id, Conteudo_id FROM (select enunciado, Status_id, Conteudo_id FROM Questao LEFT JOIN Progresso ON Questao_id = id and Estudante_nickname = ?) as progresso WHERE progresso.Conteudo_id = ?;";
		
		Connection con = Conexao.conectar();

		try {
			PreparedStatement comando = con.prepareStatement(query);
						
			comando.setString(1, nickname);
            comando.setInt(2, conteudoId);
            
            ResultSet rSet = comando.executeQuery();
			
			while(rSet.next()){
				Questao questao = new Questao();
				Status status = new Status();

				questao.setEnunciado(rSet.getString("enunciado"));
				status.setId(rSet.getInt("Status_id"));
				questao.setStatus(status);
				
				questoes.add(questao);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}
		return questoes;
	}
	
	public void deletarProgresso(String nickname, int idConteudo) {
		String queryInserir = "DELETE FROM Progresso WHERE Estudante_nickname = ? AND Questao_id IN (SELECT id FROM Questao WHERE Conteudo_id = ?);";
		  
        Connection con = Conexao.conectar();
  
        try {
  
            PreparedStatement comando = con.prepareStatement(queryInserir);
            comando.setString(1, nickname);
            comando.setInt(2, idConteudo);
 
            comando.executeUpdate();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
        finally {
            Conexao.desconectar();
        }
	}
	
	public double calcularPontuacao(String nickname, int idConteudo) {		
		double pontuacao = 0;
		
		String query = "SELECT ROUND(SUM(100*((3-tentativas)/3)*((30-tempo)/30)), 2) as pontos FROM Progresso, Questao WHERE Progresso.Questao_id = Questao.id AND Status_id = 1 AND Questao.Conteudo_id = ? AND  Estudante_nickname = ?;";

		Connection con = Conexao.conectar();

		try {
			//Calculando a pontuação total obtida em um conteúdo
			
			PreparedStatement comando = con.prepareStatement(query);

			comando.setInt(1, idConteudo);
			comando.setString(2, nickname);
			
			ResultSet rSet = comando.executeQuery();
			
			if(rSet.next()){
				pontuacao = rSet.getDouble("pontos");
				
			}
			
			//Aplicando pontuação do estudante
			
			query = "UPDATE Estudante SET pontuacao = pontuacao + ? WHERE nickname = ?;";
			
			comando = con.prepareStatement(query);

			comando.setDouble(1, pontuacao);
			comando.setString(2, nickname);
			
			comando.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}
		
		return pontuacao;
	}

}
