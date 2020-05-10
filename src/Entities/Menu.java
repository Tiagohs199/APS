package Entities;

import java.util.Scanner;

public class Menu {
	public Menu() {
	}
	
	public void inicial() {
		DataBase data = new DataBase();
		Scanner sc = new Scanner(System.in);
		System.out.printf("---Menu inicial---%n"
				          +"--Add Aluno--(1)%n"
				          +"--Add Curso--(2)%n"
				          +"--Add notas--(3)%n"
				          +"--Consulta alunos--(4)%n"
				          +"--Consulta Cursos--(5)%n"
				          +"--Id aluno--(6)%n"
				          +"--Disciplina--(7)%n"
				          +"--Sair--");

		int n = sc.nextInt();
		
		switch(n) {
		case 1:
			data.addAluno();
		case 2:
			data.addCurso();
		case 3:
			break;
		case 4:
			data.getAllAluno();
		case 5:
			data.getAllCurso();
		}
	
		
		
		
		sc.close();
	}
	
	public void back() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------");
		System.out.println("Deseja voltar ao Menu inicial? (y/n)");
		char c = sc.next().charAt(0);
		if(c == 'y' || c == 'Y') {
		inicial();
		}else {
			System.exit(0);
		}
	}
}
