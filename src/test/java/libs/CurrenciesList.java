package libs;

import java.util.LinkedList;

/**
 * Created by Denis on 7/17/2017.
 */
public class CurrenciesList {
    private static Currency[] popularCurrencies = {
            new Currency("USD","United States Dollar"),
            new Currency("EUR","Euro"),
            new Currency("CHF","Swiss Franc"),
            new Currency("JPY","Japanese Yen"),
            new Currency("AUD","Australian Dollar"),
            new Currency("CAD","Canadian Dollar")
    };
    private static Currency[] allCurrencies = {
            new Currency("AED", "United Arab Emirates Dirham"),
            new Currency("BDT", "Bangladeshi Taka"),
            new Currency("BHD", "Bahraini Dinar"),
            new Currency("BND", "Brunei Dollar"),
            new Currency("CNY", "Chinese Yuan (Renminbi)"),
            new Currency("DZD", "Algerian Dinar"),
            new Currency("EGP", "Egyptian Pound"),
            new Currency("GHS", "Ghanaian cedi"),
            new Currency("HKD", "Hong Kong Dollar"),
            new Currency("IDR", "Indonesian Rupiah"),
            new Currency("INR", "Indian Rupee"),
            new Currency("JOD", "Jordanian Dinar"),
            new Currency("JPY", "Japanese Yen"),
            new Currency("KES", "Kenyan Shilling"),
            new Currency("KRW", "South-Korean Won"),
            new Currency("KWD", "Kuwaiti Dinar"),
            new Currency("LKR", "Sri Lanka Rupee"),
            new Currency("LBP", "Lebanese Pound"),
            new Currency("MYR", "Malaysian Ringgit"),
            new Currency("MAD", "Moroccan Dirham"),
            new Currency("NPR", "Nepalese Rupee"),
            new Currency("NGN", "Nigerian Naira"),
            new Currency("OMR", "Omani Riyal"),
            new Currency("PHP", "Philippine Peso"),
            new Currency("PKR", "Pakistani rupee"),
            new Currency("QAR", "Quatar Riyal"),
            new Currency("SAR", "Saudi Riyal"),
            new Currency("SGD", "Singapore Dollar"),
            new Currency("SYP", "Syrian Pound"),
            new Currency("THB", "Thai Baht"),
            new Currency("TND", "Tunisian Dinar"),
            new Currency("TWD", "Taiwan Dollar"),
            new Currency("UZS", "Uzbekistan Som"),
            new Currency("UGX", "Uganda Shilling"),
            new Currency("VUV", "Vanuatu Vatu"),
            new Currency("VND", "Vietnamese Dong"),
            new Currency("XOF", "CFA Franc BCEAO"),
            new Currency("ZAR", "South African Rand"),
            new Currency("BGN", "Bulgarian Lev"),
            new Currency("BYR", "Belarusian Ruble"),
            new Currency("CHF", "Swiss Franc"),
            new Currency("DKK", "Danish Krone"),
            new Currency("EEK", "Estonian Kroon"),
            new Currency("EUR", "Euro"),
            new Currency("GBP", "Pound Sterling"),
            new Currency("GEL", "Georgian Lari"),
            new Currency("HRK", "Croatian Kuna"),
            new Currency("HUF", "Hungarian Forint"),
            new Currency("ILS", "Israeli Shekel"),
            new Currency("ISK", "Iceland Krona"),
            new Currency("LVL", "Latvian Lat"),
            new Currency("LTL", "Lithuanian Litas"),
            new Currency("NOK", "Norwegian Krone"),
            new Currency("PLN", "Polish Zloty"),
            new Currency("RON", "Romanian Leu New"),
            new Currency("RUB", "Russian Rouble"),
            new Currency("SEK", "Swedish Krona"),
            new Currency("TRY", "New Turkish Lira"),
            new Currency("UAH", "Ukrainian Hryvnia"),
            new Currency("ARS", "Argentine Peso"),
            new Currency("BRL", "Brazilian Real"),
            new Currency("CAD", "Canadian Dollar"),
            new Currency("COP", "Colombian Peso"),
            new Currency("CLP", "Chilean Peso"),
            new Currency("MXN", "Mexican Peso"),
            new Currency("USD", "United States Dollar"),
            new Currency("AUD", "Australian Dollar"),
            new Currency("NZD", "New Zealand Dollar")
    };
    public static Currency[] getPopularCurrencies(String filter) {
        return filterArray(popularCurrencies, filter);
    }

    public static Currency[] getAllCurrencies(String filter) {
        return filterArray(allCurrencies, filter);
    }

    private static Currency[] filterArray(Currency[] curs, String filter) {
        LinkedList<Currency> result = new LinkedList<>();
        for(Currency cur: curs) {
            if(cur.getShortDescription().contains(filter) || cur.getLongDescription().contains(filter)) {
                result.add(cur);
            }
        }
        return (Currency[]) result.toArray();
    }

    public static Currency get(String shortName) {
        for(Currency cur: allCurrencies) {
            if(cur.getShortDescription().equalsIgnoreCase(shortName)) {
                return cur;
            }
        }
        throw new RuntimeException("Incorrect country short name: " + shortName);
    }




}
