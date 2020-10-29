package br.edu.ifrn.pi.javaofpserver.dominio;

import java.util.List;
import java.util.ArrayList;

public class Estudante {
	private String nickname;
	private String senha;
	private String email;
	private int foto;
	private long ultimoAcesso;
	private double pontuacao;
	private List<Conteudo> conteudo = new ArrayList<>();
	
	public Estudante () {
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFoto() {
		return foto;
	}
	public void setFoto(int foto) {
		this.foto = foto;
	}
	public long getUltimoAcesso() {
		return ultimoAcesso;
	}
	public void setUltimoAcesso(long ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	public double getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}
	public List<Conteudo> getConteudo() {
		return conteudo;
	}
	public void setConteudo(List<Conteudo> conteudo) {
		this.conteudo = conteudo;
	}
	
}
