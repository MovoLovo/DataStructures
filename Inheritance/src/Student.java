public class Student {
    private String name;
    public int id;

    public Student(String name, int id){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printWelcome(){
        System.out.println("Welcome! " + this.getName());
    }
}
