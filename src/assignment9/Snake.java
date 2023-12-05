package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;

	private BodySegment prevLast;
	
	public Snake() {
		deltaX = 0;
		deltaY = 0;

		segments = new LinkedList<BodySegment>();

		BodySegment e = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
		segments.add(e);
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { // up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { // down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { // left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { // right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		BodySegment seg = segments.getFirst();

		segments.addFirst(new BodySegment(seg.getX()+this.deltaX, seg.getY()+this.deltaY, SEGMENT_SIZE, seg.getColor()));
		
		this.prevLast = segments.getLast();
		segments.removeLast();
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment seg : segments) {
			seg.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		double dis = Math.sqrt(Math.pow(this.segments.getFirst().getX()-f.getX(), 2)+Math.pow(this.segments.getFirst().getY()-f.getY(), 2));
		if (dis <= SEGMENT_SIZE+Food.FOOD_SIZE) {

			this.segments.addLast(prevLast);
			return true;
		}

		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		if (segments.getFirst().getX() <= 1 && segments.getFirst().getX() >= 0 && segments.getFirst().getY() <= 1 && segments.getFirst().getY() >= 0) {
			return true;
		}
		return false;
	}

	public int length() {
		return segments.size();
	}
}
