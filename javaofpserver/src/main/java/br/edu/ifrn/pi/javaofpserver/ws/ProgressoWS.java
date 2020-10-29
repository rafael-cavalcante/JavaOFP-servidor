package br.edu.ifrn.pi.javaofpserver.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.pi.javaofpserver.dao.ProgressoDAO;
import br.edu.ifrn.pi.javaofpserver.dominio.Progresso;

@Path("progressos")
public class ProgressoWS {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addProgresso(Progresso progresso) {
		System.out.println("Chamando o método addProgresso()");
		ProgressoDAO progressoDAO = new ProgressoDAO();
		
		return progressoDAO.enviarProgresso(progresso);
	}
	
	@GET
	@Path("/{nickname}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Progresso> buscarProgresso(@PathParam("nickname") String nickname) {
		System.out.println("Chamando o método buscarProgresso()...");
		ProgressoDAO progresso = new ProgressoDAO();
		
		return progresso.receberProgressos(nickname);
	}
	
	@DELETE
	@Path("/{nickname},{idConteudo}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletarProgresso(@PathParam("nickname") String nickname, @PathParam("idConteudo") int idConteudo) {
		System.out.println("Chamando o método deletarProgresso()...");
		ProgressoDAO progresso = new ProgressoDAO();
		
		progresso.deletarProgresso(nickname, idConteudo);
	}
	
	@GET
	@Path("/{nickname},{idConteudo}")
	@Produces(MediaType.APPLICATION_JSON)
	public double calcularPontuacao(@PathParam("nickname") String nickname, @PathParam("idConteudo") int idConteudo) {
		System.out.println("Chamando o método calcularPontuacao()...");
		ProgressoDAO progresso = new ProgressoDAO();
		
		return progresso.calcularPontuacao(nickname, idConteudo);
	}
	
}
