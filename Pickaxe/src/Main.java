public class Main {
    public static void main(String[] args) {
        Pickaxe pickaxe = new Pickaxe("diamond", 149, true);
        Pickaxe pickaxe2 = new Pickaxe("diamond", 149, true);

        pickaxe.setName("Pickmeaxe");
        pickaxe2.setName("Pickmeaxe");

        pickaxe.enchant("fire aspect", 1);
        pickaxe2.enchant("fire aspect", 1);

        System.out.println(pickaxe.equals(pickaxe2));
    }
}
