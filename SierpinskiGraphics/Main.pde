
void setup() {
   background(33, 49, 77); // Avon blue!
   stroke(255);            // White!
   size(700, 700);     // Square!
   fill(130, 36, 51);      // Avon red!
   
   // set the recursion depth and start drawing triangles
   int depth = 7; 
   Sierpinski s = new Sierpinski(0,height, width/2, 0, width, height, depth);
   s.display();
}
