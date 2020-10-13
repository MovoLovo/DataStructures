public class GradStudent extends Student {
    private String dissertationTopic;

    public GradStudent(String name, int id, String dissertationTopic){
        super(name, id);
        this.dissertationTopic = dissertationTopic;
    }

    public String getTopic(){
        return dissertationTopic;
    }

    public void setTopic(String t){
        this.dissertationTopic = t;
    }

    public void printWelcome(){
        System.out.println("Welcome to Graduate School, " + super.getName());
    }
}
