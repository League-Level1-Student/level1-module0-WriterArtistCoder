/*
 *    Copyright (c) The League of Amazing Programmers 2013-2017
 *    Level 1
 */

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PhotoQuiz {

	public static void main(String[] args) throws Exception {
		JFrame quizWindow = new JFrame();
		quizWindow.setVisible(true);
		quizWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // This will make sure the program exits when you
																	// close the window

		// 1. find an image on the internet, and put its URL in a String variable (from
		// your browser, right click on the image, and select “Copy Image Address”)
		String imgURL = "https://imgs.xkcd.com/comics/birdwatching.png";
		// 2. create a variable of type "Component" that will hold your image
		Component comp0 = createImage(imgURL);
		// 3. use the "createImage()" method below to initialize your Component

		// 4. add the image to the quiz window
		quizWindow.add(comp0);
		// 5. call the pack() method on the quiz window
		quizWindow.pack();
		// 6. ask a question that relates to the image
		String query0 = JOptionPane.showInputDialog("Who wrote this webcomic? (e.g. Abraham Lincoln)");
		// 7. print "CORRECT" if the user gave the right answer
		if (query0.equalsIgnoreCase("Randall Munroe")) {
			JOptionPane.showMessageDialog(null, "CORRECT");
		} else {
			// 8. print "INCORRECT" if the answer is wrong
			JOptionPane.showMessageDialog(null, "INCORRECT");
		}
		// 9. remove the component from the quiz window (you may not see the effect of
		// this until step 12)
		quizWindow.remove(comp0);
		// 10. find another image and create it (might take more than one line of code)
		Component comp1 = createImage("https://imgs.xkcd.com/comics/wall_art.png");
		quizWindow.add(comp1);
		// 5. call the pack() method on the quiz window
		quizWindow.pack();
		// 6. ask a question that relates to the image
		String query1 = JOptionPane.showInputDialog("Who is the character talking about getting older? (e.g. Megan)");
		// 7. print "CORRECT" if the user gave the right answer
		if (query1.equalsIgnoreCase("Cueball")) {
			JOptionPane.showMessageDialog(null, "CORRECT");
		} else {
			// 8. print "INCORRECT" if the answer is wrong
			JOptionPane.showMessageDialog(null, "INCORRECT");
		}
	}

	private static Component createImage(String imageUrl) throws MalformedURLException {
		URL url = new URL(imageUrl);
		Icon icon = new ImageIcon(url);
		JLabel imageLabel = new JLabel(icon);
		return imageLabel;
	}

	/* OPTIONAL */
	// *14. add scoring to your quiz
	// *15. make something happen when mouse enters image
	// (imageComponent.addMouseMotionListener())
}
