import processing.core.PApplet;
import java.util.HashMap;


public class Sketch extends PApplet {


  // GLOBAL VARIABLES
  int counter;
  int width = 800;
  int height = 800;
  int count;
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
  float playerX;
  float playerY;

  boolean menuScene;
  boolean gameScene;
  boolean gameOverScene;
  boolean scoreScene;
  boolean level1;
  boolean level2;
  boolean level3;

  HashMap<Float, Float> obstacles1 = new HashMap<Float, Float>();
  HashMap<Float, Float> obstacles2 = new HashMap<Float, Float>();
  HashMap<Float, Float> obstacles3 = new HashMap<Float, Float>();

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
    // level 1 obstacles
    obstacles1.put(unitWidth*3, unitHeight*9);
    obstacles1.put(unitWidth*8, unitHeight);
    obstacles1.put(unitWidth*5, unitHeight*16);
    obstacles1.put(unitWidth*14, unitHeight*9);

    // level 2 obstacles
    //obstacles2.put(unitWidth*, unitHeight*);

    // level 3 obstacles


    playerX = 40;
    playerY = 40;

    menuScene = true;
    gameScene = false;
    gameOverScene = false;
    scoreScene = false;
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
    else if (scoreScene) {
      scoreScene();
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
      level1 = true;
    }
  }

  // game scene
  public void gameScene() {
    background(210);

    // change level accordingly
    if (level1) {
      levels(grid1, obstacles1);
    }
    else if (level2) {
      levels(grid2, obstacles2);
    }
    else if (level3) {
      levels(grid3, obstacles3);
    }
  }

  // game over scene
  public void gameOverScene() {
    background(1);

    fill(255,0,15);
    rect(width/2-50, height/2-25, 100, 50);
    if(mouseX > width/2-50 && mouseY > height/2-25 && mouseX < width/2+50 && mouseY < height/2+25 && mousePressed==true){
      gameOverScene = false;
      menuScene = true;
    }
  }

  // score scene
  public void scoreScene() {
    background(255);


  }
  
  // levels
  public void levels(int [][] array, HashMap<Float, Float> hashMap) {
    // interval for obstacle appearing
    if (count >= 170) {
      count = 0;
    }
    count++;

    // draw grid
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        unitX = unitWidth * i;
        unitY = unitHeight * j;

        // fill the walls in
        if (array[j][i] == 1) {
          fill(1);
        } 
        else {
          noFill();
        }
        rect(unitX, unitY, unitWidth, unitHeight);

        // fill the obstacles in
        if (count >= 0 && count <= 100) {
          obstacles(hashMap);
        }
      }
    }

    goal();
    movePlayer(array);
  }

  // draw obstacle
  public void drawObstacle(float obstacleX, float obstacleY) {
    fill(255, 0, 0);
    rect(obstacleX, obstacleY, unitWidth, unitHeight);
  }

  // obstacles for levels
  public void obstacles(HashMap<Float, Float> hashMap) {
    for (float i : hashMap.keySet()) {
      drawObstacle(i, hashMap.get(i));

      // if player hit by obstacle
      if (playerX == i && playerY == hashMap.get(i)) {
        level1 = false;
        level2 = false;
        level3 = false;
        playerIndexX = 0;
        playerIndexY = 0;
        playerX = 40;
        playerY = 40;
        gameScene = false;
        gameOverScene = true;
      }
    }
  }

  // goal
  public void goal() {
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
        level1 = false;
        gameScene = false;
        scoreScene = true;
      }
    }
  }

  // move player 
  public void movePlayer(int [][] array) {
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

    }

    keyCode = 0;
  
    // draw player
    fill(255, 255, 0);
    rect(playerX, playerY, playerWidth, playerHeight);
  } 
  

}
