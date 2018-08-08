import org.jointheleague.graphical.robot.*;
public class Houses {
	public static void main(String[] args) {
		Robot bob = new Robot(); // As in Bob the Builder
		bob.miniaturize();
		bob.moveTo(0, 500);
		for (int i = 0; i < 10; i++) {
			drawHouse(bob, 100, false);
		}
	}
	
	public static void drawHouse(Robot bot, int height, boolean point) {
		// Setup
		bot.setSpeed(500);
		bot.penDown();
		// Draw grass
		bot.setPenColor(0, 100, 0);
		bot.turn(90);
		bot.move(50);
		// Draw house
		bot.setRandomPenColor();
		bot.turn(-90); // Build left wall
		bot.move(height);
		
		if (point) {
			bot.turn(45); // Build left roof
			bot.move(50);
		
			bot.turn(90); // Build right roof
			bot.move(50);
		
			bot.turn(45); // Build right wall
		} else {
			bot.turn(90);
			bot.move(80);
			bot.turn(90);
		}
		bot.move(height);
		// Reset
		bot.turn(180);
	}
}
