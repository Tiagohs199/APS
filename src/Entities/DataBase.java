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
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

import Entities.enums.Nivel;

public class DataBase {
	
	public DataBase() {
	}
	
	Menu menu = new Menu();
	Aluno aluno;

	public String destiny() {
		
		String path = System.getProperty("user.dir")+"\\src";
		File sourceFile = new File(path);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\Database").mkdir();

		return sourceFolderStr;
	}
	
	
	
	public void addAluno() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(destiny() + "\\Database\\Aluno.csv", true))) {
			System.out.print("Digite o numero de alunos a ser adicionado: ");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				System.out.print("Digite o ra, nome");
				String ra = sc.next();
				String nome = sc.next();
				
				if (nome.matches("[A-Za-z]*")) {
					if(!verifyAluno(ra)) {
						bw.write(ra + "," + nome);
						bw.newLine();
					}else {
						System.out.println("!!!Ra ja cadastrado!!!");
						menu.inicial();
					} 
				} else {
					System.out.println("!!!!Nome invalido!!!!");
					menu.inicial();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Lista vazia");
		}
		System.out.println("Adicionado com sucesso!!");
		menu.back();
		sc.close();
	}
	
	public List<Aluno> getAllAluno() {
		List<Aluno> list = new ArrayList<>();
	try (BufferedReader br = new BufferedReader(new FileReader(destiny()+ "\\Database\\Aluno.csv"))) {
		br.readLine();
		String itemCsv = br.readLine();
		
		while (itemCsv != null) {

			String fields[] = itemCsv.split(",");
		
			String ra = fields[0];
			String name = fields[1];
			
			list.add(new Aluno(ra,name));
			itemCsv = br.readLine();
		}
		return list;
	}catch (IOException e) {
		e.printStackTrace();
	}
	return null;
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
			}catch(IllegalArgumentException e) {
				System.out.println("-------------------");
				System.out.println("!!!!Argumento invalido!!!!");
				System.out.println("-------------------");
				menu.inicial();
			}
		System.out.println("Adicionado com sucesso!!");
		menu.back();
		sc.close();
		
	}
	public List<Curso> getAllCurso() {
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
		return list;
	}catch (IOException e) {
		e.printStackTrace();
	}catch (ParseException e) {
		e.printStackTrace();
	}return null;
	}
	
	public void addNota() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------");

		System.out.print("Digite o ra e o curso do aluno: ");
		String ra = sc.next();
		String curso = sc.next();

		if (verifyAluno(ra)) {
			System.out.println("verify aluno ok ");

			if (verifyCurso(curso)) {
				System.out.println("verify curso ok");
				try (BufferedWriter bw = new BufferedWriter(
						new FileWriter(destiny() + "\\Database\\Notas.csv", true))) {

					System.out.print("Digite as notas (NP1,NP2,REP,EX)");
					Double np1 = sc.nextDouble();
					Double np2 = sc.nextDouble();
					Double rep = sc.nextDouble();
					Double ex = sc.nextDouble();

					bw.write(ra + "," + curso + "," + np1 + "," + np2 + "," + rep + "," + ex);
					bw.newLine();

				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Lista vazia");
				} catch (NoSuchElementException e) {
					System.out.println("!!!Curso inexsistente!!!");
				}
				System.out.println("Adicionado com sucesso!!");
				menu.back();
				sc.close();
			}
		} else {
			System.out.println("!!!Usuario inexistente!!!");
		}
	}
	
	public Aluno returnAluno(String id) {
		Aluno alu = new Aluno(id);
		
		try (BufferedReader br = new BufferedReader(new FileReader(destiny() + "\\Database\\Aluno.csv"))) {

			String itemCsv = br.readLine();
			Stream<String> str = br.lines();
			
			Stream<Aluno> ids = str.map(aluno -> {
				String fields[] = aluno.split(",");
				return new Aluno(fields[0],fields[1]);

			});
			Stream<Aluno> alunoFiltrados = ids.filter(aluno -> aluno.equals(alu));
			return alunoFiltrados.findFirst().get();

		} catch (IOException e) {
			System.out.println("ERROR");
		} catch (NoSuchElementException e) {
		}
		return null;
	}

 	public Curso returnCurso(String curso) {
 		Curso cur =new Curso(curso);
 		
 		try (BufferedReader br = new BufferedReader(new FileReader(destiny()+ "\\Database\\Curso.csv"))) {
 			String itemCsv = br.readLine();
 			 Stream<String> str = br.lines();
 	
 			Stream<Curso> cursos = str.map(nome -> { 
 				 String fields[] = nome.split(",");
 				return new Curso(fields[0]);
 			 });
 			Stream<Curso> cursoFiltrados = cursos.filter(nome -> nome.equals(cur));
 			return cursoFiltrados.findFirst().get();
 			
 				
 			
 		}catch( IOException e) {
 			System.out.println("ERROR");
		} catch (NoSuchElementException e) {
			System.out.println("Not found");
		}
 		return null;
 	}
 	
 	
 	
 	
 	
 	public boolean verifyAluno(String id) {	
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(destiny() + "\\Database\\Aluno.csv"))) {

			br.readLine();
			String line;
			while((line = br.readLine()) != null) {
				
				list.add(line.split(",")[0]);
			}
			return list.contains(id);
		} catch (IOException e) {
			System.out.println("ERROR");
		} catch (NoSuchElementException e) {
			
		}
		return false;
	}
 	

 	public boolean verifyCurso(String id) {	
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(destiny() + "\\Database\\Curso.csv"))) {

			br.readLine();
			String line;
			while((line = br.readLine()) != null) {
				
				list.add(line.split(",")[0]);
			}
			return list.contains(id);
		} catch (IOException e) {
			System.out.println("ERROR");
		} catch (NoSuchElementException e) {
			
		}
		return false;
	}
 	
 	
 	public List<Nota> returnHistorico(String id) {
 		List<Nota> list = new ArrayList<>();
 		try (BufferedReader br = new BufferedReader(new FileReader(destiny()+ "\\Database\\Notas.csv"))) {
 			br.readLine();
 			String itemCsv = br.readLine();
 			
 			while (itemCsv != null) {
 			Aluno aluno = null;
 				String fields[] = itemCsv.split(",");
 			
 				String ra = fields[0];
 				String curso = fields[1];
 				Double np1 = Double.parseDouble(fields[2]);
 				Double np2 =  Double.parseDouble(fields[3]);
 				Double rep =  Double.parseDouble(fields[4]);
 				Double ex =  Double.parseDouble(fields[5]);;
 				
 				if(verifyValida(id)) {
 				  aluno =returnAluno(id);
 				
 				list.add(new Nota(np1,np2,ex,rep,ra,new Curso(curso)) );
 				itemCsv = br.readLine();
 				}
 			}
 			return list;
 		}catch (IOException e) {
 			e.printStackTrace();
 		}
 		return null;
	}
 	
 	

 	public boolean verifyValida(String id) {	
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(destiny() + "\\Database\\Notas.csv"))) {

			br.readLine();
			String line;
			while((line = br.readLine()) != null) {
				
				list.add(line.split(",")[0]);
			}
			return list.contains(id);
		} catch (IOException e) {
			System.out.println("ERROR");
		} catch (NoSuchElementException e) {
			
		}
		return false;
	}
 	
 	
 	
 	public List<Nota> getAllNota() {
		
		List<Nota> list = new ArrayList<>();
	try (BufferedReader br = new BufferedReader(new FileReader(destiny()+ "\\Database\\Notas.csv"))) {
		br.readLine();
		String itemCsv = br.readLine();
		
		while (itemCsv != null) {

			String fields[] = itemCsv.split(",");
		
			String ra = fields[0];
			String curso = fields[1];
			Double np1 = Double.parseDouble(fields[2]);
			Double np2 =  Double.parseDouble(fields[3]);
			Double rep =  Double.parseDouble(fields[4]);
			Double ex =  Double.parseDouble(fields[5]);;
			
			list.add(new Nota(np1,np2,ex,rep,ra,new Curso(curso)));
			itemCsv = br.readLine();
			
		}
		return list;
	}catch (IOException e) {
		e.printStackTrace();
	}return null;
	}
 	
 	
 	
 	
 	

}
