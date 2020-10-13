class Stock {
    private String tickerSymbol;
    private String companyName;
    private int price; // In cents
    private double percentChange = 0;
    private int totalShares;
    private long marketCap;

    public Stock(String tickerSymbol, String companyName, int price, int totalShares){
        this.tickerSymbol = tickerSymbol;
        this.companyName = companyName;
        this.price = price;
        this.totalShares = totalShares;
        this.marketCap = price * totalShares;
    }

    // This method will change the price by change (change can be positive or negative).
    // It should also recalculate percentChange and marketCap.
    // percentChange represents the percentage changed
    public void adjustPrice(int change){
        int prevPrice = this.price;
        this.price += change;
        this.percentChange = (((double)this.price / prevPrice) - 1) * 100;
        this.marketCap = this.price * this.totalShares;
    }

    /* Ticker Symbol: GOOG
    * Company: Google, Inc.
    * Current Price: $802 (+7.2%)
    * Market Cap: $538000000000 */
    public String toString(){
        return "Ticker Symbol: " + this.tickerSymbol +"\nCompany: " + this.companyName + "\nCurrent Price: " +
                Integer.toString(this.price) + "\nMarket Cap: " + Long.toString(this.marketCap);
    }
}