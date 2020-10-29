package br.edu.ifrn.pi.javaofpserver.ws;
 
    import java.util.List;
    import javax.ws.rs.GET;
    import javax.ws.rs.Path;
    import javax.ws.rs.PathParam;
    import javax.ws.rs.Produces;
    import javax.ws.rs.core.MediaType;
     
    import br.edu.ifrn.pi.javaofpserver.dao.AlternativaDAO;
    import br.edu.ifrn.pi.javaofpserver.dominio.Alternativa;
     
    @Path("alternativas")
    public class AlternativaWS {

        @GET
        @Path("/{questaoId}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Alternativa> buscarAlternativa(@PathParam("questaoId") int id) {
        	System.out.println("Chamando o método buscarAlternativas()..."+id);
            AlternativaDAO alternativaDAO = new AlternativaDAO();
              
            return alternativaDAO.buscarAlternativas(id);
        }
      
    }