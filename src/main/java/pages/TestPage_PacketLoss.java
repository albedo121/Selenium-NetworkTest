package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPage_PacketLoss {

    //To disable selenium warnings and keep console clean.
    static {
        Logger.getLogger("org.openqa.selenium")
                .setLevel(Level.SEVERE);
    }

    public void startTest(){

        //Initialize setup before test
        WebDriver driver = new ChromeDriver();
        String url = "https://packetlosstest.com/";
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));  //Explicit wait of 120s

        //Go to url
        driver.get(url);

        //Pre test locators stored here
        By Button_Locator = By.id("start");  //Start test button
        By Result_Locator = By.id("results");  //Result section
        By Server_Locator = By.id("server");  //Server dropdown
        By TestData_Locator = By.xpath("//details[@id='settings']//p[2]");

        //Get Test settings data - server location, data used, pings
        WebElement ServerDropdown_Element = driver.findElement(Server_Locator);
        Select Server_Dropdown = new Select(ServerDropdown_Element);
        WebElement SelectedServer = Server_Dropdown.getFirstSelectedOption();
        String TestData = driver.findElement(TestData_Locator).getText();

        //Click on start test
        driver.findElement(Button_Locator).click();

        //Log message - Test started
        System.out.println("***** PACKETLOSS.COM *****");
        System.out.println("Packet loss test in progress. Please wait...");
        System.out.println(TestData);

        //Wait for test result section to appear when test is over
        wait.until(ExpectedConditions.visibilityOfElementLocated(Result_Locator));

        //Post test locators for extracting final results
        By UploadPacketLoss_Locator = By.xpath("(//span[@class='percent'])[1]");   //Upload packet loss (%) locator
        By UploadPacketCount_Locator = By.xpath("(//div[@class='counts'])[1]");   //Upload packets count locator

        By TotalPacketLoss_Locator = By.xpath("(//span[@class='percent'])[2]");   //Total packet loss (%) locator
        By TotalPacketCount_Locator = By.xpath("(//div[@class='counts'])[2]");   //Total packet loss count locator

        By DownloadPacketLoss_Locator = By.xpath("(//span[@class='percent'])[3]");   //Download packet loss(%) locator
        By DownloadPacketCount_Locator = By.xpath("(//div[@class='counts'])[3]");   //Download packet loss locator

        By LatePackets_Locator = By.xpath("(//span[@class='percent'])[4]");   //Late packets (%) locator
        By LatePacketsCount_Locator = By.xpath("(//div[@class='counts'])[4]");   //Late packets count locator

        By AverageLatency_Locator = By.xpath("//details[@id='results']/p[1]");   //Average latency locator
        By AverageJitter_Locator = By.xpath("//details[@id='results']/p[2]");  //Average jitter locator

        //Find Elements based on above locators
        WebElement UploadPacketLoss = driver.findElement(UploadPacketLoss_Locator);   //Upload packet loss (%) element
        WebElement UploadPacketCount = driver.findElement(UploadPacketCount_Locator);  //Upload packets count element

        WebElement DownloadPacketLoss = driver.findElement(DownloadPacketLoss_Locator);   //Download packet loss(%) element
        WebElement DownloadPacketCount = driver.findElement(DownloadPacketCount_Locator);   //Total packet loss count element

        WebElement TotalPacketLoss = driver.findElement(TotalPacketLoss_Locator);   //Total packet loss (%) element
        WebElement TotalPacketCount = driver.findElement(TotalPacketCount_Locator);  //Total packet loss count element

        WebElement LatePackets = driver.findElement(LatePackets_Locator);   //Late packets (%) element
        WebElement LatePacketsCount = driver.findElement(LatePacketsCount_Locator);   //Late packets count element

        WebElement AverageLatency = driver.findElement(AverageLatency_Locator);   //Average latency element
        WebElement AverageJitter = driver.findElement(AverageJitter_Locator);   //Average jitter element


        //Log message - Print final message
        System.out.println();
        System.out.println("********** FINAL TEST RESULTS **********");
        System.out.println("Upload Packet Loss: " + UploadPacketLoss.getText() + "% - " + UploadPacketCount.getText());
        System.out.println("Download Packet Loss: " + DownloadPacketLoss.getText() + "% - " + DownloadPacketCount.getText());
        System.out.println("Total Packet Loss: " + TotalPacketLoss.getText() + "% - " + TotalPacketCount.getText());
        System.out.println("Late Packets: " + LatePackets.getText() + "% - " + LatePacketsCount.getText());
        System.out.println(AverageLatency.getText());
        System.out.println(AverageJitter.getText());

        //Quit driver
        driver.quit();

    }
}
