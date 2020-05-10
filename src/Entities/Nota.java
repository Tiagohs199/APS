package Entities;

public class Nota {
	private Double NP1,NP2,exame,reposicao;
	private Aluno aluno;
	private Disciplina disciplina;
	
	public Nota() {
	}

	public Nota(Double nP1, Double nP2, Double exame, Double reposicao, Aluno aluno, Disciplina disciplina) {
		super();
		NP1 = nP1;
		NP2 = nP2;
		this.exame = exame;
		this.reposicao = reposicao;
		this.aluno = aluno;
		this.disciplina = disciplina;
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
		return "Nota " + NP1 + ", NP2=" + NP2 + ", exame=" + exame + ", reposicao=" + reposicao + ", aluno="
				+ aluno + ", disciplina=" + disciplina;
	}
	
	
}
