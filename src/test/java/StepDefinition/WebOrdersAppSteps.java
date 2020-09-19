package StepDefinition;

import Pages.OrderPage;
import Pages.WebOrdersHomePage;
import Pages.WebOrdersLoginPage;
import Utilities.CommonUtils;
import Utilities.Driver;
import Utilities.ExcelUtils;
import io.cucumber.datatable.DataTable;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WebOrdersAppSteps {

    WebDriver driver= Driver.getDriver("chrome");
    WebOrdersLoginPage webOrdersLoginPage=new WebOrdersLoginPage();
    WebOrdersHomePage webOrdersHomePage=new WebOrdersHomePage();
    OrderPage orderPage=new OrderPage();

    @Given("User navigates to WebOrders application")
    public void user_navigates_to_web_orders_application() {

        driver.get(CommonUtils.getProperty("WebOrdersURL"));
    }

    @When("User provides username {string} and password {string}")
    public void user_provides_username_and_password(String username, String password) {
        webOrdersLoginPage.logIn(username,password);

    }
    @Then("User validates that application {string} logged in")
    public void user_validates_that_application_logged_in(String condition) {

      if(condition.equalsIgnoreCase("is")){
          String expectedTitle="Web Orders";
          String actualTitle=driver.getTitle();
          Assert.assertEquals(" Actual Title Did not match with expected title",expectedTitle,actualTitle);
      }else if(condition.equalsIgnoreCase("is not")){
          String expectedErrorMessage="Invalid Login or Password.";
          String actualErrorMessage=webOrdersLoginPage.errorMessage.getText();
          Assert.assertEquals(expectedErrorMessage,actualErrorMessage);
      }
    }
    @When("User click on Order part")
    public void user_click_on_order_part() {
        webOrdersHomePage.orderPart.click();
    }
        @When("User adds new order with data")
    public void user_adds_new_order_with_data(DataTable dataTable) {
    List<Map<String ,Object>> data=dataTable.asMaps(String.class,Object.class);
     orderPage.quantity.clear();
     orderPage.quantity.sendKeys(data.get(0).get("Quantity").toString());
     orderPage.customerNameBox.sendKeys(data.get(0).get("Customer name").toString());
     orderPage.streetBox.sendKeys(data.get(0).get("Street").toString());
     orderPage.cityBox.sendKeys(data.get(0).get("City").toString());
     orderPage.stateBox.sendKeys(data.get(0).get("State").toString());
     orderPage.zipBox.sendKeys(data.get(0).get("Zip").toString());
     orderPage.VisaCardBox.click();
     orderPage.cardNumBox.sendKeys(data.get(0).get("Card Nr").toString());
     orderPage.expireDate.sendKeys(data.get(0).get("Expire Date").toString());

    }
    @Then("User click on Process button and validates {string} message")
    public void user_click_on_process_button_and_validates_message(String success) {
        orderPage.processButton.click();
        String actualMessage=orderPage.verifyOrderCreated.getText();
        Assert.assertEquals(success,actualMessage);


    }
    @Then("User click View All Orders part")
    public void user_click_view_all_orders_part() {
    webOrdersHomePage.viewAllOrder.click();

    }
    @Then("User created order is added to list with data")
    public void user_created_order_is_added_to_list_with_data(DataTable dataTable) {
        List<Map<String ,Object>>data=dataTable.asMaps(String.class,Object.class);
        webOrdersHomePage.firstrow.get(1).getText();
        Assert.assertEquals(data.get(0).get("Customer name"),webOrdersHomePage.firstrow.get(1).getText());
        Assert.assertEquals(data.get(0).get("Quantity"),webOrdersHomePage.firstrow.get(3).getText());
        Assert.assertEquals(data.get(0).get("Street"),webOrdersHomePage.firstrow.get(5).getText());
        Assert.assertEquals(data.get(0).get("City"),webOrdersHomePage.firstrow.get(6).getText());
        Assert.assertEquals(data.get(0).get("State"),webOrdersHomePage.firstrow.get(7).getText());
        Assert.assertEquals(data.get(0).get("Zip"),webOrdersHomePage.firstrow.get(8).getText());
        Assert.assertEquals(data.get(0).get("Card Nr"),webOrdersHomePage.firstrow.get(10).getText());
        Assert.assertEquals(data.get(0).get("Expire Date"),webOrdersHomePage.firstrow.get(11).getText());

    }
    @Then("User validates UI headers with {string} excel file expected result")
    public void user_validates_ui_headers_with_excel_file_expected_result(String excelFile) {
        ExcelUtils.openExcelFile(excelFile,"Sheet1");
        String expectedResult=ExcelUtils.getValue(1,4);
        System.out.println(expectedResult);
        String []results=expectedResult.split("\n");

        Assert.assertEquals(results[1],orderPage.productLabel.getText());
        Assert.assertEquals(results[2],orderPage.quantityLabel.getText());
        Assert.assertEquals(results[3],orderPage.pricePerUnitLabel.getText());
        Assert.assertEquals(results[4],orderPage.discountLabel.getText());
        Assert.assertEquals(results[5],orderPage.totalLabel.getText());

    }

    @Then("User updates {string} with {string}")
    public void user_updates_with(String string, String string2) {

        ExcelUtils.setValue(1,6,string2);

    }

    @And("User creates all orders from {string} excel file")
    public void userCreatesAllOrdersFromExcelFile(String fileName) throws InterruptedException {
       int lastRow= ExcelUtils.openExcelFile(fileName,"Sheet1").getLastRowNum();
        List<List<String>> excelData=new ArrayList<>();
        for(int i=1;i<5;i++){
            List<String>rowData=ExcelUtils.getRowValues(i);
            excelData.add(rowData);
        }
        for(int i=0;i<excelData.size();i++){
            orderPage.quantity.clear();
            orderPage.quantity.sendKeys(excelData.get(i).get(0));
            orderPage.customerNameBox.sendKeys(excelData.get(i).get(1));
            orderPage.streetBox.sendKeys(excelData.get(i).get(2));
            orderPage.cityBox.sendKeys(excelData.get(i).get(3));
            orderPage.stateBox.sendKeys(excelData.get(i).get(4));
            orderPage.zipBox.sendKeys(excelData.get(i).get(5).substring(0,excelData.get(i).get(5).indexOf('.')));
            orderPage.VisaCardBox.click();
            orderPage.cardNumBox.sendKeys(excelData.get(i).get(6).substring(0,excelData.get(i).get(6).indexOf('.')));
            orderPage.expireDate.sendKeys(excelData.get(i).get(7));
            orderPage.processButton.click();
            Thread.sleep(5000);

        }
    }

    @Then("User validates that orders from {string} excel file is created")
    public void userValidatesThatOrdersFromExcelFileIsCreated(String arg0) {
    }
}
