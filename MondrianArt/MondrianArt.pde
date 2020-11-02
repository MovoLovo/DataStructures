void setup(){
  // Setup the file
  size(800, 800);
  
  // Make call to draw mondrian art given the size of the window
  mond(0, 0, width, height);
}

// Constant for the minimun size where you can split the rectangle
final float MIN_WIDTH = 120;

// This function recusively draws rectangles in the fashion of Mondrian Art
// Obviously mond is short of mondrian
void mond(float x1, float y1, float x2, float y2){
  
  // Creating some variables for split locations so that the program is more readable
  // Randomizes the splits between 1/3 and 2/3 length of the region
  float horiz = random(x1 + (x2 - x1) / 3, x2 - (x2 - x1) / 3);
  float vert = random(y1 + (y2 - y1) / 3, y2 - (y2 - y1) / 3); 
  
  // Recursive flow controlling if brancher
  if(x2 - x1 > width/2 && y2 - y1 > height/2){ // If the region must be split into 4 
    
    // Call recursion manager function with randomized horizontal and vertical splits
    split(x1, y1, x2, y2, horiz, vert);
    
  }else if(x2 - x1 > width/2){ // If region must be split into 2 horizontally
  
    // Call recursive manager with horizontal split location only
    split(x1, y1, x2, y2, horiz, 0);
    
  }else if(y2 - y1 > height/2){ // If region must be split into 2 vertically
  
    // Call recursive manager with vertical split location only
    split(x1, y1, x2, y2, 0, vert);
    
  }else{ // Handles cases where spliting is a probability
    
    // Call recursive manager with randomly selected split locations or none at all
    split(x1, y1, x2, y2, shouldSplit(x2 - x1) ? horiz : 0, shouldSplit(y2 - y1) ? vert : 0);
    
  }
}

// Managers how the mond function recurses to minimize repeated code
void split(float x1, float y1, float x2, float y2, float horiz, float vert){
  
  // Flow control of how the mond function will recurse
  if(horiz != 0 && vert != 0){ // If the horizontal and vertical split locations are not 0, then split into 4 (if they are 0 randomly somehow, it shouldn't split regardless)
    
    mond(x1, y1, horiz, vert); // Top left region
    mond(horiz, y1, x2, vert); // Top right region
    mond(x1, vert, horiz, y2); // Bottom left region
    mond(horiz, vert, x2, y2); // Bottom right region
    
  }else if(horiz != 0){ // If horizontal split has been passed to this function, then split horizontally only
  
    mond(x1, y1, horiz, y2); // Top region
    mond(horiz, y1, x2, y2); // Bottom region
    
  }else if(vert != 0){ // If vertical split has been passed to this function, then split vertically only
    
    mond(x1, y1, x2, vert); // Left region
    mond(x1, vert, x2, y2); // Right region
    
  }else{ // If no splitting needs to be done, paint in the color of the rectangles 
    
    // The final location of this rectangle is now known, it's now time to actually draw it
    // This isn't done like Sierpinski for efficiency, where it covers each region with it's own subregions, it's only drawn when location of region is finalized
    paint(x1, y1, x2, y2); 
    
  }
}

// Paints the regions based the rules described in the assignment 
void paint(float x1, float y1, float x2, float y2){
  
  // Creates a random number between 0 and 1 that decides the color of the region
  float r = random(0, 1);
  
  // Presets color to white before coloring
  fill(255);
  
  // If tree that is based on the randomly selected value 
  if(r < 0.0833){ // If r < 0.0833 draw this rectangle red
  
    fill(255, 0, 0);
    rect(x1, y1, x2, y2);
    
  }else if(r < 0.1667){ // If r < 0.1667 draw this rectangle light blue
  
    fill(100, 100, 255);
    rect(x1, y1, x2, y2);
    
  }else if(r < 0.25){ // If r < 0.0833 draw this rectangle yellow 
  
    fill(255, 255, 0);
    rect(x1, y1, x2, y2);
    
  }else{ // Just draw a normal rectangle based on given parameters
  
    rect(x1, y1, x2, y2);
    
  }
}

// Function that decides if some region should be split based on width of the region
boolean shouldSplit(float wide){
  // Returns a bool based on the criteria given in the assignment
  return wide > MIN_WIDTH && random(MIN_WIDTH, wide * 1.5) < wide;
}
