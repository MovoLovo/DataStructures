public class Main {
    public static void main(String[] args) {
        // Creating test instances
        Student li = new Student("Spencer", 2022);
        Student sayles = new GradStudent("Evan", 2018, "Dealing with crazy computer students");
        Student beu = new Kindergartner("Alma", 2022, "mouse");

        // Testing welcome message functionality
        li.printWelcome(); // Should print "Welcome Spencer!"
        sayles.printWelcome(); // Should print "Welcome to Graduate School Evan!"
        beu.printWelcome(); // Should print "Welcome to Kindergarten little mouse!"
    }
}
