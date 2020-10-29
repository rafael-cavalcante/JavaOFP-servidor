package br.edu.ifrn.pi.javaofpserver.dominio;
import java.util.ArrayList;

public class Questao {
	private int id;
	private String nivel;
	private String enunciado;
	private ArrayList<Alternativa> alternativas;
	private Status status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public ArrayList<Alternativa> getAlternativas() {
		return alternativas;
	}
	public void setAlternativas(ArrayList<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	
}
