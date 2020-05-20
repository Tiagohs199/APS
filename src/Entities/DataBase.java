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
import java.util.InputMismatchException;
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
						bw.write(ra + "," + (Character.toUpperCase(nome.charAt(0))+nome.substring(1)));  // Character.toUpperCase(string.charAt(0)) + string.substring(1);
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
		
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------");
		
	
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(destiny()+ "\\Database\\Curso.csv",true))){
			
			System.out.print("Digite o nome do curso, tipo e ano: ");
			String nome = sc.next();
			String tipo = sc.next();
			int ano = sc.nextInt();
			if(nome.matches("[A-Za-z]*")) {
				bw.write(nome.toUpperCase()+","+Nivel.valueOf(tipo.toUpperCase())+","+ano);
				bw.newLine();
				System.out.println("Adicionado com sucesso!!");
		}else {
			System.out.println("!!!Nome de curso invalido!!!");
		}
			
		}catch(DomainException e) {
			e.printStackTrace();
			System.out.println("Lista vazia");
			}catch (IOException e) {
				e.printStackTrace();
			}catch(IllegalArgumentException e) {
				System.out.println("-------------------");
				System.out.println("!!!!Argumento invalido!!!!");
				System.out.println("-------------------");
				menu.inicial();
			}catch (InputMismatchException e) {
				System.out.println("!!Ano deve conter apenas numeros!!");
			}
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
			String date = fields[2];
			
			list.add(new Curso(nome, Nivel.valueOf(nivel),date));
			itemCsv = br.readLine();
			
		}
		return list;
	}catch (IOException e) {
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

			if (verifyCurso(curso.toUpperCase())) {
				System.out.println("verify curso ok");
				if(!(verifyNota(ra) && verifyNota1(curso.toUpperCase()))) {
				try (BufferedWriter bw = new BufferedWriter(
						new FileWriter(destiny() + "\\Database\\Notas.csv", true))) {

					System.out.print("Digite as notas (NP1,NP2,REP,EX)");
					Double np1 = sc.nextDouble();
					Double np2 = sc.nextDouble();
					Double rep = sc.nextDouble();
					Double ex = sc.nextDouble();

					bw.write(ra + "," + curso.toUpperCase() + "," + np1 + "," + np2 + "," + rep + "," + ex);
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
				}else {
					System.out.println("!!!Ra e curso ja cadastrado!!!");
				}
			}else {
				System.out.println("!!!Curso inexistente!!!");
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
 		Curso cur =new Curso(curso.toUpperCase());
 		 SimpleDateFormat sdf =new SimpleDateFormat("yyyy");
 		try (BufferedReader br = new BufferedReader(new FileReader(destiny()+ "\\Database\\Curso.csv"))) {
 			String itemCsv = br.readLine();
 			 Stream<String> str = br.lines();
 			 
 			Stream<Curso> cursos = str.map(nome -> { 
 				 String fields[] = nome.split(",");
 				
 				return new Curso(fields[0],Nivel.valueOf(fields[1]), fields[2]);
 			 });
 			
 			Stream<Curso> cursoFiltrados = cursos.filter(nome -> nome.equals(cur));
 			return cursoFiltrados.findFirst().get();
 			
 		}catch( IOException e) {
 			System.out.println("ERROR");
		} catch (NoSuchElementException e) {
			System.out.println("Not found");
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
 		return null;
 	}
 	
 	public List<Nota> returnHistorico(String id) {
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
 				
 				if(ra.equals(id) && verifyNota1(curso.toUpperCase())) {
 				  
 					list.add(new Nota(np1,np2,ex,rep,returnCurso(curso),returnAluno(ra)));
 					itemCsv = br.readLine();
 				}else {
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
 	public boolean verifyNota(String id) {	
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
 	public boolean verifyNota1(String id) {	
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(destiny() + "\\Database\\Notas.csv"))) {

			br.readLine();
			String line;
			while((line = br.readLine()) != null) {
				
				list.add(line.split(",")[1]);
			
			}
			return list.contains(id);
		} catch (IOException e) {
			System.out.println("ERROR");
		} catch (NoSuchElementException e) {
			
		}
		return false;
	}
 	public List<Nota> returnHistoricoCurso(String id) {
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
 				
 				if(verifyNota(ra) && curso.equals(id.toUpperCase())) {
 				  
 					list.add(new Nota(np1,np2,ex,rep,returnCurso(curso),returnAluno(ra)));
 					itemCsv = br.readLine();
 				}else {
 					itemCsv = br.readLine();
 				}
 			}
 			return list;
 		}catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		return null;
	}
 	
 	
 	
 	
 	

}
