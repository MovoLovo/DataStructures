void setup() {
  // Config program settings
  size(800, 800);
  strokeJoin(ROUND);
  fill(255);
  frameRate(10);
}

final float MIN_WIDTH = 120;
final float DELTA_ROTATION = .005;

// Global variable for tracking rotation
float rotation = 0;

// Add animation to drawing of mondrian art
void draw(){
  
  // Rotate .005 radians
  rotation += DELTA_ROTATION;
  
  // Move drawing origin to mid point of screen 
  translate(width/2, height/2);
  
  // Rotate the drawing system
  rotate(rotation);
  
  /* Because we are translating the location of the origin to the center of the board,
   * the locations of the regions start to have some weird effects.
   * The original mondrian drawing function was based on the assumption that the top left 
   * corner would be the origin. With this assumption changing, the drawing of the regions
   * as well. 
   */
  
  // Draw a mondrian piece with the rotation for this frame 
  mond(-width/2, -height/2, width/2, height/2);
}

// The same draw mondrian function from vanilla program
void mond(float x1, float y1, float x2, float y2) {
  
  // Resetting strokeweight to 1 
  strokeWeight(1);
  
  float horiz = random(x1 + abs(x2 - x1) / 3, x2 - abs(x2 - x1) / 3);
  float vert = random(y1 + abs(y2 - y1) / 3, y2 - abs(y2 - y1) / 3);
  
  if (abs(x2 - x1) > width/2 && abs(y2 - y1) > height/2) {
    split(x1, y1, x2, y2, horiz, vert);
  } else if (x2 - x1 > width/2) {
    split(x1, y1, x2, y2, horiz, 0);
  } else if (y2 - y1 > height/2) {
    split(x1, y1, x2, y2, 0, vert);
  } else {
    split(x1, y1, x2, y2, (shouldSplit(x2 - x1) ? horiz : 0), (shouldSplit(y2 - y1) ? vert : 0));
  }
}

// Same split function as before 
void split(float x1, float y1, float x2, float y2, float horiz, float vert) {
  if (horiz != 0 && vert != 0) {
    mond(x1, y1, horiz, vert);
    mond(horiz, y1, x2, vert);
    mond(x1, vert, horiz, y2);
    mond(horiz, vert, x2, y2);
  } else if (horiz != 0) {
    mond(x1, y1, horiz, y2);
    mond(horiz, y1, x2, y2);
  } else if (vert != 0) {
    mond(x1, y1, x2, vert);
    mond(x1, vert, x2, y2);
  } else {
    paint(x1, y1, x2, y2);
  }
}

// The paint function now also adjusts strokeweight
// Added a few extra colors to possible rectangles
void paint(float x1, float y1, float x2, float y2) {
  float r = random(0, 1);
  strokeWeight(random(3, 10));
  if (r < 0.0833) { // Red
    fill(255, 0, 0);
    rect(x1, y1, x2, y2);
  } else if (r < 0.1667) { // Blue
    fill(100, 100, 255);
    rect(x1, y1, x2, y2);
  } else if (r < 0.25) { // Yellow
    fill(255, 255, 0);
    rect(x1, y1, x2, y2);
  }else if(r < 0.30){ // Light Purple
    fill(255, 100, 255);
    rect(x1, y1, x2, y2);
  }else if(r < 0.40){ // Light blue
    fill(0, 200, 255);
    rect(x1, y1, x2, y2);
  }else if(r < 0.45){ // Orange
    fill(255, 100, 0);
    rect(x1, y1, x2, y2);
  }else{
    rect(x1, y1, x2, y2);
  }
  
  fill(255);
}

// Same shouldSplit function as before
boolean shouldSplit(float wide) {
  return wide > MIN_WIDTH && random(MIN_WIDTH, wide * 1.5) < wide;
}
