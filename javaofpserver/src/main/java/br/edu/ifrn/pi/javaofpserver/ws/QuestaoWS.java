package br.edu.ifrn.pi.javaofpserver.ws;
 
 
import java.util.List;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import br.edu.ifrn.pi.javaofpserver.dao.QuestaoDAO;
import br.edu.ifrn.pi.javaofpserver.dominio.Questao;
 
@Path("questoes")
public class QuestaoWS {
     
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Questao> buscarQuestoes() {
    	System.out.println("Chamando o método buscarQuestões()...");
        QuestaoDAO questaoDAO = new QuestaoDAO();
         
        return questaoDAO.buscarQuestoes();
    }
    @GET
    @Path("/{questaoId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Questao> buscarQuestoes(@PathParam("questaoId") int id) {
    	System.out.println("Chamando o método buscarQuestões()... "+id);
        QuestaoDAO questaoDAO = new QuestaoDAO();
         
        return questaoDAO.buscarQuestao(id);
    }
 
}