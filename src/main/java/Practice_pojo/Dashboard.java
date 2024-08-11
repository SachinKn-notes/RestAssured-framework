package Practice_pojo;

public class Dashboard {
    private int purchaseAmount;
    private String website;

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "purchaseAmount=" + purchaseAmount +
                ", website='" + website + '\'' +
                '}';
    }
}
