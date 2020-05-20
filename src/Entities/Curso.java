package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Entities.enums.Nivel;

public class Curso {
	private String nome;
	private Nivel nivel;
	private String ano;
	
	private List<Double> notas;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	
	public Curso(String nome) {
		this.nome = nome;
	}
	public Curso(String nome, Nivel nivel) {
		this.nome = nome;
		this.nivel = nivel;
	}
	public Curso(String nome, Nivel nivel, String ano) {
		
		this.nome = nome;
		this.nivel = nivel;
		this.ano = ano;
	}

	public String getCurso() {
		return nome;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public String getAno() {
		return ano;
	}
	public List<Double> getNotas() {
		return notas;
	}
	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}
	
	
	
	@Override
	public String toString() {
		return "Curso =" + nome + ", nivel = "+ getNivel()+" Date ="+getAno();
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
