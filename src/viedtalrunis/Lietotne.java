package viedtalrunis;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;  
import java.awt.event.*;  


public class Lietotne implements ActionListener {
	//Lietotnes atribūti
	public int appSize;
	public String appName, appIcon;
	
	public Lietotne(int appSize, String appName, String appIcon) {
		this.appSize = appSize;
		this.appName = appName;
		this.appIcon = appIcon;
	}
	
	
	
	// set atributu metodes
	public void setAppName(String value) {
		this.appName = value;
	}
	public void setAppIcon(String value) {
		this.appIcon = value;
	}
	public void setAppSize(int value) {
		this.appSize = value;
	}
	
	
	public void loadApp() {
		JOptionPane.showMessageDialog(null, "Tiek sagatavota aplikāciju darbam!", "Viedtālrunis", 1);
		try {
			Thread.sleep(this.appSize);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Aplikācija lādējās "+this.appSize+" mili sekundes!", "Viedtālrunis", 1);


	}
	public void changeApp() {
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter app name:");
		JTextField field = new JTextField(10);
		panel.add(label);
		panel.add(field);
		
		String name="", icon="";
		int size=0;
		
		String[] options = new String[]{"OK", "Cancel"};
		do {
		int option = JOptionPane.showOptionDialog(null, panel, "Viedālrunis",
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[1]);
		if(option == 0) // pressing OK button
		{	
			 name = field.getText(); 
		}
		else { // pressing cancel 
			return;
		}
		} while(name.compareTo("") == 0);
		
		label.setText("Enter app size");
		field.setText("");
		
		do {
			int option = JOptionPane.showOptionDialog(null, panel, "Viedālrunis",
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[1]);
			if(option == 0) // pressing OK button
			{	
				try {
				 size = Integer.parseInt( field.getText()); 
				} catch (Exception e) {
					System.out.println("error4");
				}
			}
			else { // pressing cancel 
				return;
			}
			} while(size <= 0);
		Icon[] iconArray = new Icon[2];
		iconArray[0] = new ImageIcon("ikonas/174879.png");
		iconArray[1] = new ImageIcon("ikonas/cont.png");
		
		
		Icon myIcon = (Icon) JOptionPane.showInputDialog(null, "Choose icon:", "Viedtālrunis", 1,null, iconArray, iconArray[0]);
		icon =  myIcon.toString();
		
		this.setAppName(name);
		this.setAppIcon(icon);
		this.setAppSize(size);
		JOptionPane.showMessageDialog(null, "restarte dialog lodziņu lai redzētu izmaiņas!", "Viedtālrunis", 1);
		
	}
	public static void downloadApp(Object[] apps) {
		if (apps[9] != null) {
			JOptionPane.showMessageDialog(null, "Tev ir maksimums aplikāciju skaits!", "Viedtālrunis", 0);
			return;
		}
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter app name:");
		JTextField field = new JTextField(10);
		panel.add(label);
		panel.add(field);
		
		String name="", icon="";
		int size=0;
		
		String[] options = new String[]{"OK", "Cancel"};
		do {
		int option = JOptionPane.showOptionDialog(null, panel, "Viedālrunis",
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[1]);
		if(option == 0) // pressing OK button
		{	
			 name = field.getText(); 
		}
		else { // pressing cancel 
			return;
		}
		} while(name.compareTo("") == 0);
		
		label.setText("Enter app size");
		field.setText("");
		
		do {
			int option = JOptionPane.showOptionDialog(null, panel, "Viedālrunis",
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[1]);
			if(option == 0) // pressing OK button
			{	
				try {
				 size = Integer.parseInt( field.getText()); 
				} catch (Exception e) {
					System.out.println("error4");
				}
			}
			else { // pressing cancel 
				return;
			}
			} while(size <= 0);
		Icon[] iconArray = new Icon[2];
		iconArray[0] = new ImageIcon("ikonas/174879.png");
		iconArray[1] = new ImageIcon("ikonas/cont.png");

		
		Icon myIcon = (Icon) JOptionPane.showInputDialog(null, "Choose icon:", "Viedtālrunis", 1,null, iconArray, iconArray[0]);
		icon =  myIcon.toString();
		int appNbr = viedtalrunis.getNuberOfApps();
		
		String[] options2 = {"spele", "sazinasLietotne"};
		String izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību leitotnē:", "Viedtālrunis", 1,null, options2, options2[0]);
		
		if (izvele.compareTo("spele")==0) {
			Spele app = new Spele(size, name, icon, 0);
			apps[appNbr] = app;
		}
		else {
			SazinasLietotne app = new SazinasLietotne(size, name, icon, null);
			apps[appNbr] = app;
		}
		//Lietotne app = new Lietotne(size, name, icon);
		
		//apps[appNbr] = app;
		//saglaba failā
		saveFile(apps, false);
	}
	public static void saveFile(Object[] apps, boolean deleting) {
		
		Writer myObj = null;
	    int appNbr = viedtalrunis.getNuberOfApps();
		try {
			 myObj = new FileWriter("apps.txt", false);
		} catch (Exception e) {
			System.out.println("Error2: Problem with reading data from a file");
		}
		String line="";
		int index=0;
		
		if (deleting)
		for(Object el : apps) {
			
			if (el instanceof Spele) {
				Spele element = (Spele)el;
				
				index++;
				if(element == null)
					break;
				if(index <= appNbr) {
					line = line + element.appName+":"+element.appSize+":"+element.appIcon+":0\n"+element.highScore+"\n";
				}	
				
			}
			else {
				SazinasLietotne element = (SazinasLietotne)el;
				
				index++;
				if(element == null)
					break;
				if(index <= appNbr) {
					String line1="";
					for(String text :element.nummurs) {
						line1 += text+":";
					}
					line = line + element.appName+":"+element.appSize+":"+element.appIcon+":1\n"+line1+"\n";
				}	
			}
			
			
			
			
			
		}
		else
			for(Object el : apps) {
				if (el instanceof Spele) {
					Spele element = (Spele)el;
					
					index++;
					if(element == null)
						break;
					if(index <= appNbr+1) {
						line = line + element.appName+":"+element.appSize+":"+element.appIcon+":0\n"+element.highScore+"\n";
					}	
					
				}
				else {
					SazinasLietotne element = (SazinasLietotne)el;
					
					index++;
					if(element == null)
						break;
					if(index <= appNbr+1) {
						String line1="";
						for(String text :element.nummurs) {
							line1 += text+":";
						}
						line = line + element.appName+":"+element.appSize+":"+element.appIcon+":1\n"+line1+"\n";
					}	
				}
			}	
		System.out.print(line);
		try {
			myObj.write(line);
			myObj.close();
			System.out.println("check3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Object[] deleteApp(Object[] apps) {
		int index=0;
		for(Object element : apps) {
			if (this == element) {
				break;
			}
			index++;
		}
		
		for (int i = index; i < 9;i++) {
			apps[i] = apps[i+1];
			System.out.println(i);
		}
		apps[9] = null;
		
		return apps;
	}
	public static void showApps(Object[] apps) {
		
		
		JPanel pane = new JPanel();
		pane.setSize(400, 640);
		pane.setLayout(new GridLayout(2,5));
		
		int appNbr = viedtalrunis.getNuberOfApps();
		System.out.println(appNbr);
		int index=0;
		
		for(Object el : apps) {
			
			if(index != appNbr) {
			
				if (el instanceof Lietotne) {
					Lietotne element = (Lietotne)el;
					
					Icon icon = new ImageIcon(element.appIcon);
					JButton btn = new JButton(element.appName, icon);
					btn.setHorizontalTextPosition(JButton.CENTER);
					btn.setVerticalTextPosition(JButton.BOTTOM);
				    btn.addActionListener(element);  
					pane.add(btn);
				}
			
				
			
				
				index++;
			}
			
		}
		String option = "back";
		JOptionPane.showMessageDialog(null, pane, "Viedtālrunis", JOptionPane.PLAIN_MESSAGE);
	
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// šeit idejiski tiktu padots Lietotne objekts
		// tiktu dotas opcijas ko darīt ar lietoni - loadApp, changeApp, deleteApp.
		
		String[] options = {"izmantot aplikāciju", "mainīt aplikāciju", "dzēst aplikāciju"};
		String izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darību", "Viedtālrunis", 1, null, options, options[0]);
		switch(izvele) {
			case "izmantot aplikāciju":
				if (this instanceof Spele) {
					//System.out.println("spele");
					Spele elements = (Spele)this;
					elements.start();
					saveFile(viedtalrunis.appArray, false);
				}
				else {
					//System.out.println("sazina");
					SazinasLietotne elements = (SazinasLietotne)this;
					elements.start();
					saveFile(viedtalrunis.appArray, false);
				}
				break;
			case "mainīt aplikāciju": changeApp(); break;
			case "dzēst aplikāciju": viedtalrunis.appArray =  deleteApp(viedtalrunis.appArray);
				saveFile(viedtalrunis.appArray, true);
				JOptionPane.showMessageDialog(null, "restarte dialog lodziņu lai redzētu izmaiņas!", "Viedtālrunis", 1);
				break;
		}
		
		
	}
	
}
