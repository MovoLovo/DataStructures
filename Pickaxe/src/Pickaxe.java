public class Pickaxe {
    private String material;
    private int durability;
    private boolean equipped;
    private boolean enchanted;
    private String enchantment = "";
    private int enchantmentLevel = 0;
    private String name = "";

    public Pickaxe(){
        this.material = "wood";
        this.durability = 59;
        this.equipped = false;
        this.enchanted = false;
    }

    public Pickaxe(String material, int durability, boolean equipped){
        this.material = material;
        this.durability = durability;
        this.equipped = equipped;
    }

    public String getMaterial(){
        return material;
    }

    public int getDurability(){
        return durability;
    }

    public boolean isEquipped(){
        return equipped;
    }

    public boolean isEnchanted(){
        return enchanted;
    }

    public String getName(){
        return name;
    }

    public String getEnchantment(){
        return enchantment;
    }

    public void setMaterial(String material){
        this.material = material;
    }

    public void setDurability(int durability){
        this.durability = durability;
    }

    public void setEquipped(boolean equipped){
        this.equipped = equipped;
    }

    public void enchant(String enchantment, int level){
        this.enchanted = true;
        this.enchantment = enchantment;
        this.enchantmentLevel = level;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean equals(Pickaxe pickaxe) {
        return this.material == pickaxe.getMaterial()
                && this.durability == pickaxe.getDurability()
                && this.equipped == pickaxe.isEquipped()
                && this.enchanted == pickaxe.enchanted
                && this.enchantment == pickaxe.getEnchantment()
                && this.name == pickaxe.getName();
    }
}
