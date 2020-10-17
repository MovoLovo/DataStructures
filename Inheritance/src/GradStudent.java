public class GradStudent extends Student {
    // Declaring private variables for grad students
    private String dissertationTopic;

    // Constructor for grad students
    public GradStudent(String name, int id, String dissertationTopic){
        // Inherits the constructor from the general student class
        super(name, id);
        this.dissertationTopic = dissertationTopic;
    }

    // Get dissertation topic
    public String getTopic(){
        return dissertationTopic;
    }

    // Set dissertation topic
    public void setTopic(String t){
        this.dissertationTopic = t;
    }

    // Prints the welcome to grad students. Overrides the welcome printing function from the general student class.
    @Override
    public void printWelcome(){
        System.out.println("Welcome to Graduate School " + super.getName() + "!");
    }
}
