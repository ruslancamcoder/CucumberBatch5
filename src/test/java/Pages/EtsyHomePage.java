package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EtsyHomePage {
    WebDriver driver= Driver.getDriver("browser");
    public EtsyHomePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@id='catnav-primary-link-10855']/parent::a")
    public WebElement jewelry;

    @FindBy(xpath = " //span[@id='catnav-primary-link-10923']/parent::a")
    public WebElement clothing;

    @FindBy(xpath = " //span[@id='catnav-primary-link-891']/parent::a")
    public WebElement home;

    @FindBy(xpath = " //span[@id='catnav-primary-link-10983']/parent::a")
    public WebElement wedding;

    @FindBy(xpath = " //span[@id='catnav-primary-link-11049']/parent::a")
    public WebElement toys;

    @FindBy(xpath = "//span[@id='catnav-primary-link-66']/parent::a")
    public WebElement art;

    @FindBy(id = "global-enhancements-search-query")
    public WebElement searchBox;

    @FindBy(xpath = "//h3[@class='text-gray text-truncate mb-xs-0 text-body ']")
    public List<WebElement>items;

    @FindBy(xpath = "//a[@href='https://www.etsy.com/search?q=carpet&explicit=1&min=1000&max=&price_bucket=1']")
    public WebElement radioButton;

    @FindBy(xpath = "//span[@class='currency-value']")
    public List<WebElement>prices;

}












