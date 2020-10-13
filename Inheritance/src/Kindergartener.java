public class Kindergartener extends Student{
    private String favoriteAnimal;

    public Kindergartener(String name, int id, String favoriteAnimal){
        super(name, id);
        this.favoriteAnimal = favoriteAnimal;
    }

    public String getFavoriteAnimal() {
        return favoriteAnimal;
    }

    public void setFavoriteAnimal(String favoriteAnimal) {
        this.favoriteAnimal = favoriteAnimal;
    }

    public void printWelcome(){
        System.out.println("Welcome to Kindergarten little " + this.favoriteAnimal + "!");
    }
}
