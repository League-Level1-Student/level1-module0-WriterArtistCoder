import java.util.Random;
import org.jointheleague.graphical.robot.*;
public class Houses {
	public static void main(String[] args) {
		Robot bob = new Robot(); // As in Bob the Builder
		String[] heights = {"small", "small", "small", "medium", "medium", "medium", "large", "large", "large"};
		bob.miniaturize();
		bob.moveTo(0, 500);
		for (int i = 0; i < 9; i++) {
			int randR = new Random().nextInt(256);
			int randB = new Random().nextInt(256);
			drawHouse(bob, randR, 0, randB, heights[i]);
		}
	}
	
	public static void drawHouse(Robot bot, int r, int g, int b, String heightStr) {
		// Setup
		bot.setSpeed(500);
		bot.penDown();
		int height;
		boolean point = true;
		if (heightStr.equalsIgnoreCase("small")) {
			height = 60;
		} else if (heightStr.equalsIgnoreCase("medium")) {
			height = 120;
		} else if (heightStr.equalsIgnoreCase("large")) {
			height = 250;
			point = false;
		} else {
			height = 0;
		}
		// Draw grass
		bot.setPenColor(0, 100, 0);
		bot.turn(90);
		bot.move(20);
		// Draw house
		bot.setPenColor(r, g, b);
		bot.turn(-90); // Build left wall
		bot.move(height);
		
		if (point) {
			bot.turn(45); // Build left roof
			bot.move(25);
		
			bot.turn(90); // Build right roof
			bot.move(25);
		
			bot.turn(45); // Build right wall
		} else {
			bot.turn(90);
			bot.move(60);
			bot.turn(90);
		}
		bot.move(height);
		// Reset
		bot.turn(180);
	}
}
