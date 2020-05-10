package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import Entities.enums.Nivel;

public class Curso {
	private String nome;
	private Nivel nivel;
	private Date ano;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	
	public Curso() {
	}

	public Curso(String nome, Nivel nivel, Date ano) {
		super();
		this.nome = nome;
		this.nivel = nivel;
		this.ano = ano;
	}

	public String getCurso() {
		return nome;
	}

	public void setCurso(String nome) {
		this.nome = nome;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Curso =" + nome + ", nivel = " + nivel + ", ano = " + sdf.format(ano);
	}
	
}
