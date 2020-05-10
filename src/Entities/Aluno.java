package Entities;

public class Aluno {
	private String id;
	private String nome;
	private Curso curso;
	
	public Aluno() {
	}
	public Aluno(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public Aluno(String id, String nome, Curso curso) {
		super();
		this.id = id;
		this.nome = nome;
		this.curso = curso;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Aluno --id = " + id + "- nome = " + nome + "- curso = " + curso;
	}

	

	

}
