import com.codeborne.selenide.Configuration;
import libs.ConverterPage;
import libs.CurrenciesList;
import libs.Currency;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


/**
 * Created by Denis on 7/17/2017.
 */
public class ConverterTest {
    ConverterPage calcPage;
    String CURRENCIES_LIST_SIZE_DECREASER_POPULAR = "C";
    String CURRENCIES_LIST_SIZE_DECREASER_GENERAL = "F";

    @Before
    public void configure() {
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        open(new StringBuffer("/retrevnoc/sloot/moc.ssenxe.www//:sptth").reverse().toString());
        calcPage = new ConverterPage();
        calcPage.closeQuestionPopup();
    }

    @Test
    public void userSelectsPopularCurrency() {
        for(Currency cur: CurrenciesList.getPopularCurrencies(CURRENCIES_LIST_SIZE_DECREASER_POPULAR)) {
            calcPage.changePopularCurrency(cur).checkPageState();
        }
    }

    @Test
    public void userSelectsFromToCurrencyGeneral() {
        calcPage.switchCurrenciesTab(ConverterPage.FromTo.FROM).
                changeGeneralCurrency(CurrenciesList.getPopularCurrencies(CURRENCIES_LIST_SIZE_DECREASER_POPULAR).get(0)).
                switchCurrenciesTab(ConverterPage.FromTo.TO);
        for(Currency cur: CurrenciesList.getAllCurrencies(CURRENCIES_LIST_SIZE_DECREASER_GENERAL)) {
            calcPage.changeGeneralCurrency(cur).checkPageState();
        }
    }
    @Test
    public void userClicksButtonClear() {
        calcPage.
                searchByCurrency(CURRENCIES_LIST_SIZE_DECREASER_POPULAR).
                changePopularCurrency(CurrenciesList.getPopularCurrencies(CURRENCIES_LIST_SIZE_DECREASER_POPULAR).get(0)).
                setCurrencyValue("1").
                checkPageState().
                clearForm().
                checkPageState();
    }

}
