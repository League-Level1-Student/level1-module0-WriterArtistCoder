import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CowSim {

	private long timeStep;
	private int money;
	private int cows;
	private int milk;
	private double milkProb;
	
	public String ENsellMilk = "How many gallons? (Type an integer, or 0 to cancel)\n Gallons are worth $3 each.";
	public String ENsellCows = "How many cows? (Type an integer, or 0 to cancel)\n Cows are worth $50 each.";
	public String ENbuyCows = "How many cows? (Type an integer, or 0 to cancel)\n Cows are worth $100 each.";
	
	public CowSim() {
		money = 100;
		cows = 1;
		milk = 0;
		milkProb = 0.02;
		timeStep = 100;
	}

	public int getMoney() {
		return money;
	}

	public int getCows() {
		return cows;
	}

	public int getMilk() {
		return milk;
	}
	
	public void sellMilk(int amount) {
		milk-=amount; // Decrease milk
		money+=(amount*3); // Increase money
	}
	
	public void sellCows(int amount) {
		cows-=amount; // Decrease cows
		money+=(amount*50); // Increase money
	}
	
	public void buyCows(int amount) {
		cows+=amount; // Decrease cows
		money-=(amount*100); // Increase money
	}

	public void update() {
		double seed = Math.random();
		double massNoMilkProb = Math.pow(1 - milkProb, cows); // Get probability of no milk from any cows
		double massMilk = seed - massNoMilkProb; // Get amount of milk from cows

		if (massMilk > 0) {
			milk += Math.ceil(massMilk * 5); // Add milk
		}
	}

	public void updateGraphics(CowSim simulator, JFrame ui) {
		// Import images
		String milkImgname = "carton.png";
		Icon milkImg;
		String cowImgname = "cow.png";
		Icon cowImg;
		String moneyImgname = "money.png";
		Icon moneyImg;
		try {
			milkImg = new ImageIcon(ImageIO.read(new MyFirstSwingGUI().getClass().getResourceAsStream(milkImgname)));
		} catch (IOException e) {
			milkImg = null;
		}

		try {
			cowImg = new ImageIcon(ImageIO.read(new MyFirstSwingGUI().getClass().getResourceAsStream(cowImgname)));
		} catch (IOException e) {
			cowImg = null;
		}
		
		try {
			moneyImg = new ImageIcon(ImageIO.read(new MyFirstSwingGUI().getClass().getResourceAsStream(moneyImgname)));
		} catch (IOException e) {
			moneyImg = null;
		}

		// UI setup
		JPanel ui0 = new JPanel(); // Tracker panel
		JPanel ui1 = new JPanel(); // Control panel

		JLabel ui00 = new JLabel(); // Milk tracker
		ui00.setIcon(milkImg); // Add milk icon
		JLabel ui01 = new JLabel(); // Cow tracker
		ui01.setIcon(cowImg); // Add cow icon
		JLabel ui02 = new JLabel(); // Money tracker
		ui02.setIcon(moneyImg); // Add money icon

		ui0.add(ui00); // Add trackers to panel
		ui0.add(ui01);
		ui0.add(ui02);
		
		JButton ui10 = new JButton("Sell milk");
		ui10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = 0;
				try {
					amount = Integer.parseInt(JOptionPane.showInputDialog(ENsellMilk));
				} catch (Exception ex) {
					
				}
				
				if (simulator.getMilk() >= amount) {
					simulator.sellMilk(amount);
				}
			}
		});
		
		JButton ui11 = new JButton("Sell cows");
		ui11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = 0;
				try {
					amount = Integer.parseInt(JOptionPane.showInputDialog(ENsellCows));
				} catch (Exception ex) {
					
				}
				
				if (simulator.getCows() >= amount) {
					simulator.sellCows(amount);
				}
			}
		});
		
		JButton ui12 = new JButton("Buy cows");
		ui12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = 0;
				try {
					amount = Integer.parseInt(JOptionPane.showInputDialog(ENbuyCows));
				} catch (Exception ex) {
					
				}
				
				if (simulator.getMoney() >= (amount*100)) {
					simulator.buyCows(amount);
				}
			}
		});
		
		ui1.add(ui10); // Add buttons to panel
		ui1.add(ui11);
		ui1.add(ui12);

		ui.add(ui0, BorderLayout.NORTH); // Add tracker panel to frame
		ui.add(ui1, BorderLayout.CENTER); // Add control panel to frame
		// Frame setup
		ui.setVisible(true);
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			simulator.update();
			ui00.setText("" + simulator.getMilk() + ""); // Print amount of milk
			ui01.setText("" + simulator.getCows() + ""); // Print amount of cows
			ui02.setText("" + simulator.getMoney() + ""); // Print amount of money
			ui.setSize(600, 600); // Pack frame

			// Wait timeStep
			try {
				Thread.sleep(timeStep);
			} catch (Exception e) {

			}
		}
	}

	public static void main(String args[]) {
		CowSim player = new CowSim();
		JFrame frame = new JFrame();
		
		player.updateGraphics(player, frame); // Run update loop forever
	}

}