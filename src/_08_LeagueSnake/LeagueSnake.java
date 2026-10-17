package _08_LeagueSnake;

import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    	Segment head;
    	int foodX;
    	int foodY;
    	int direction = UP;
    	int foodEaten = 0;
    	
    	
    	ArrayList<Segment> tailPieces = new ArrayList<Segment>();
    	
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        head = new Segment(10, 10);
        frameRate(16);
        dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
        background(205, 230, 0, 200);
        drawFood();
        move();
        drawSnake();
        eat();
    }

    void drawFood() {
        // Draw the food
    	fill(255, 0, 0, 200);
        rect(foodX, foodY, 10, 10);
       
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(0, 255, 70, 200);
    	 rect(head.x, head.y, 10, 10);
    	 manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
    	for(Segment s : tailPieces){
			rect(s.x, s.y, 10, 10);
		}
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
    	checkTailCollision();
    	drawTail();
    	Segment seg = new Segment(head.x, head.y);
    	tailPieces.add(seg);
    	tailPieces.remove(0);
    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
       for(Segment seg: tailPieces) {
    	  if(seg.x == head.x && seg.y == head.y) {
    		foodEaten = 1;
    		ArrayList<Segment> tail = new ArrayList<Segment>();
    		tail.add(seg);
    	  }
    	  
       }
    	   
    	   
       }
    

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */
    

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
       
        if(direction == DOWN && keyCode != UP) {
        	 direction = keyCode;
        }
        else if(direction == UP && keyCode != DOWN) {
        	direction = keyCode;
        }
        else if(direction == RIGHT && keyCode != LEFT) {
        	direction = keyCode;
        }
        else if(direction == LEFT && keyCode != RIGHT) {
        	direction = keyCode;
        }
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.
    	if(direction == UP) {
    		head.y -=10;
    	}
    	else if(direction == DOWN) {
    		head.y +=10;
    	}
    	else if(direction == LEFT) {
    		head.x -=10;
    	}
    	else if(direction == RIGHT) {
    		head.x +=10;
    	}
    	checkBoundaries();
    	
        /*
        if (direction == UP) {
            // Move head up
            
        } else if (direction == DOWN) {
            // Move head down
                
        } else if (direction == LEFT) {
            
        } else if (direction == RIGHT) {
            
        }
        */
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(head.x > 500) {
        	head.x = 0;
        }
        if(head.x < 0) {
        	head.x = 500;
        }
        if(head.y < 0) {
        	head.y = 500;
        }
        if(head.y > 500) {
        	head.y = 0;
        }
    }

    void eat() {
        // When the snake eats the food, its tail should grow and more
        // food appear
        if(head.x == foodX && head.y == foodY) {
        	foodEaten +=1;
        	dropFood();
        	Segment seg = new Segment(head.x, head.y);
        	tailPieces.add(seg);
        	
        }
        
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
