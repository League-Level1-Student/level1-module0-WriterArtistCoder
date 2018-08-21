import java.applet.AudioClip;
import java.awt.event.*;

import javax.swing.*;

public class SoundFXMachine {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		JButton b1 = new JButton("Drum");
		addSoundListener(frame, panel, b1, "drum.wav");
		
		JButton b2 = new JButton("Cymbal");
		addSoundListener(frame, panel, b2, "cymbal.wav");
		
		JButton b3 = new JButton("Dog");
		addSoundListener(frame, panel, b3, "labrador-barking-daniel_simon.wav");
	}
	
	public static void addSoundListener(JFrame frame, JPanel panel, JButton b, String fileName) {
		panel.add(b);
		frame.pack();
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SoundFXMachine().playSound(fileName);
			}
		});
	}

	private void playSound(String fileName) {
		AudioClip sound = JApplet.newAudioClip(getClass().getResource(fileName));
		sound.play();
	}

}
