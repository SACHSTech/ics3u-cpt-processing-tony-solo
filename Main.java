import processing.core.PApplet;


/**
 * Main class to execute sketch
 * @ Tony Yao
 *
 */
class Main {
  public static void main(String[] args) {
    
    String[] processingArgs = {"MySketch"};
	  Sketch mySketch = new Sketch(); 
	  PApplet.runSketch(processingArgs, mySketch);
  }
  
}
