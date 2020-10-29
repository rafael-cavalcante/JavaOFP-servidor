package br.edu.ifrn.pi.javaofpserver.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.pi.javaofpserver.dao.EstudanteDAO;
import br.edu.ifrn.pi.javaofpserver.dominio.Estudante;

@Path("estudantes")
public class EstudanteWS {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean solicitarRegistro(Estudante estudante) {
		System.out.println("Chamando o método realizarRegistro()");
		EstudanteDAO estudanteDAO = new EstudanteDAO();

		return estudanteDAO.solicitarRegistro(estudante);
	}

	@GET
	@Path("/{nickname},{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudante solicitarLogin(@PathParam("nickname") String nickname, @PathParam("senha") String senha) {
		System.out.println("Chamando o médoto solicitarLogin()...");
		EstudanteDAO estudanteDAO = new EstudanteDAO();
		
		Estudante estudante = estudanteDAO.solicitarLogin(nickname, senha);
		
		if(estudante != null) {
			estudanteDAO.updateUltimoAcesso(nickname);
		}
		
		return estudante;
	}
	
	@PUT
	@Path("/{nickname},{perfil}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updatePerfil(@PathParam("nickname") String nickname, @PathParam("perfil") int perfil) {
		System.out.println("Chamando o método updatePerfil()");
		EstudanteDAO estudanteDAO = new EstudanteDAO();

		return estudanteDAO.updatePerfil(nickname, perfil);
	}

	
}
