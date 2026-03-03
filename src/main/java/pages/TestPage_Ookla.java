package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.Visibility;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPage_Ookla {
    //To disable selenium warnings and keep console clean.
    static {
        Logger.getLogger("org.openqa.selenium")
                .setLevel(Level.SEVERE);
    }

    public void startTest(){
        //INITIALIZE SELENIUM AND SETUP--------------------------------------
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));  //Explicit wait of 120s

        //TEST BEGINS FROM HERE----------------------------------------------
        //Log message
        System.out.println("***** SPEEDTEST.NET *****");

        //Go to url
        String url = "https://www.speedtest.net/";
        driver.get(url);

        //Locators- pre test
        By GoButton_Locator = By.xpath("//span[@class='start-text']");   //Go/Start button locator
        By Isp_Locator = By.xpath("(//div[@class='result-label'])[7]");   //Isp locator
        By Ip_Locator = By.xpath("(//div[@title='{{ipType}}'])");   //Ip locator
        By Server_Locator = By.xpath("(//a[@class='hostUrl'])[1]");   //Server locator
        By ServerRegion_Locator = By.xpath("(//span[@class='name'])[1]");   //Server region locator

        //Retrieve Elements
        WebElement Isp = wait.until(ExpectedConditions.visibilityOfElementLocated(Isp_Locator));
        WebElement Ip = wait.until(ExpectedConditions.visibilityOfElementLocated(Ip_Locator));
        WebElement Server = wait.until(ExpectedConditions.visibilityOfElementLocated(Server_Locator));
        WebElement ServerRegion = wait.until(ExpectedConditions.visibilityOfElementLocated(ServerRegion_Locator));

        //Click on Go button to start speed test
        driver.findElement(GoButton_Locator).click();

        //Log message -
        System.out.println("Your ISP: " + Isp.getText());
        System.out.println("Your IP: " + Ip.getText());
        System.out.println("Test Server: " + Server.getText() + ", " + ServerRegion.getText());
        System.out.println("Speed test in progress. Please wait ...");

        //Locators- post test
        By DownloadSpeed_Locator = By.cssSelector(".result-data-value.download-speed");
        By DownloadSpeedUnit_Locator = By.xpath("(//span[@class='result-data-unit'])[1]");
        By UploadSpeed_Locator = By.cssSelector(".result-data-value.upload-speed");
        By UploadSpeedUnit_Locator = By.xpath("(//span[@class='result-data-unit'])[2]");

        By IdleLatency_Locator = By.cssSelector(".ping-speed");
        By DownloadLatency_Locator = By.xpath("(//span[@class='result-data-value'])[1]");
        By UploadLatency_Locator = By.xpath("(//span[@class='result-data-value'])[2]");
        By LatencyUnit_Locator = By.xpath("(//span[@class='result-data-unit'])[3]");

        By BrowsingQuality_Locator = By.xpath("(//div[@class='MuiBox-root css-dfpqc0'])[1]");
        By GamingQuality_Locator = By.xpath("(//div[@class='MuiBox-root css-dfpqc0'])[2]");
        By StreamingQuality_Locator = By.xpath("(//div[@class='MuiBox-root css-dfpqc0'])[3]");
        By VideoQuality_Locator = By.xpath("(//div[@class='MuiBox-root css-dfpqc0'])[4]");

        By Connections_Locator = By.xpath("(//div[@class='result-data'])[2]");

        //Retrieve Elements
        WebElement DownloadSpeed = wait.until(ExpectedConditions.visibilityOfElementLocated(DownloadSpeed_Locator));
        WebElement DownloadSpeedUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(DownloadSpeedUnit_Locator));
        WebElement UploadSpeed = wait.until(ExpectedConditions.visibilityOfElementLocated(UploadSpeed_Locator));
        WebElement UploadSpeedUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(UploadSpeedUnit_Locator));

        WebElement IdleLatency = wait.until(ExpectedConditions.visibilityOfElementLocated(IdleLatency_Locator));
        WebElement DownloadLatency = wait.until(ExpectedConditions.visibilityOfElementLocated(DownloadLatency_Locator));
        WebElement UploadLatency = wait.until(ExpectedConditions.visibilityOfElementLocated(UploadLatency_Locator));
        WebElement LatencyUnit = wait.until(ExpectedConditions.visibilityOfElementLocated(LatencyUnit_Locator));

        /*WebElement BrowsingQualityText = wait.until(ExpectedConditions.visibilityOfElementLocated(BrowsingQuality_Locator));
        WebElement GamingQualityText = wait.until(ExpectedConditions.visibilityOfElementLocated(GamingQuality_Locator));
        WebElement StreamingQualityText = wait.until(ExpectedConditions.visibilityOfElementLocated(StreamingQuality_Locator));
        WebElement VideoQualityText = wait.until(ExpectedConditions.visibilityOfElementLocated(VideoQuality_Locator)); */

        WebElement Connections = wait.until(ExpectedConditions.visibilityOfElementLocated(Connections_Locator));


        //Log message - Print final results
        System.out.println();
        System.out.println("********** FINAL TEST RESULTS **********");
        System.out.println("Download Speed: " + DownloadSpeed.getText() + " " + DownloadSpeedUnit.getText());
        System.out.println("Upload Speed: " + UploadSpeed.getText() + " " + UploadSpeedUnit.getText());
        System.out.print("Latency (" + LatencyUnit.getText() + "): ");
        System.out.println("Idle ("+IdleLatency.getText()+"), Download("+DownloadLatency.getText()+"), Upload("+UploadLatency.getText()+")");
        System.out.println("Connections: " + Connections.getText());

    }
}
