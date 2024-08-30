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

public class e2eTest5 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void e2eTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/#");
        System.out.println(driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).isSelected());

         // from ve to datalrını secelim
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='DEL']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
        //return date in disabled oldgunu kontrol edelim
        if(driver.findElement(By.id("Div1")).getAttribute("style").contains("0.5")){
            Assert.assertTrue(true);
            System.out.println("True");
        }else
        {
            Assert.assertTrue(false);
        }

        // yolcu ekleme
        driver.findElement(By.id("divpaxinfo")).click();
        int i=1;
        while(i<5){
            driver.findElement(By.id("hrefIncAdt")).click(); // 4 times
            i++;
        }
        Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(),"5 Adult");
        Thread.sleep(3000);
        driver.findElement(By.id("btnclosepaxoption")).click();
        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

        //senior citizen checkbox tıkla
        driver.findElement(By.id("ctl00_mainContent_SeniorCitizenDiv")).click();
        // Search tıkla
        driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
        Thread.sleep(2000);
    }

    @Test
    public void windowAlerts() throws InterruptedException {

        //window mesajları html based olmadıgı için inspect edip elementleri almak imkansız
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        String text = "Alper";
        driver.findElement(By.id("name")).sendKeys(text);
        driver.findElement(By.id("alertbtn")).click();
        // diyelimki hata mesajındaki texti yakalamak istiyoruz
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept(); // ok butonuna tıkla
        // yukarıdaki metod ile driver a diyoruz ki sayfada alert ara ve kabul et.
        driver.findElement(By.id("name")).sendKeys("Gizem");
        driver.findElement(By.id("confirmbtn")).click();
        Thread.sleep(3000);
        System.out.println(driver.switchTo().alert().getText());
        Assert.assertEquals(driver.switchTo().alert().getText(),"Hello Gizem, Are you sure you want to confirm?");
        driver.switchTo().alert().dismiss(); // cancel butonuna tıkla
        Thread.sleep(2000);
    }


//        //İsimlendirme dersi
//        class name büyük harfle baslamalı
//        class Name
//       // değişken isimleri kücük harf ile baslamalı
//        String name;
//        int number; gibi
//










    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
