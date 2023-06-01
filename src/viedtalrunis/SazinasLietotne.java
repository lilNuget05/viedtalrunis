package viedtalrunis;

import javax.swing.JOptionPane;
import java.util.LinkedList;

public class SazinasLietotne extends Lietotne {
	public LinkedList<String> nummurs = new LinkedList<String>();
	public SazinasLietotne(int appSize, String appName, String appIcon, LinkedList nummurs) {
		super(appSize, appName, appIcon);
		this.nummurs = nummurs;
	}
	public void start() {
		this.loadApp();
		String[] options = {"pierakstīt nummuru", "zvanīt"};
		String izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību leitotnē:", "Viedtālrunis", 1,null, options, options[0]);
		switch (izvele){
		 case "pierakstīt nummuru": 
			String numb = (String) JOptionPane.showInputDialog(null, "Nummurs: ", "Viedtālrunis");
			this.nummurs.add(numb);
		 	break;
		 case "zvanīt":
			 String[] options1 = new String[nummurs.size()];
			 int j=0;
			 for (String element : nummurs) {
				 options1[j] = element;
				 j++;
			 }
			 String izvele2 = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību leitotnē:","", 1,null, options1, options1[0]);
			 JOptionPane.showMessageDialog(null, "nevarēja sazvanīt "+izvele2+" nummuru!");
			 break;
		
		}
	}
}

