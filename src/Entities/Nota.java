package Entities;

import Entities.enums.Nivel;

public class Nota{
	private Double NP1,NP2,exame,reposicao;
	private Curso curso;
	private Aluno aluno;
	
		
	public Nota() {
	}
	
	public Nota(Double nP1, Double nP2, Double exame, Double reposicao, Curso cursos,Aluno aluno) {
		
		NP1 = nP1;
		NP2 = nP2;
		this.exame = exame;
		this.reposicao = reposicao;
		this.curso = cursos;
		this.aluno = aluno;
		
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	private boolean foiAprovado() {
		int refer;
		if(curso.getNivel()== Nivel.GRADUACAO) {
			 refer = 7;
		}else {
			 refer = 5;
		}
		Double media = ((NP1 + NP2) / 2);
		if (media >= 7) {
			return true;
		}else if (((media + exame) / 2) >= 5) {
			return true;	
		}else {
			return false;
		}	
	}
	private Double mediaF() {
		double rep, media;
		if(reposicao > NP1 || reposicao > NP2) {
			if(NP1 >NP2) {
				rep = reposicao;
				media = ((NP1+rep)/2);
				if (media >= 7) {
					return media;
				}else if (((media + exame) / 2) >= 5) {
					return media;	
				}
				
			}
			if(NP1<NP2) {
				rep = reposicao;
				media = ((NP2+rep)/2);
				if (media >= 7) {
					return media;
				}else if (((media + exame) / 2) >= 5) {
					return media;	
				}
				
			}
			if(NP1.equals(NP2)) {
				
				media =((NP2+reposicao)/2);
				if (media >= 7) {
					return media;
				}else{
					double ex =(media+exame)/2;
					
					return ex;	
				}
			}
		}else {
		 media = ((NP1 + NP2) / 2);
		
		if (media >= 7) {
			return media;
		}else if (((media + exame) / 2) >= 5) {
			return media;	
		}
		}
		return 0.0;
	}
	
	
	
	
	public  String ToString1() {
		if(foiAprovado()) {
			return "Aprovado";
		}
		return "Reprovado";
	}
	

	@Override
	public String toString() {
		return getAluno()+" "+ getCurso()+" Nota1 = " + NP1 + " Nota2 =" + NP2 + " Exame = " + exame 
				+ " Reposicao = " + reposicao+ " Aluno = "+ToString1()+" Media ="+mediaF();
	}
	
	
}
