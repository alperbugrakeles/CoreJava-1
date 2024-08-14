package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Locators3 {

    WebDriver driver;

    @BeforeClass
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void test1(){

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        //Locators usinf siblings with xpath traverse
        // Question-> Difference between absolute and relative xpath
        // //header/div/button[1]/following-sibling::button[1]  *> aşagı inip karsdesine tutunduk
        String txt = driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]")).getText();
        System.out.println(txt);

        // //header/div/button[1]/parent::div  burada da parenta geri gittik yani yukarı cıktk
        // eger header a da yukarı cıkmak istersek  //header/div/button[1]/parent::div/parent::header
        // eger oradan da baska bir elemente gecmek istersem //header/div/button[1]/parent::div/parent::header/a
        driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]"));
        driver.findElement(By.xpath("//header/div/button[1]/parent::div/button[2]"));
        // yukarıdaki 2 x path de aynı login butonunun üzerinde, farklı travellar anlatmak için
        // 2 elementinde textinin aynı oldugunu anlamak için yazdıralım
        String text1 =  driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]")).getText();
        String text2 =  driver.findElement(By.xpath("//header/div/button[1]/parent::div/button[2]")).getText();
        Assert.assertEquals(text1,text2);
    }

@Test
public void test3() throws InterruptedException {
        driver.get("https://www.google.com"); // waits until all the components fully loaded
        driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/"); // dont wait until all the components fully loaded
        driver.navigate().back();
        driver.navigate().forward();
        Thread.sleep(2000);

        //SINAV SORUSU: driver.get ile navigate.to arasoındaki kücük fark nedir ?
}








    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
