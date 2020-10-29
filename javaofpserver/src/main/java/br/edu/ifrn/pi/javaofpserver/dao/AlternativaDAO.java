package br.edu.ifrn.pi.javaofpserver.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
import br.edu.ifrn.pi.javaofpserver.dominio.Alternativa;
 
public class AlternativaDAO {
    public List<Alternativa> buscarAlternativas(int idConteudo) {
  
        ArrayList<Alternativa> alternativas = new  ArrayList<Alternativa>();
  
                String queryInserir = "SELECT id,descricao, Questao_id, correta FROM Alternativa WHERE Questao_id = ? ORDER BY RAND();";
  
        Connection con = Conexao.conectar();
  
        try {
  
            PreparedStatement comando = con.prepareStatement(queryInserir);
        comando.setInt(1, idConteudo);
 
            ResultSet rSet = comando.executeQuery();
  
            while(rSet.next()){
        Alternativa alternativa = new Alternativa();  
  
                alternativa.setId(rSet.getInt("id"));
                alternativa.setDescricao(rSet.getString("descricao"));
                alternativa.setQuestaoId(rSet.getInt("Questao_id"));
                alternativa.setAlternativaCorreta(rSet.getInt("correta"));
  
                alternativas.add(alternativa);
  
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
        finally {
            Conexao.desconectar();
        }
  
        return alternativas;
    } 
  
}
