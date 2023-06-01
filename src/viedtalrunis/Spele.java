package viedtalrunis;
import javax.swing.*;

public class Spele extends Lietotne {
	public int highScore=0;
	public Spele(int appSize, String appName, String appIcon, int highScore) {
		super(appSize, appName, appIcon);
		this.highScore = highScore;
	}
	public void start() {
		this.loadApp();
		JOptionPane.showMessageDialog(null, "Tavs lielākais score ir: "+this.highScore);

		int score;
		score = (int)(Math.random()*100)+1;
		JOptionPane.showMessageDialog(null, "Tavs score ir: "+score);
		if (score > this.highScore) {
			this.highScore = score;
		}
	}
	

}
