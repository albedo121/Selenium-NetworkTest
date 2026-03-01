package tests;

import org.openqa.selenium.WebDriver;
import pages.TestPage_Fast;
import pages.TestPage_Ookla;
import pages.TestPage_PacketLoss;

import java.sql.Driver;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Scanner object to get user input
        Scanner input = new Scanner(System.in);

        // Displays a menu prompting the user to select a network test to perform.
        while(true)
        {
            System.out.println("WELCOME TO NETWORK TEST. PLEASE CHOOSE TEST TO PERFORM-");
            System.out.println("1 - Speed test - Fast.com");
            System.out.println("2 - Speed test - Speedtest.net");
            System.out.println("3 - Packet loss test - PacketLoss.com");

            //Get user input
            int choice = input.nextInt();

            //Based on user input perform required test
            switch(choice)
            {
                case 1:
                    TestPage_Fast fastTest = new TestPage_Fast();
                    fastTest.startTest();
                    break;

                case 2:
                    TestPage_Ookla OolkaTest = new TestPage_Ookla();
                    break;

                case 3:
                    TestPage_PacketLoss PacketLossTest = new TestPage_PacketLoss();
                    PacketLossTest.startTest();
                    break;

                default:
                    System.out.println("Invalid choice");
                    continue;
            }
            break;
        }
    }
}
