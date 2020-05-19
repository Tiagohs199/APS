package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Entities.enums.Nivel;

public class Curso {
	private String nome;
	private Nivel nivel;
	private Date ano;
	private List<Double> notas;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	
	public Curso(String nome) {
		this.nome = nome;
	}
	public Curso(String nome, Nivel nivel) {
		super();
		this.nome = nome;
		this.nivel = nivel;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
