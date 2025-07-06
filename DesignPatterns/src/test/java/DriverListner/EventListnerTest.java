package DriverListner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.Test;

public class EventListnerTest {

    @Test
    public void webdriverListenerTest(){
        WebDriver driver=new EdgeDriver();
        WebDriverListener myListner = new MyListener(driver);
        driver=new EventFiringDecorator<>(myListner).decorate(driver);
        driver.get("https://www.google.com/");
        System.out.println("Page Loaded");
        System.out.println("Title is "+driver.getTitle());
        try {
            driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div/sectionofthedevil")).click();
        }catch (Exception e){
            //e.printStackTrace();
        }
        driver.close();
    }
}
