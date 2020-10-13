public class Main {
    public static void main(String[] args) {
        Student li = new Student("Spencer", 2022);
        Student sayles = new GradStudent("Evan", 2018, "Dealing with crazy computer students");
        Student beu = new Kindergartener("Alma", 2022, "mouse");

        li.printWelcome();
        sayles.printWelcome();
        beu.printWelcome();

    }
}
