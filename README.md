# 🌐 Network Speed Test Automation

A Java-based Selenium automation suite that runs multiple network diagnostic tests from popular speed test websites and prints results directly to the console.

---

## 📋 Overview

This project automates four different network tests using Selenium WebDriver and Chrome:

| Test | Website | Measures |
|------|---------|----------|
| `TestPage_Fast` | [fast.com](https://fast.com) | Download & Upload Speed, Latency |
| `TestPage_Ookla` | [speedtest.net](https://speedtest.net) | Download & Upload Speed, Latency, Connections |
| `TestPage_Dns` | [dnsspeedtest.online](https://dnsspeedtest.online) | DNS Server Response Times |
| `TestPage_PacketLoss` | [packetlosstest.com](https://packetlosstest.com) | Packet Loss, Latency, Jitter |

---

## 🛠️ Prerequisites

- Java 11+
- Maven or Gradle
- Google Chrome browser
- ChromeDriver (matching your Chrome version)

### Dependencies

Add the following to your `pom.xml`:

```xml
<dependencies>
    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.x.x</version>
    </dependency>

    <!-- FlipTables (used for DNS results table) -->
    <dependency>
        <groupId>com.jakewharton.fliptables</groupId>
        <artifactId>fliptables</artifactId>
        <version>1.1.0</version>
    </dependency>
</dependencies>
```

---

## 🚀 Usage

Each test class has a `startTest()` method. Call them individually from your main class or test runner:

```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new TestPage_Fast().startTest();
        new TestPage_Ookla().startTest();
        new TestPage_Dns().startTest();
        new TestPage_PacketLoss().startTest();
    }
}
```

---

## 📊 Sample Output

**fast.com**
```
***** FAST.COM *****
Download speed test in progress --> 45 Mbps 67 Mbps 89 Mbps
Upload speed test in progress --> 12 Mbps 18 Mbps

********** FINAL TEST RESULTS **********
Download Speed- 89 Mbps
Upload Speed- 18 Mbps
Unloaded Latency- 12 ms
Loaded Latency- 28 ms
Client Location- Mumbai, India
Client IP- 103.x.x.x
Server(s)- Mumbai
```

**dnsspeedtest.online**
```
***** DNSSPEEDTEST.ONLINE *****
DNS speed test in progress. Please wait...
╔══════════════╦══════════╦════════════╦═════════════╦══════════╗
║    SERVER    ║ MIN(ms)  ║ MEDIAN(ms) ║ AVERAGE(ms) ║ MAX(ms)  ║
╠══════════════╬══════════╬════════════╬═════════════╬══════════╣
║ Google       ║ 4        ║ 6          ║ 7           ║ 18       ║
║ Cloudflare   ║ 3        ║ 5          ║ 5           ║ 12       ║
╚══════════════╩══════════╩════════════╩═════════════╩══════════╝
```

---

## ⚙️ How It Works

- **Selenium WebDriver** launches Chrome and navigates to each test URL.
- **WebDriverWait** with explicit conditions is used to wait for test completion rather than fixed sleeps wherever possible.
- Results are parsed from the DOM and printed to the console in a readable format.
- The DNS test uses **FlipTable** to render results as an ASCII table.

---

## ⚠️ Notes

- Tests open a real Chrome browser window — do not interact with it while a test is running.
- Test duration varies by network speed; allow at least **2–3 minutes** per run.
- Locators are tied to the current DOM structure of each website. If a site updates its UI, locators may need to be updated.
- ChromeDriver must match your installed Chrome version. Download from [chromedriver.chromium.org](https://chromedriver.chromium.org).

---

## 📄 License

This project is open source. Feel free to use and modify it for personal or educational purposes.
