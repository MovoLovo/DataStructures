public class Student {
    // Declaring private variables used to define general students
    private String name;
    public int id;

    // Constructor for general student class
    public Student(String name, int id){
        this.name = name;
        this.id = id;
    }

    // Get the name of the student
    public String getName() {
        return name;
    }

    // Set the name of the student
    public void setName(String name) {
        this.name = name;
    }

    // General welcome printing function
    public void printWelcome(){
        System.out.println("Welcome " + this.getName() + "!");
    }
}
