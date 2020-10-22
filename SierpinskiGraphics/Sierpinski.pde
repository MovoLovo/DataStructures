/**
 * Siepinski: Recursively draws a Sierpinski triangle.
 * @author Spencer Li @Spencillian
 */

public class Sierpinski 
{
  private int depth; // number of Sierpinski triangles within
  private float x1, y1, x2, y2, x3, y3; // location of triangle corners
  
  /**
   * Instantiates a new Sierpinski object's variables.
   */
  public Sierpinski(float x1, float y1, float x2, float y2, float x3, float y3, int depth)
  {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.x3 = x3;
    this.y3 = y3; //<>//
    this.depth = depth;
  }
  
  /**
   * Creates a triangle background. The location of the points of the triangle are based on parameters given in the constructor.
   * Then recusively creates 3 new child Sierpinski objects. The points of the children objects are calculated by selectively getting the halfway
   * point of between two of the points on the parent triangle. These points are then passed as parameters into the child Sierpinski objects.
   * These child objects draw their own triangles and create their own child objects continueing the recursion until the depth reaches 0. 
   */
  public void display()
  {
    if(this.depth != 0){
      // draw the triangle
      triangle(x1, y1, x2, y2, x3, y3);
      
      // Bottom left triangle
      new Sierpinski(x1, y1, this.avg(x1, x2), this.avg(y1, y2), this.avg(x1, x3), this.avg(y1, y3), this.depth - 1).display();
      
      // Top triangle
      new Sierpinski(this.avg(x1, x3), this.avg(y1, y3), this.avg(x2, x3), this.avg(y2, y3), x3, y3, this.depth - 1).display();
      
      // Bottom right triangle
      new Sierpinski(this.avg(x1, x2), this.avg(y1, y2), x2, y2, this.avg(x2, x3), this.avg(y2, y3), this.depth - 1).display();
    }
  }
  
  /**
   * Calculates the average of two numbers. Duh.
   */
  private float avg(float n1, float n2) {
    return (n1 + n2) / 2;
  }
}
