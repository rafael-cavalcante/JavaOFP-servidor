package br.edu.ifrn.pi.javaofpserver.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifrn.pi.javaofpserver.dao.ConteudoDAO;
import br.edu.ifrn.pi.javaofpserver.dao.LinkDAO;
import br.edu.ifrn.pi.javaofpserver.dominio.Conteudo;

@Path("conteudos")
public class ConteudoWS {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conteudo> buscarConteudos() {
		System.out.println("Chamando o método buscarConteúdos()...");
		ConteudoDAO conteudo = new ConteudoDAO();
		LinkDAO links = new LinkDAO();
		
		for(Conteudo c: conteudo.buscarConteudos()) {
			c.setLinks(links.buscarLinks(c.getId()));
		}
		
		return conteudo.buscarConteudos();

	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Conteudo buscarConteudo(@PathParam("id") int id) {
		System.out.println("Chamando o método buscarConteúdos()...");
		ConteudoDAO conteudo = new ConteudoDAO();
		return conteudo.buscarConteudo(id);

	}

}
