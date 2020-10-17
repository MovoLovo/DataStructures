class Main {
    public static void main(String[] args) {
        Stock google = new Stock("GOOG", "Google, Inc.", 1483, 681511);

        System.out.println(google);

        google.adjustPrice(100);

        System.out.println(google);
    }
}