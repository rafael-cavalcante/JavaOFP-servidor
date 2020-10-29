package br.edu.ifrn.pi.javaofpserver.dao;

import br.edu.ifrn.pi.javaofpserver.dominio.Questao;

public class TesteProgresso {
	public static void main(String[] args) {
		ProgressoDAO progressoDAO = new ProgressoDAO();
		
		for(Questao questao: progressoDAO.buscarProgressoQuestoes("Rafael", 1)) {
			System.out.println(questao.getEnunciado());
		}
	}

}
