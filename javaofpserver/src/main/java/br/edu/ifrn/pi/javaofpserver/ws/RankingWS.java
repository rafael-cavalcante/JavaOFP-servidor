package br.edu.ifrn.pi.javaofpserver.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.pi.javaofpserver.dao.RankingDAO;
import br.edu.ifrn.pi.javaofpserver.dominio.Ranking;

@Path("ranking")
public class RankingWS {
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ranking buscarRanking() {
		System.out.println("Chamando o método buscarRanking()...");
        RankingDAO rankingDAO = new RankingDAO();
         
        return rankingDAO.buscarRanking();
    }

}
