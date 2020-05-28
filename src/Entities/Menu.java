package Entities;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
	public Menu() {
	}
	public void inicial() {
		DataBase data = new DataBase();
		Scanner sc = new Scanner(System.in);
		try {
		System.out.printf("---Menu inicial---%n"
				          +"--Add Aluno--(1)%n"
				          +"--Add Curso--(2)%n"
				          +"--Add notas--(3)%n"
				          +"--Consulta alunos--(4)%n"
				          +"--Consulta Cursos--(5)%n"
				          +"--Consulta Id aluno--(6)%n"
				          +"--Consulta Id Curso--(7)%n"
				          +"--Sair--(0)");

		int n = sc.nextInt();
		
		switch(n) {
		case 1:
			data.addAluno();
		case 2:
			data.addCurso();
		case 3:
			data.addNota();
			back();
		case 4:
			List<Aluno> lisAluno = data.getAllAluno();
			 lisAluno.forEach(aluno -> System.out.println(aluno));
			back();
		case 5:
			List<Curso> lisCurso = data.getAllCurso();
			lisCurso.forEach(curso -> System.out.println(curso));
			back();	
		case 6:
			System.out.print("Digite o Ra do aluno: ");
			String id = sc.next();
			List<Nota> listNota = data.returnHistorico(id);
			listNota.forEach(nota -> System.out.println(nota));
			back();
		case 7:
			System.out.print("Digite o Curso: ");
			String cur = sc.next();
			List<Nota> listCur = data.returnHistoricoCurso(cur);
			listCur.forEach(nota -> System.out.println(nota));
			back();
		case 9 :
			System.out.print("Digite o Ra do aluno: ");
			String id1 = sc.next();
			String id2 = sc.next();
			System.out.println(data.verifyNota1(id1,id2));
		case 0:
			System.exit(0);
		}
		}catch (InputMismatchException e) {
			System.out.println("!!Valor invalido!!");
		}catch (NoSuchElementException e) {
			System.out.println("!!!Valor não encontrado!!!");
		}
		sc.close();
	}
	
	public void back() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------");
		System.out.println("Deseja voltar ao Menu inicial? (y) "
				+ "ou qualquer tecla para sair");
		char c = sc.next().charAt(0);
		if(c == 'y' || c == 'Y') {
		inicial();
		}else {
			System.exit(0);
		}
	}
}
