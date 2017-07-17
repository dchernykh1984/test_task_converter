package libs;

import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Denis on 7/17/2017.
 */
public class ConverterPage {
    public enum FromTo {
        FROM(0), TO(1);
        int value;
        FromTo(int val) {
            value = val;
        }
        int getValue() {
            return value;
        }
    }
    private FromTo stateTabFromTo = FromTo.FROM;
    private Currency stateCurrencies[] = {CurrenciesList.get("EUR"), CurrenciesList.get("USD")};
    private String searchCurrenciesFilter = "";
    private String currencyValues[] = {"0", "0"};

    private String ID_SEARCH_CURRENCIES = "find-currencies";

    String PATH_CONVERTER_TILE = "//div[@id='converter-mount-node']";
    private String TEPLATE_PATH_POPULAR_CURRENCY = PATH_CONVERTER_TILE +
            "//ul[@class='converter-popularList']/li[text()='%s']";
    private String PATH_POPULAR_CURRENCY_SELECTED_ITEMS = PATH_CONVERTER_TILE +
            "//div/ul[@class='converter-popularList']/li[@class='converter-popularItem converter-popularItem__selected']";
    private String PATH_GENERAL_CURRENCY_SELECTED_ITEMS = PATH_CONVERTER_TILE +
            "//ul[@class='converter-currencies']/li[@class='converter-currenciesItem converter-currenciesItem__selected']";
    private String TEPLATE_PATH_GENERAL_CURRENCY = PATH_CONVERTER_TILE +
            "//ul[@class='converter-currencies']/li/span[@class='converter-currenciesName' and text()='%s']";
    private String PATH_FROM_TO_TABS = "//div[@class='converter-tab']";
    private String TEMPLATE_FROM_TO_TAB = PATH_FROM_TO_TABS + "/div[%d]";
    private String TEMPLATE_FROM_TO_CURRENCY = TEMPLATE_FROM_TO_TAB + "//span";
    private String TEMPLATE_FROM_TO_CURRENCY_INPUT = TEMPLATE_FROM_TO_TAB + "//input";
    private String TO_TOP_BUTTON_LOCATOR = "//div[@class='ui-button-top js-button-top fixed']";
    private String PATH_CLEAR_BUTTON = PATH_FROM_TO_TABS + "/div[@class='converter-tabBtns']/div";
    private String PATH_CLOSE_QUESTION_POPUP = "//div[@id='nanorep-fw']//button[contains(@class,'widget-floating__button widget-floating__button--close')]";

    private String selectedTabClass = "converter-tabItem converter-tabItem__selected";
    private String notSelectedTabClass = "converter-tabItem";

    public ConverterPage closeQuestionPopup() {
        $(By.xpath(PATH_CLOSE_QUESTION_POPUP)).click();
        return this;
    }


    public ConverterPage toPageTop() {
        if($(By.xpath(TO_TOP_BUTTON_LOCATOR)).isDisplayed())  {
            $(By.xpath(TO_TOP_BUTTON_LOCATOR)).click();
        }
        return this;
    }

    public ConverterPage switchCurrenciesTab(FromTo ft) {
        toPageTop();
        stateTabFromTo = ft;
        $(By.xpath(String.format(TEMPLATE_FROM_TO_TAB, stateTabFromTo.getValue()+1))).click();
        return this;
    }

    public ConverterPage changePopularCurrency(Currency cur) {
        $(By.xpath(String.format(TEPLATE_PATH_POPULAR_CURRENCY,cur.getShortDescription()))).click();
        setCurrentCurrency(cur);
        return this;
    }

    public ConverterPage searchByCurrency(String filter) {
        toPageTop();
        $(By.id(ID_SEARCH_CURRENCIES)).setValue(filter);
        searchCurrenciesFilter = filter;
        return this;
    }
    public ConverterPage changeGeneralCurrency(Currency cur) {
        toPageTop();
        $(By.xpath(String.format(TEPLATE_PATH_GENERAL_CURRENCY, cur.getLongDescription()))).click();
        setCurrentCurrency(cur);
        return this;
    }

    public ConverterPage setCurrencyValue(String value) {
        toPageTop();
        $(By.xpath(String.format(TEMPLATE_FROM_TO_CURRENCY_INPUT,  stateTabFromTo.getValue()+1))).setValue(value);
        currencyValues[stateTabFromTo.getValue()] = value;
        currencyValues[(stateTabFromTo.getValue() +1)%2] =
                $(By.xpath(String.format(TEMPLATE_FROM_TO_CURRENCY_INPUT,  ((stateTabFromTo.getValue()+1) % 2)+1))).getValue();
        return this;
    }

    private ConverterPage setCurrentCurrency(Currency cur) {
        stateCurrencies[stateTabFromTo.getValue()] = cur;
        return this;
    }

    private Currency getCurrentCurrency() {
        return stateCurrencies[stateTabFromTo.getValue()];
    }

    public ConverterPage checkPageState() {
        //check tab currencies
        for(int i = 0;i<=1;i++) {
            checkSelectedItems(String.format(TEMPLATE_FROM_TO_CURRENCY,i+1), stateCurrencies[i].getShortDescription());
            Assert.assertTrue("Incorrect selected tab",
                    $(By.xpath(
                            String.format(TEMPLATE_FROM_TO_TAB, i+1))).
                            getAttribute("class").
                            equals((i==stateTabFromTo.getValue()?selectedTabClass:notSelectedTabClass)));
            Assert.assertTrue("Incorrect value of currency",
                    $(By.xpath(
                            String.format(TEMPLATE_FROM_TO_CURRENCY_INPUT, stateTabFromTo.getValue()+1))).
                            getValue().
                            equals(currencyValues[stateTabFromTo.getValue()]));
        }
        //check popular currency
        checkSelectedItems(PATH_GENERAL_CURRENCY_SELECTED_ITEMS, getCurrentCurrency().getFullDescription());
        if(getCurrentCurrency().isPopular()) {
            checkSelectedItems(PATH_POPULAR_CURRENCY_SELECTED_ITEMS, getCurrentCurrency().getShortDescription());
        } else {
            checkSelectedItems(PATH_POPULAR_CURRENCY_SELECTED_ITEMS);
        }
        //check currency names filter
        Assert.assertTrue("Currencies filter has incorrect value",
                $(By.id(
                        ID_SEARCH_CURRENCIES)).
                        getValue().
                        equals(searchCurrenciesFilter));
        return this;
    }

    public ConverterPage clearForm() {
        toPageTop();
        $(By.xpath(PATH_CLEAR_BUTTON)).click();
        searchCurrenciesFilter = "";
        currencyValues[0] = "0";
        currencyValues[1] = "0";
        return this;
    }

    private void checkSelectedItems(String locator, String... values) {
        $$(By.xpath(locator)).shouldHaveSize(values.length);
        for(int i = 0;i<values.length;i++) {
            Assert.assertTrue($$(By.xpath(locator)).get(i).getText().equalsIgnoreCase(values[i]));
        }
    }
}
