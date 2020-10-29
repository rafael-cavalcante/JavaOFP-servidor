package br.edu.ifrn.pi.javaofpserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import br.edu.ifrn.pi.javaofpserver.dominio.Questao;
 
public class QuestaoDAO {
    public List<Questao> buscarQuestao(int idConteudo) {  
 
        ArrayList<Questao> questoes = new ArrayList<Questao>();   
 
        String queryInserir = "SELECT id, enunciado, Conteudo_id, nivel_id FROM Questao WHERE Conteudo_id = ?";
 
 
        Connection con = Conexao.conectar();
 
        try {
 
 
            PreparedStatement comando = con.prepareStatement(queryInserir);
            comando.setInt(1, idConteudo);
 
            ResultSet rSet = comando.executeQuery();
             
            while(rSet.next()){
                Questao questao = new Questao();
                 
                questao.setId(rSet.getInt("id"));
                questao.setEnunciado(rSet.getString("enunciado"));
 
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
     
    public List<Questao> buscarQuestoes() {   
 
        ArrayList<Questao> questoes = new ArrayList<Questao>();   
 
        String queryInserir = "SELECT id, enunciado, Conteudo_id, nivel_id FROM Questao";
 
 
        Connection con = Conexao.conectar();
 
        try {
 
 
            PreparedStatement comando = con.prepareStatement(queryInserir);
 
            ResultSet rSet = comando.executeQuery();
 
            while(rSet.next()){
                Questao questao = new Questao();
                 
                questao.setId(rSet.getInt("id"));
                questao.setEnunciado(rSet.getString("enunciado"));
 
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
     
}
