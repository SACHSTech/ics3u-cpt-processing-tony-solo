import processing.core.PApplet;
import java.util.*;

public class Sketch extends PApplet {


  // GLOBAL VARIABLES
  int width = 800;
  int height = 800;
  int rows = 20;
  int columns = 20;
  int playerIndexX;
  int playerIndexY;

  float unitWidth = width/rows;
  float unitHeight = width/columns;
  float unitX;
  float unitY;
  float playerWidth = unitWidth;
  float playerHeight = unitHeight;
  float playerX = 40;
  float playerY = 40;

  boolean menuScene;
  boolean gameScene;
  boolean gameOverScene;
  boolean level1;
  boolean level2;
  boolean level3;

  // level design (0 = empty, 1 = filled in)
  int [][] grid1 = { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                     {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                     {1,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1},
                     {1,0,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,0,1},
                     {1,0,1,1,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,1},
                     {1,0,0,0,0,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1},
                     {1,1,1,1,1,0,1,1,0,0,0,0,1,1,0,1,1,1,1,1},
                     {1,1,1,1,1,0,0,1,1,1,1,0,1,0,0,0,0,0,0,1},
                     {1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,0,1,1},
                     {1,0,0,0,0,0,0,1,0,1,1,0,1,1,0,1,1,1,1,1},
                     {1,0,1,1,1,1,1,1,0,0,0,0,1,1,0,0,0,0,0,1},
                     {1,0,0,0,0,0,0,1,1,1,1,0,1,1,0,1,1,1,1,1},
                     {1,1,1,1,1,1,0,1,0,0,0,0,1,1,0,1,1,1,1,1},
                     {1,0,0,0,0,0,0,1,1,1,1,0,0,1,0,0,0,0,0,1},
                     {1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1},
                     {1,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,0,1},
                     {1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1},
                     {1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1},
                     {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1},
                     {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, };
  
  int [][] grid2 = { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                     {1,0,0,0,0,0,0,1,0,1,1,1,1,0,0,0,0,0,0,1},
                     {1,0,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1},
                     {1,0,1,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,1},
                     {1,0,1,0,0,1,1,1,1,1,1,0,1,0,1,0,1,1,1,1},
                     {1,0,1,1,1,1,0,1,1,1,0,0,0,0,1,0,0,0,0,1},
                     {1,0,0,0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,0,1},
                     {1,1,0,1,0,1,1,0,1,1,1,0,0,0,1,1,1,1,0,1},
                     {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,0,0,0,1},
                     {1,0,0,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1,1,1},
                     {1,1,1,1,1,0,1,0,1,0,1,0,0,0,1,1,0,0,1,1},
                     {1,0,1,0,0,0,1,0,1,0,1,1,1,0,0,1,1,0,0,1},
                     {1,0,0,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,1},
                     {1,0,1,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,0,1},
                     {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1},
                     {1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1},
                     {1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,0,1},
                     {1,0,0,0,1,0,1,0,1,0,0,0,1,1,1,0,0,1,0,1},
                     {1,1,1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,1,0,1},
                     {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, };

  int [][] grid3 = { {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                     {1,0,0,1,0,0,0,1,0,1,1,1,0,0,0,0,1,1,1,1},
                     {1,1,0,1,0,1,0,1,0,0,0,1,0,1,1,0,0,0,0,1},
                     {1,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,1,1,0,1},
                     {1,0,1,1,1,1,0,0,0,1,0,1,1,1,0,0,0,1,0,1},
                     {1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,1,0,1,0,1},
                     {1,0,1,1,0,1,0,1,0,0,1,1,0,1,1,1,1,0,0,1},
                     {1,0,0,1,0,1,0,0,0,1,1,0,1,1,0,0,0,0,1,1},
                     {1,1,0,1,0,0,1,1,0,1,1,0,0,0,1,0,1,0,1,1},
                     {1,0,0,1,1,0,1,0,0,0,0,0,1,1,0,0,1,0,0,1},
                     {1,0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
                     {1,0,0,1,0,1,1,0,1,1,1,0,0,0,0,0,0,0,0,1},
                     {1,1,0,1,0,0,1,0,0,0,0,1,1,0,1,0,1,0,1,1},
                     {1,0,0,1,1,0,0,1,0,1,0,1,1,0,1,0,1,0,0,1},
                     {1,0,1,1,1,1,0,0,0,1,0,1,0,0,1,1,1,1,0,1},
                     {1,0,0,0,1,0,1,1,1,1,0,1,1,0,0,0,0,0,1,1},
                     {1,1,1,1,0,0,0,0,0,0,0,1,0,1,1,1,1,0,0,1},
                     {1,0,1,1,0,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1},
                     {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,0,1},
                     {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, };                 
               


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
    level1 = true;
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
    if(mouseX > width/2-50 && mouseY > height/2-25 && mouseX < width/2+50 && mouseY < height/2+25 && mousePressed) {
      menuScene = false;
      gameScene = true;
    }
  }

  // game scene
  public void gameScene() {
    background(200);

    // change level accordingly
    if (level1) {
      level1();
    }
    else if (level2) {
      level2();
    }
    else if (level3) {
      level3();
    }

    // goal
    fill(0, 255, 0);
    rect(width - unitWidth*2, height - unitHeight*2, unitWidth, unitHeight);
    if (playerX == width - unitWidth*2 && playerY == height - unitHeight*2) {
      playerX = playerWidth;
      playerY = playerHeight;
      if (level1) {
        level1 = false;
        level2 = true;
      }
      else if (level2) {
        level2 = false;
        level3 = true;
      }
      else if (level3) {
        level3 = false;
        level1 = true;
        gameScene = false;
        gameOverScene = true;
      }
    
    }

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
  
  // level 1
  public void level1() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        unitX = unitWidth * i;
        unitY = unitHeight * j;

        //fill the obstacles in 
        if (grid1[j][i] == 1) {
          fill(1);
        } 
        else {
          noFill();
        }
        rect(unitX, unitY, unitWidth, unitHeight);
      }
    }

    movePlayer(grid1);
  }
  
  // level 2
  public void level2() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        unitX = unitWidth * i;
        unitY = unitHeight * j;

        //fill the obstacles in 
        if (grid2[j][i] == 1) {
          fill(1);
        } 
        else {
          noFill();
        }
        rect(unitX, unitY, unitWidth, unitHeight);
      }
    }

    movePlayer(grid2);
  }

  // level 3 
  public void level3() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        unitX = unitWidth * i;
        unitY = unitHeight * j;

        //fill the obstacles in 
        if (grid3[j][i] == 1) {
          fill(1);
        } 
        else {
          noFill();
        }
        rect(unitX, unitY, unitWidth, unitHeight);
      }
    }

    movePlayer(grid3);
  }
  
  // move player 
  public int[][] movePlayer(int [][] array) {
    playerIndexX = (int)playerX / 40;
    playerIndexY = (int)playerY / 40;

    if (keyPressed) {
      if (keyCode == UP && playerY > 0 && array[playerIndexY-1][playerIndexX] == 0) {
        playerY -= playerHeight;
      }
      else if (keyCode == DOWN && playerY < height - unitHeight && array[playerIndexY+1][playerIndexX] == 0) {
        playerY += playerHeight;
      }
      else if (keyCode == LEFT && playerX > 0 && array[playerIndexY][playerIndexX-1] == 0) {
        playerX -= playerWidth;
      }
      else if (keyCode == RIGHT && playerX < width - unitWidth && array[playerIndexY][playerIndexX+1] == 0) {
        playerX += playerWidth;
      }
    keyCode = 0;
    }
  
    // draw player
    fill(255, 255, 0);
    rect(playerX, playerY, playerWidth, playerHeight);

    return array;
  } 


}