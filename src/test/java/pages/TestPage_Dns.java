package pages;


import com.jakewharton.fliptables.FlipTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPage_Dns {
    //To disable selenium warnings and keep console clean.
    static {
        Logger.getLogger("org.openqa.selenium")
                .setLevel(Level.SEVERE);
    }

    //INITIALIZE SELENIUM AND SETUP--------------------------------------
   public void startTest() throws InterruptedException {
       //INITIALIZE SELENIUM AND SETUP--------------------------------------
       WebDriver driver = new ChromeDriver();  //Open chrome browser
       driver.manage().window().maximize();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  //Explicit wait of 20s

       //TEST BEGINS FROM HERE----------------------------------------------
       //Go to url
       String url = "https://dnsspeedtest.online/";
       driver.get(url);

       // Displaying speed test status message and going to new line for better readability
       System.out.println("***** DNSSPEEDTEST.ONLINE *****");

       //Locators - Pre test
       By CheckDnsSpeedButton_Locator = By.id("checkButton");

       //Click on start test button
       wait.until(ExpectedConditions.visibilityOfElementLocated(CheckDnsSpeedButton_Locator)).click();
       System.out.println("DNS speed test in progress. Please wait...");

       //Wait for test to finish
       WebElement LoadingMessage = driver.findElement(By.id("loadingMessage"));
       while(true)
       {
           Thread.sleep(1000);
           if(LoadingMessage.getAttribute("class").contains("hidden"))
               break;
       }

       //Get results-
       getResults(driver);

   }

   public void getResults(WebDriver driver){

       //Locator - post test
       By ResultTable_Locator = By.xpath("//tr[@class='border-b border-gray-300 hover:bg-gray-200 dark:border-gray-600 dark:hover:bg-gray-700']");   //Locator for table

       //Initialize variables to store the data
       String[] parts;   //We will use this array to store the data row wise
       String[] values;  //In parts[3] we get time values in single string. We use this array to split and store values here.
       String server;    //To store server name

       List<WebElement> ResultTable_Element = driver.findElements(ResultTable_Locator);   //Get the entire table element

       String[] headers = {"SERVER", "MIN(ms)", "MEDIAN(ms)", "AVERAGE(ms)", "MAX(ms)"};   //Header when printing final results
       String[][] data = new String[ResultTable_Element.size()][5];   //A 2D array to store time values in rows

       //Extract and store data in above defined arrays
       for (int i = 0; i < ResultTable_Element.size(); i++) {
           server = ResultTable_Element.get(i).getAttribute("data-server-name");
           parts = ResultTable_Element.get(i).getText().split("\n");   //Here we store the first row data
           values = parts[3].split(" ");   //Here we take parts[3] which has time info in string format. We use split and store in values array

           data[i] = new String[]{server, values[0], values[1], values[2], values[3]};   //Store all extracted data in data array.
       }

       //Log message - Print final results
       System.out.println(FlipTable.of(headers, data));

   }
}
