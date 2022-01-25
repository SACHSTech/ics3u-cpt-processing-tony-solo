import processing.core.PApplet;

public class Sketch extends PApplet {
	
	
  // global variables
  int width = 800;
  int height = 800;
  int rows = 25;
  int columns = 25;
  float unitWidth = width/rows;
  float unitHeight = width/columns;
  float unitX;
  float unitY;

  boolean[][] grid = new boolean[rows][columns];

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(width, height);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(200);

    for (int i = 1; i < rows-1; i++) {
      for (int j = 1; j < columns-1; j++) {
        unitX = unitWidth * i;
        unitY = unitHeight * j;
        noFill();
        rect(unitX, unitY, unitWidth, unitHeight);
      }
     
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
  }
  
  // method



}