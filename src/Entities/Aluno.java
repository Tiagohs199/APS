package Entities;

public class Aluno implements Comparable<Aluno>{
	private String id;
	private String nome;
	
	public Aluno(String id) {
		this.id = id;
	}
	
	public Aluno(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	@Override
	public String toString() {
		return "Aluno - nome = " + nome+", Ra = " + id+","  ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Aluno o) {
		return  nome.compareTo(o.getNome());
	}
}
