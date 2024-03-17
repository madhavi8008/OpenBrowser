package org.example;

//package org.example;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BrowserOpener implements CommandLineRunner {
//
//    @Value("${spring.application.url}")
//    private String applicationUrl;
//
//    @Override
//    public void run(String... args) {
//        // Set the path to your ChromeDriver executable
//        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
//
//        // Initialize the WebDriver
//        WebDriver driver = new ChromeDriver();
//
//        // Open the browser window
//        driver.get(applicationUrl);
//
//        // Wait for 5 seconds to show results
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Close the browser window
//        driver.quit();
//    }
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class AllGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllGoodApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openEdgeBrowser() {
        String url = "http://localhost:8099/check?name=Super"; // Replace with your desired URL
        openBrowser(url);
    }

    public static void openBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            // Fallback for systems without Desktop support
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

