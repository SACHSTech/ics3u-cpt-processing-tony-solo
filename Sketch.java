import processing.core.PApplet;
import java.util.HashMap;


public class Sketch extends PApplet {


  // VARIABLES
  String difficulty; 
  String totalTime;
  String currentTime;

  int width = 800;
  int height = 800;
  int rows = 20;
  int columns = 20;
  int sceneBGColour = 45;
  int count;
  int count2;
  int playerIndexX;
  int playerIndexY;
  int seconds;
  int minutes;

  float unitWidth = width/rows;
  float unitHeight = width/columns;
  float playerX = unitWidth;
  float playerY = unitHeight;
  float unitX;
  float unitY;

  boolean firstButtonClicked = false;
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
                     {1,0,1,1,1,1,0,1,1,0,0,0,0,0,1,0,0,0,0,1},
                     {1,0,0,0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,0,1},
                     {1,1,0,1,0,1,1,0,1,1,1,0,0,0,1,1,1,1,0,1},
                     {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,0,0,0,1},
                     {1,0,0,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1,1,1},
                     {1,1,1,1,1,0,1,0,1,0,1,0,0,0,1,1,0,0,1,1},
                     {1,0,1,0,0,0,1,0,1,0,1,1,1,0,0,1,1,0,0,1},
                     {1,0,0,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,1},
                     {1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1},
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
                     {1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1,0,0,1},
                     {1,0,0,1,0,1,0,0,0,1,1,0,1,1,0,0,0,0,1,1},
                     {1,1,0,1,0,0,1,1,0,1,1,0,0,0,1,0,1,0,1,1},
                     {1,0,0,0,1,0,1,0,0,0,0,0,1,1,0,0,1,0,0,1},
                     {1,0,1,1,0,0,1,1,1,0,1,1,1,1,1,1,1,1,0,1},
                     {1,0,0,1,0,1,1,0,1,0,1,0,0,0,0,0,0,0,0,1},
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
    // set obstacle positions
    obstaclesPos1();
    obstaclesPos2();
    obstaclesPos3();

    // set up scenes
    menuScene = true;
    gameScene = false;
    gameOverScene = false;
    scoreScene = false;

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // draws scene
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
  
  /**
  * Changes to menu scene 
  */
  public void menuScene() {
    background(sceneBGColour);

    if (!firstButtonClicked) {
      fill(0, 255,  0);
      rect(width/2-60, height/2-25, 125, 60);
      textSize(145);
      fill(255);
      text("Maze Run", width/2 - 330, height/2 - 175);
      textSize(40);
      fill(1);
      text("Play", width/2 - 36, width/2+19);
    }
    else {
      textSize(120);
      fill(255);
      text("Difficulty", width/2 - 230, height/2 - 115);
      fill(255);
      rect(width/2-150, height/2, 125, 60);
      rect(width/2+20, height/2, 125, 60);
      textSize(40);
      fill(sceneBGColour);
      text("Easy", width/2-132, height/2+43);
      text("Hard", width/2+41, height/2+43);
    }
    
  }

  /**
  * Changes to game scene
  */
  public void gameScene() {
    background(220);

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

  /**
  * Changes to game over scene
  */
  public void gameOverScene() {
    background(sceneBGColour);

    fill(255);
    rect(width/2-150, height/2, 125, 60);
    rect(width/2+20, height/2, 125, 60);
    textSize(100);
    fill(255);
    text("Game Over!", width/2 - 295, height/2 - 175);
    textSize(50);
    textSize(30);
    fill(sceneBGColour);
    text("Retry", width/2-123, height/2+40);
    text("Menu", width/2+45, height/2+40);

  }

  /**
  * Changes to score scene 
  */
  public void scoreScene() {
    background(sceneBGColour);

    fill(255);
    rect(width/2-150, height/2, 125, 60);
    rect(width/2+20, height/2, 125, 60);
    textSize(145);
    fill(255);
    text("Score", width/2 - 195, height/2 - 175);
    textSize(50);
    text("Difficulty: " + difficulty, width/2 - 190, height/2 - 110);
    text("Clear Time: " + totalTime, width/2 - 245, height/2 - 50);
    
    textSize(30);
    fill(sceneBGColour);
    text("Retry", width/2-123, height/2+40);
    text("Menu", width/2+45, height/2+40);

  }
  
  /**
  * Draws the levels 
  * 
  * @param level  the level to be played (1,2,3)
  * @param obstaclePos  the positions of the obstacles
  */
  public void levels(int [][] level, HashMap<Float, Float> obstaclePos) {
    // interval for obstacle appearing
    if (count == 165) {
      count = 0;
    }
    count++;
    // 60 iterations = 1 second
    if (count2 == 60) {
      seconds++;
      count2 = 0;
    }
    count2++;
    if(seconds == 60) {
      minutes++;
      seconds = 0;
    }

    // draw grid
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        unitX = unitWidth * i;
        unitY = unitHeight * j;

        // fill the walls in
        if (level[j][i] == 1) {
          fill(1);
        } 
        else {
          noFill();
        }
        rect(unitX, unitY, unitWidth, unitHeight);

        // fill the obstacles in
        if (difficulty == "Easy") {
          if (count >= 0 && count <= 100) {
            obstacles(obstaclePos);
          }
        }
        else if (difficulty == "Hard") {
          if (count >= 0 && count <= 140) {
            obstacles(obstaclePos);
          }
        }
      }
    }

    goal();
    movePlayer(level);

    if (seconds < 10) {
      currentTime = minutes + ":" + "0" + seconds;
    }
    else {
      currentTime = minutes + ":" + seconds;
    }

    fill(255);
    textSize(25);
    text(currentTime, width/2-15, 30);

  }

  /**
  * moves the player
  * 
  * @param level  the level to be played (1,2,3)
  */
  public void movePlayer(int [][] level) {
    // translate player pixel position to an index on grid array 
    playerIndexX = (int)playerX / 40;
    playerIndexY = (int)playerY / 40;

    // controls + checks if there is a wall in front
    if (keyPressed) {
      if (keyCode == UP && playerY > 0 && level[playerIndexY-1][playerIndexX] == 0) {
        playerY -= unitHeight;
      }
      else if (keyCode == DOWN && playerY < height - unitHeight && level[playerIndexY+1][playerIndexX] == 0) {
        playerY += unitHeight;
      }
      else if (keyCode == LEFT && playerX > 0 && level[playerIndexY][playerIndexX-1] == 0) {
        playerX -= unitWidth;
      }
      else if (keyCode == RIGHT && playerX < width - unitWidth && level[playerIndexY][playerIndexX+1] == 0) {
        playerX += unitWidth;
      }
    }

    keyCode = 0;
  
    // draw player
    fill(255, 255, 0);
    rect(playerX, playerY, unitWidth, unitHeight);

  } 

  /**
  * draws obstacles in level
  * 
  * @param obstaclePos the positions of the obstacles 
  */
  public void obstacles(HashMap<Float, Float> obstaclePos) {
    fill(255, 0, 0);
    for (float i : obstaclePos.keySet()) {
      rect(i, obstaclePos.get(i), unitWidth, unitHeight);

      // if player hit by obstacle
      if (playerX == i && playerY == obstaclePos.get(i)) {
        // reset player position & timer
        playerIndexX = 0;
        playerIndexY = 0;
        playerX = unitWidth;
        playerY = unitHeight;
        seconds = 0;
        minutes = 0;

        // change scene
        level1 = false;
        level2 = false;
        level3 = false;
        gameScene = false;
        gameOverScene = true;
      }
    }

  }

  /**
  * executes an event when mouse clicks button
  */
  public void mouseClicked() {
    if (menuScene) {
      if(!firstButtonClicked) {
        if(mouseX > width/2-60 && mouseY > height/2-25 && mouseX < width/2+65 && mouseY < height/2+35) {
          firstButtonClicked = true;
        }
      }
      else if(mouseX > width/2-150 && mouseY > height/2 && mouseX < width/2-25 && mouseY < height/2+60) {
        firstButtonClicked = false;
        difficulty = "Easy";
        menuScene = false;
        gameScene = true;
        level1 = true;
      }
      else if(mouseX > width/2+20 && mouseY > height/2 && mouseX < width/2+105 && mouseY < height/2+60) {
        firstButtonClicked = false;
        difficulty = "Hard";
        menuScene = false;
        gameScene = true;
        level1 = true;
      }
    }
    else if (scoreScene) {
      if(mouseX > width/2 - 150 && mouseY > height/2 && mouseX < width/2 - 25 && mouseY < height/2 + 60) {
        scoreScene = false;
        gameScene = true;
        level1 = true;
      }
      else if (mouseX > width/2 + 20 && mouseY > height/2 && mouseX < width/2 + 145 && mouseY < height/2 + 60) {
        scoreScene = false;
        menuScene = true;
      }
    }
    else if (gameOverScene) {
      if(mouseX > width/2 - 150 && mouseY > height/2 && mouseX < width/2 - 25 && mouseY < height/2 + 60) {
        gameOverScene = false;
        gameScene = true;
        level1 = true;
      }
      else if (mouseX > width/2 + 20 && mouseY > height/2 && mouseX < width/2 + 145 && mouseY < height/2 + 60) {
        gameOverScene = false;
        menuScene = true;
      }
    }
    
  }

  /**
  * executes an event when mouse clicks button
  */
  public void goal() {
    // draw goal
    fill(0, 255, 0);
    rect(width - unitWidth*2, height - unitHeight*2, unitWidth, unitHeight);
    
    if (playerX == width - unitWidth*2 && playerY == height - unitHeight*2) {
      playerX = unitWidth;
      playerY = unitHeight;
      if (level1) {
        level1 = false;
        level2 = true;
      }
      else if (level2) {
        level2 = false;
        level3 = true;
      }
      else if (level3) {
        totalTime = currentTime;
        minutes = 0;
        seconds = 0;
        level3 = false;
        level1 = false;
        gameScene = false;
        scoreScene = true;
      }
    }

  }

  /**
  * obstacle positions for level 1
  */
  public void obstaclesPos1() {
    obstacles1.put(unitWidth*3, unitHeight*9);
    obstacles1.put(unitWidth*8, unitHeight);
    obstacles1.put(unitWidth*5, unitHeight*16);
    obstacles1.put(unitWidth*14, unitHeight*9);

  }

  /**
  * obstacle positions for level 2
  */
  public void obstaclesPos2() {
    obstacles2.put(unitWidth*5, unitHeight*3);
    obstacles2.put(unitWidth*10, unitHeight*3);
    obstacles2.put(unitWidth*17, unitHeight*3);
    obstacles2.put(unitWidth*7, unitHeight*6);
    obstacles2.put(unitWidth*7, unitHeight*13);
    obstacles2.put(unitWidth, unitHeight*14);
    obstacles2.put(unitWidth*15, unitHeight*13);
    obstacles2.put(unitWidth*13, unitHeight*18);

  }

  /**
  * obstacle positions for level 3
  */
  public void obstaclesPos3() {
    obstacles3.put(unitWidth*6, unitHeight*4);
    obstacles3.put(unitWidth*2, unitHeight*9);
    obstacles3.put(unitWidth*10, unitHeight*2);
    obstacles3.put(unitWidth*1, unitHeight*3);
    obstacles3.put(unitWidth*5, unitHeight*13);
    obstacles3.put(unitWidth*12, unitHeight*3);
    obstacles3.put(unitWidth*18, unitHeight*4);
    obstacles3.put(unitWidth*8, unitHeight*9);
    obstacles3.put(unitWidth*17, unitHeight*11);
    obstacles3.put(unitWidth*13, unitHeight*11);
    obstacles3.put(unitWidth*16, unitHeight*15);
    obstacles3.put(unitWidth*4, unitHeight*18);
    obstacles3.put(unitWidth*11, unitHeight*18);
    obstacles3.put(unitWidth*16, unitHeight*7);
    obstacles3.put(unitWidth*9, unitHeight*16);
    obstacles3.put(unitWidth*15, unitHeight*15);

  }


}
