public class Kindergartner extends Student{
    // Declares a variable to store the kindergartners favorite animal
    private String favoriteAnimal;

    // Construct a Kindergartner
    public Kindergartner(String name, int id, String favoriteAnimal){
        // Inherit constructor from general student class
        super(name, id);
        this.favoriteAnimal = favoriteAnimal;
    }

    // Get favorite animal
    public String getFavoriteAnimal() {
        return favoriteAnimal;
    }

    // Set favorite animal
    public void setFavoriteAnimal(String favoriteAnimal) {
        this.favoriteAnimal = favoriteAnimal;
    }

    // Overrides the general student welcome printing function to print out a more welcoming statement. Usually kids
    // to be their favorite animal.
    @Override
    public void printWelcome(){
        System.out.println("Welcome to Kindergarten little " + this.favoriteAnimal + "!");
    }
}
