package assignment9;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	private Snake snake;
	private Food food;
	public final int WAIT_TIME = 75; // I made the wait time 75 because I thought the snake was moving too fast
	public final double MIDDLE_OF_SCREEN = 0.5;

	public Game() {
		StdDraw.enableDoubleBuffering();

		this.snake = new Snake();
		this.food = new Food();
	}

	public void play() {
		while (snake.isInbounds()) { // Check if snake is in bounds
			int dir = getKeypress();
			System.out.println("Keypress: " + dir);

			snake.move();
			snake.changeDirection(dir);

			updateDrawing();

			if (snake.eatFood(this.food)) {
				this.food = new Food();
			}

			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
		}

		StdDraw.setPenColor(Color.RED);
		StdDraw.text(MIDDLE_OF_SCREEN,MIDDLE_OF_SCREEN, "GAME OVER");
		StdDraw.show();
	}

	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}

	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */

		StdDraw.clear();
		snake.draw();
		food.draw();
		StdDraw.setPenColor();
		StdDraw.textLeft(0.01, 0.02, "Score: "+ snake.length());
		StdDraw.pause(WAIT_TIME);
		StdDraw.show();
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
