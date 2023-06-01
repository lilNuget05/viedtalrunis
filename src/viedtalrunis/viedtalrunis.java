package viedtalrunis;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.*;
import java.util.Scanner;

public class viedtalrunis {
	//telefona dati
	public static String nummurs=null, model=null, owner=null, password=null;
	public static int numberOfApps = 0;
	public static Object[] appArray = new Object[10];
	
	
	
	public static void iegutTelefonaDatus() {
		// telefona dati tiek paņemti no faila
		try {
			File myObj = new File("phoneData.txt");
		    Scanner myReader = new Scanner(myObj);
		    String lineData = myReader.nextLine();
		    String[] splitData = lineData.split("/");
		    nummurs = splitData[0];
		    model = splitData[1];
		    owner = splitData[2];
		    password = splitData[3];
		    myReader.close();
		} catch (Exception e) {
			System.out.println("Error1: Problem with reading data from a file");
		}
	}
	public static void startetTelefonu() {
		// Tiek palaists telefons ar datiem
		JOptionPane.showMessageDialog(null, "Palaiž "+model+"!", "Viedtālrunis", 1);
		JOptionPane.showMessageDialog(null, "Telefons veiksmīgi palaists!", "Viedtālrunis", 1);
		// Pieprasa ievadīt telefona paroli
		prasitParoli();
		// Pārbaudīt cik ir saglabātas lietotnes
		int appNbr = getNuberOfApps();
		// piepilda masivu ar lietotnēm
		setAppArray(appNbr);
		
	}
	private static void setAppArray(int appNbr) {
		int appSize=0;
		try {
			File myObj = new File("apps.txt");
		    Scanner myReader = new Scanner(myObj);
		    String line;
		    String[] splitLine = null;
			for (int i=0;i<appNbr;i++) {
				line = myReader.nextLine();
				splitLine = line.split(":");
				appSize =Integer.parseInt(splitLine[1]);
				if(splitLine[3].compareTo("0")==0) {
					int line2 = Integer.parseInt(myReader.nextLine());
					Spele app = new Spele(appSize, splitLine[0], splitLine[2], line2);
					appArray[i] = app;
				}
				else {
					String line2 = myReader.nextLine();
					String[] line3 = line2.split(":");
					LinkedList<String> nummuri = new LinkedList<String>();
					for (String element : line3) {
						nummuri.add(element);
						
					}
					System.out.println(nummuri);
					SazinasLietotne app = new SazinasLietotne(appSize, splitLine[0], splitLine[2], nummuri);
					appArray[i] = app;
				}
			}
			myReader.close();
		} catch (Exception e) {
			System.out.println("Error3: Problem with reading data from a file");
		}
		
		
	}
	public static int getNuberOfApps() {
		
		FileReader myObj;
	    BufferedReader myReader = null; 
		
		try {
			 myObj = new FileReader("apps.txt");
		     myReader = new BufferedReader(myObj);
		} catch (Exception e) {
			System.out.println("Error2: Problem with reading data from a file");
		}
		     
		    String line=null;
		    int index=0;
		    try {
				while ((line = myReader.readLine()) != null)  {
					index++;
					  
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return index/2;
		
		
	}
	
	@SuppressWarnings("deprecation")
	public static void prasitParoli() {
		// Tiek izveidots  panelis telefona paroles/pin ievadīšanai
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter a password:");
		JPasswordField pass = new JPasswordField(10);
		String password1;
		boolean passwordCorrect = false;
		panel.add(label);
		panel.add(pass);
		// Tiek veikta paroles ievadīšana un pārbaude logā
		do {
				
			String[] options = new String[]{"OK", "Cancel"};
			int option = JOptionPane.showOptionDialog(null, panel, "Viedālrunis",
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[1]);
			if(option == 0) // pressing OK button
			{	
				password1 = pass.getText();
				String pass1 = new String(password1);
				if (pass1.compareTo(password) == 0)
				{
					passwordCorrect = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Nepareiza parole!", "Viedtālrunis", 0);
					pass.setText("");	
				}	
			}
			else { // pressing cancel 
				System.exit(0);
			}
		}while (passwordCorrect != true);
	}
	
	public static void main(String[] args) {
		//Lietotne virsklase = new Lietotne();
	
		// Tiek iegūti dati par pašu telefonu
		iegutTelefonaDatus();
	
		// Palaiž telefona startēšanu
		startetTelefonu();
	
		//Lietotne.addApps();
		//Tiek palaista programmas galvenā daļa
		String[] phoneOption = {"Lietotnes","Lādēt lietotni", "Izslēgt telefonu"};
	
		while(true) {
			String izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību tālrunī:", "Viedtālrunis", 1,null, phoneOption, phoneOption[0]);
			switch (izvele){
				case "Lietotnes": Lietotne.showApps(appArray); break;
				case "Lādēt lietotni": Lietotne.downloadApp(appArray); break;
				case "Izslēgt telefonu": System.exit(0);
			}
		}
	}
}