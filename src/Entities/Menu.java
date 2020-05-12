package Entities;

import java.util.List;
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
				          +"--Sair--(0)");

		int n = sc.nextInt();
		
		switch(n) {
		case 1:
			data.addAluno();
		case 2:
			data.addCurso();
		case 3:
			break;
		case 4:
			List<Aluno> lisAluno = data.getAllAluno();
			for(Aluno li: lisAluno) {
			System.out.println(li.toString());
			}back();
		case 5:
			List<Curso> lisCurso = data.getAllCurso();
			for(Curso list:lisCurso) {
				System.out.println(list.toString());
			}back();
			
		case 6:
			System.out.println(data.returnAluno("1234"));
		case 0:
			System.exit(0);
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
