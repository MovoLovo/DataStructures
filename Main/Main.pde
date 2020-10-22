
void setup() {
 colorMode(HSB); // Change color mode for easier random color generation
 background(33, 49, 77); // No longer Avon blue!
 noStroke();            // Not even sure its White!
 size(700, 700);     // Square!
 fill(130, 36, 51); // I think this was supposed to be Avon red?!
  
  // set the recursion depth and start drawing triangles
  int depth = 10;
  // Created a direction variable to make it clear what value this variable starts with 
  int direction = 0;
  Sierpinski s = new Sierpinski(0,height, width/2, 0, width, height, depth, direction);
  s.display();
}
