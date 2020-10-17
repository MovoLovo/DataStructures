public class RoosterData {
    private static double numChicken = 23.7;

    private static int roosterCrowVolume(){
        return 90;
    }

    private static double getNumRoosters(){
        return numChicken / 23.7;
    }

    private static double getNumChicken(){
        return numChicken;
    }

    public static double totalRoosterVolume(){
        return (10 * Math.log10(getNumRoosters() * Math.pow(10, 9))) + roosterCrowVolume();
    }
}
