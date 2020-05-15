package Entities;

public class Nota  extends Aluno{
	private Double NP1,NP2,exame,reposicao;
	private Curso curso;
	private Aluno aluno;
	
		
	
	
	
	
	
	
	
	
	public Nota(Double nP1, Double nP2, Double exame, Double reposicao,String id, Curso cursos) {
		super(id);
		NP1 = nP1;
		NP2 = nP2;
		this.exame = exame;
		this.reposicao = reposicao;
		
	}
	public Double getNP1() {
		return NP1;
	}

	public void setNP1(Double nP1) {
		NP1 = nP1;
	}

	public Double getNP2() {
		return NP2;
	}

	public void setNP2(Double nP2) {
		NP2 = nP2;
	}

	public Double getExame() {
		return exame;
	}

	public void setExame(Double exame) {
		this.exame = exame;
	}

	public Double getReposicao() {
		return reposicao;
	}

	public void setReposicao(Double reposicao) {
		this.reposicao = reposicao;
	}
	private boolean foiAprovado() {
		Double media = ((NP1 + NP2) / 2);
		if (media >= 7) {
			return true;
		}else if (((media + exame) / 2) >= 5) {
			return true;	
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Aluno: Id = "+aluno.getId()+" Nome: "+aluno.getNome()+" Curso ="+curso+" Nota1 = " + NP1 + " Nota2 =" + NP2 + " Exame = " + exame + " Reposicao = " + reposicao;
	}
	
	
}
