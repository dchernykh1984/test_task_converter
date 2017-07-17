package libs;

/**
 * Created by Denis on 7/17/2017.
 */
public class Currency {
    String shortDescription;
    String longDescription;
    Currency(String shortD, String longD) {
        this.shortDescription = shortD;
        this.longDescription = longD;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public String getFullDescription() {
        return shortDescription + longDescription;
    }
    public String getLongDescription()  {
        return longDescription;
    }
    public boolean equals(Currency cur) {
        return cur.getLongDescription().equalsIgnoreCase(longDescription) &&
                cur.getShortDescription().equalsIgnoreCase(shortDescription);
    }

    public boolean isPopular() {
        for(Currency cur:CurrenciesList.getPopularCurrencies("")) {
            if(equals(cur)) return true;
        }
        return false;
    }

}
