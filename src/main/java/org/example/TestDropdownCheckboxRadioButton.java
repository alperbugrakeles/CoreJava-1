package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestDropdownCheckboxRadioButton {

    WebDriver driver;

    @BeforeClass
    public void setup(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }


    @Test
    public void test1(){
    driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    // select tag dropdowns are usually static
    // dropdown with select tag
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown); // I createed dropdown object for this class
        //Select, Selenium WebDriver'da yerleşik bir sınıftır. Bu sınıf, HTML'de <select> etiketi ile tanımlanmış olan açılır menülerle etkileşim kurmak için kullanılır. Select sınıfı, açılır menüden seçenekleri seçmek, mevcut seçenekleri almak gibi işlevleri gerçekleştirmeyi sağlar.
        dropdown.selectByIndex(3); // 3.indexi seciyoruz peki sectiğimi nasıl dogrularım
        String txt = dropdown.getFirstSelectedOption().getText(); // web element dondurdugu içn get text ile texti alıtorum
        System.out.println(txt);
    }

    @Test
    public void test2(){
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        // eger objeleri text ine göre secmek istersem
        dropdown.selectByVisibleText("AED");
        String txt1 = dropdown.getFirstSelectedOption().getText();
        System.out.println(txt1);
        // Attribute degerine göre de secebiliriz
        dropdown.selectByValue("INR");
        String txt2 =  dropdown.getFirstSelectedOption().getText();
        System.out.println(txt2);
        Assert.assertEquals("INR", txt2);
    }

    @Test
    public void test3() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(3000);
//        driver.findElement(By.id("hrefIncAdt")).click();
//        driver.findElement(By.id("hrefIncAdt")).click();
//        driver.findElement(By.id("hrefIncAdt")).click();
//        driver.findElement(By.id("hrefIncAdt")).click();
        // Arkadaslar yukarıdaki satırları 4 defa yaazmak iyi bir pratik degildir bunun yeni while döngüsü ile tnıamlayabilriz yada for

        int i=1;
        while(i<5){
            driver.findElement(By.id("hrefIncAdt")).click(); // 4 times
            i++;
        }
        Thread.sleep(3000);
        driver.findElement(By.id("btnclosepaxoption")).click();
        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

    }

    @Test
    public void dynamicDropDownTest() throws InterruptedException {
        // 2 2tane dropdown list var 2. si 1.sinde secilen veriye göre yükleniyor
        // From listden BLR secyin sonra to dan MAA sectin 2 tane MAA geliyor
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//a[@value='MAA']")).click(); // Bu sekilde tanımlı 2 element olduug için hata alacak

        driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click(); // bu bizim aldığımız xpath
        // asagıdakide toolun aldıgı xpath
        driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[4]/div[2]/div[1]/div[5]/div[2]/div[2]/div[2]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[3]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();



    }




    @AfterClass
    public void tearDown(){
        driver.quit();
    }



}
