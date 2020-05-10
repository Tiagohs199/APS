package Entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Entities.enums.Nivel;

public class DataBase {
	
	public DataBase() {
	}
	
	Menu menu = new Menu();

	public String destiny() {
		
		String path = "C:\\Users\\tiago.henrique\\Desktop\\java\\APS\\src";
		File sourceFile = new File(path);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\Database").mkdir();

		return sourceFolderStr;
	}
	
	
	
	public void addAluno() {
	Scanner sc = new Scanner(System.in);
	System.out.println("-----------------------");
	System.out.print("Digite o numero de alunos a ser adicionado: ");
	int n = sc.nextInt();
	
	try(BufferedWriter bw = new BufferedWriter(new FileWriter(destiny() + "\\Database\\Aluno.csv",true))){
		for(int i=0; i<n;i++) {
			System.out.print("Digite o ra, nome");
			String ra = sc.next();
			String nome = sc.next();
			
			bw.write(ra+","+nome);
			bw.newLine();
			
		}
	}catch(IOException e) {
		e.printStackTrace();
		System.out.println("Lista vazia");
		}
	System.out.println("Adicionado com sucesso!!");
	menu.back();
	sc.close();
	}
	
	public void getAllAluno() {
		List<Aluno> list = new ArrayList<>();
	try (BufferedReader br = new BufferedReader(new FileReader(destiny()+ "\\Database\\Aluno.csv"))) {
		br.readLine();
		String itemCsv = br.readLine();
		
		while (itemCsv != null) {

			String fields[] = itemCsv.split(",");
		
			String ra = fields[0];
			String name = fields[1];
			
			list.add(new Aluno( ra,name));
			itemCsv = br.readLine();
		}
		
	}catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println("-------------------");
	for(Aluno li: list) {
		System.out.println(li.toString());
	}
	menu.back();
	}
	
	public void addCurso() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------");
		
	
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(destiny()+ "\\Database\\Curso.csv",true))){
			
			System.out.print("Digite o nome do curso, tipo e ano: ");
			String nome = sc.next();
			String tipo = sc.next();
			Date ano = sdf.parse(sc.next());
			
			bw.write(nome+","+Nivel.valueOf(tipo)+","+sdf.format(ano));
			bw.newLine();
				
			
		}catch(DomainException e) {
			e.printStackTrace();
			System.out.println("Lista vazia");
			}catch (IOException e) {
				e.printStackTrace();
			}catch (ParseException e) {
				e.printStackTrace();
			}
		System.out.println("Adicionado com sucesso!!");
		menu.back();
		sc.close();
		
	}
	public void getAllCurso() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		List<Curso> list = new ArrayList<>();
	try (BufferedReader br = new BufferedReader(new FileReader(destiny()+ "\\Database\\Curso.csv"))) {
		br.readLine();
		String itemCsv = br.readLine();
		
		while (itemCsv != null) {

			String fields[] = itemCsv.split(",");
		
			String nome = fields[0];
			String nivel = fields[1];
			Date date = sdf.parse(fields[2]);
			
			list.add(new Curso(nome, Nivel.valueOf(nivel),date));
			itemCsv = br.readLine();
		}
		
	}catch (IOException e) {
		e.printStackTrace();
	}catch (ParseException e) {
		e.printStackTrace();
	}
	System.out.println("-------------------");
	for(Curso li: list) {
		System.out.println(li.toString());
	}
	menu.back();
	}
	
public void addNota() {

		
		
		
	}

}
