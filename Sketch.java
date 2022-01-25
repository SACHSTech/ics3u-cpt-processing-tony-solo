import processing.core.PApplet;
import java.util.*;

public class Sketch extends PApplet {
	
	
  // GLOBAL VARIABLES
  int width = 800;
  int height = 800;
  int rows = 20;
  int columns = 20;

  float unitWidth = width/rows;
  float unitHeight = width/columns;
  float unitX;
  float unitY;
  float playerWidth = unitWidth;
  float playerHeight = unitHeight;
  float playerX = playerWidth;
  float playerY = playerHeight;

  boolean menuScene;
  boolean gameScene;
  boolean gameOverScene;

  boolean[][] grid = new boolean[rows][columns];


  // MAIN METHODS
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
    menuScene = true;
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if (menuScene) {
      menuScene();
    }
    else if (gameScene) {
      gameScene();
    }
    else if (gameOverScene) {
      gameOverScene();
    }
  }


  // OTHER METHODS
  // menu scene
  public void menuScene() {
    background(255);

    fill(42,255,15);
    rect(width/2-50, height/2-25, 100, 50);
    if(mouseX > width/2-50 && mouseY > height/2-25 && mouseX < width/2+50 && mouseY < height/2+25 && mousePressed==true){
      menuScene = false;
      gameScene = true;
    }
  }

  // game scene
  public void gameScene() {
    background(200);

    for (int i = 1; i < rows-1; i++) {
      for (int j = 1; j < columns-1; j++) {
        unitX = unitWidth * i;
        unitY = unitHeight * j;
        noFill();
        rect(unitX, unitY, unitWidth, unitHeight);
      }
    }

    if (playerX == width - unitWidth*2 && playerY == height - unitHeight*2) {
      gameScene = false;
      gameOverScene = true;
      playerX = playerWidth;
      playerY = playerHeight;
    }

    movePlayer();
  }

  // game over scene
  public void gameOverScene() {
    background(1);

    fill(255,0,15);
    rect(width/2-50, height/2-25, 100, 50);
    if(mouseX > width/2-50 && mouseY > height/2-25 && mouseX < width/2+50 && mouseY < height/2+25 && mousePressed==true){
      menuScene = true;
      gameOverScene = false;
    }
    
  }
  
  // move player 
  public void movePlayer() {
    if (keyPressed) {
      if (keyCode == UP && playerY > unitHeight) {
        playerY -= playerHeight;
      }
      else if (keyCode == DOWN && playerY < height - unitHeight*2) {
        playerY += playerHeight;
      }
      else if (keyCode == LEFT && playerX > unitWidth) {
        playerX -= playerWidth;
      }
      else if (keyCode == RIGHT && playerX < width - unitWidth*2) {
        playerX += playerWidth;
      }
    keyCode = 0;
    delay(65);
    }
  
    fill(0, 255, 0);
    rect(playerX, playerY, playerWidth, playerHeight);
  }


}