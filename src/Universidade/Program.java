package Universidade;

import java.util.Locale;
import java.util.Scanner;

import Entities.Menu;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Menu menu = new Menu();
		
		menu.inicial();
		

		sc.close();

	}

}
