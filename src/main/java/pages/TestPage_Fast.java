package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPage_Fast {

    //To disable selenium warnings and keep console clean.
    static {
        Logger.getLogger("org.openqa.selenium")
                .setLevel(Level.SEVERE);
    }

    //Start Test function
    public void startTest() throws InterruptedException {
        //INITIALIZE SELENIUM AND SETUP--------------------------------------
        WebDriver driver = new ChromeDriver();  //Open chrome browser
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));  //Explicit wait of 20s

        //TEST BEGINS FROM HERE----------------------------------------------
        //Go to url
        String url = "https://fast.com/";
        driver.get(url);

        // Displaying speed test status message and going to new line for better readability
        System.out.println("***** FAST.COM *****");

        //Locators for download speed display elements: value and its corresponding unit
        By DownloadSpeedValue_Locator = By.id("speed-value");
        By DownloadSpeedUnit_Locator = By.id("speed-units");

        //Here we will keep printing the speed values on console when test is in progress
        System.out.print("Download speed test in progress --> ");
        Thread.sleep(1000);
        while(true)
        {
            Thread.sleep(400);  //Putting sleep so that console is not flooded
            //Find speed and unit element
            WebElement DownloadSpeedValue_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(DownloadSpeedValue_Locator));
            WebElement DownloadSpeedUnit_Element = wait.until((ExpectedConditions.visibilityOfElementLocated(DownloadSpeedUnit_Locator)));

            //Keep printing the speed values until test is in progress
            System.out.print(DownloadSpeedValue_Element.getText() + " " + DownloadSpeedUnit_Element.getText() + " ");

            //Break the while loop when test is completed
            String DownloadSpeedValue_ClassName = DownloadSpeedValue_Element.getAttribute("class");
            if(DownloadSpeedValue_ClassName.contains("succeeded"))
            {
                System.out.println();
                break;
            }
        }

        //Click on show more button
        By MoreInfoButton_Locator = By.id("show-more-details-link");
        driver.findElement(MoreInfoButton_Locator).click();

        //Locator for upload speed value
        By UploadSpeedValue_Locator = By.id("upload-value");
        By UploadSpeedUnit_Locator = By.id("upload-units");

        //Wait for upload test to be over
        System.out.print("Upload speed test in progress --> ");
        while(true)
        {
            Thread.sleep(1800);  //Putting sleep so that console is not flooded
            WebElement UploadSpeedValue_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(UploadSpeedValue_Locator));
            WebElement UploadSpeedUnit_Element =  wait.until(ExpectedConditions.visibilityOfElementLocated(UploadSpeedUnit_Locator));

            System.out.print(UploadSpeedValue_Element.getText() + " " + UploadSpeedUnit_Element.getText() + " ");

            String UploadSpeedValue_ClassName = UploadSpeedValue_Element.getAttribute("class");

            if(UploadSpeedValue_ClassName.contains("succeeded"))
            {
                System.out.println();
                break;
            }

        }

        //Locators from more info section
        By UnloadedLatencyValue_Locator = By.id("latency-value");
        By UnloadedLatencyUnit_Locator = By.id("latency-units");
        By LoadedLatencyValue_Locator = By.id("bufferbloat-value");
        By LoadedLatencyUnit_Locator = By.id("bufferbloat-units");
        By ClientLocation_Locator = By.id("user-location");
        By ClientIp_Locator = By.id("user-ip");
        By ServerInfo_Locator = By.id("server-locations");

        //Print final results
        System.out.println();
        System.out.println("********** FINAL TEST RESULTS **********");
        System.out.println("Download Speed- " + driver.findElement(DownloadSpeedValue_Locator).getText() + " " + driver.findElement(DownloadSpeedUnit_Locator).getText());
        System.out.println("Upload Speed- " + driver.findElement(UploadSpeedValue_Locator).getText() + " " + driver.findElement(UploadSpeedUnit_Locator).getText());
        System.out.println("Unloaded Latency- " + driver.findElement(UnloadedLatencyValue_Locator).getText() + " " + driver.findElement(UnloadedLatencyUnit_Locator).getText());
        System.out.println("Loaded Latency- " + driver.findElement(LoadedLatencyValue_Locator).getText() + " " + driver.findElement(LoadedLatencyUnit_Locator).getText());
        System.out.println("Client Location- " + driver.findElement(ClientLocation_Locator).getText());
        System.out.println("Client IP- " + driver.findElement(ClientIp_Locator).getText());
        System.out.println("Server(s)- " + driver.findElement(ServerInfo_Locator).getText());

        Thread.sleep(5000);
        driver.quit();

    }


}
