package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Locators4TestDropdownCheckboxRadioButton {

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
        //driver.findElement(By.xpath("//a[@value='MAA']")).click(); // Bu sekilde tanımlı 2 element olduug için hata alacak neden cünkü bütün sayfayı search ediyor ve 2 tane buluyor parent vermediğimiz için

        //driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click(); // bu bizim aldığımız xpath"
        // asagıdakide toolun aldıgı xpath
        //driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[4]/div[2]/div[1]/div[5]/div[2]/div[2]/div[2]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/div[3]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
        // peki, pull requestden index kullanma talebi gelirse parent child ilişkisi ile bu xpathi nasıl yazarım
        // //div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA'] parent i yazıp arada bir bosluk bırakıp sonra diger xpathi yzazarım
        driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
        Thread.sleep(3000);
        // to kısmında MAA nın faklı secimlerini xpathler üzerinden gorduk. Once parentini verip sonra index kullanmadan secmek en dıgrusu bunada parent-child relation xpath diyoruz

    }
    @Test
    public void AutoSuggestiveDropDownTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("autosuggest")).sendKeys("ind");
        Thread.sleep(3000);
        //drop down list de "ind" yazıyoruz ve auto suggestion olrak 3 secenek geliyor biz ortadakina alacagız
        List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a")); // burada suggestion olarak gelen 3 elementi list olarak alıyoruz

        for (WebElement x :options){

            if (x.getText().equalsIgnoreCase("India"))
            {
                x.click();
                break; //india textini bulunca tıkla ve cık
            }
        }
    }

    @Test
    public void checkBoxesTest(){
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        //<input id="ctl00_mainContent_chk_friendsandfamily" type="checkbox" name="ctl00$mainContent$chk_friendsandfamily" xpath="1">
        //Yukarıda id ve name degerleri cok uzun oldugundan regular expression ile yazıyoruz
        // input[id*='SeniorCitizenDiscount'] -> içinde SeniorCitizenDiscount gecen id yi al diyorum. Sayfada 1 tane oldugundan emin olmalıyım
        System.out.println( driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected()); // false donmesini bekliyorum
        Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected()); // herzaman false dönmesini bekliyoruz
        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
        System.out.println( driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected()); // true donmesini bekliyorum
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
        //Sayfadaki checkboxların sayılarını almak istiyorum
        System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
        Assert.assertEquals(6,driver.findElements(By.cssSelector("input[type='checkbox']")).size());
    }

    @Test
    public void calendarTarihSecme() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
    }

    @Test
    public void calendartarihSecme2() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled()); // true donmesini bekliyoruz
        Thread.sleep(2000);
       // driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();
        Thread.sleep(2000);
        System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled()); // false donmesini bekliyoruz ama true dönüyor
        // yukarıdaki gibi durumlarda html kodunda mutlaka degişikillik olmalı bu duurum için "display: block; opacity: 0.5; " opacity degeri değişiyor
        // bizde opacity degerini kontrol edeceğiz
        System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));  // display: block; opacity: 0.5; yazdı
        // yukarudaki opacity üzerinden kodumuzu yazalım
        if(driver.findElement(By.id("Div1")).getAttribute("style").contains("1")){
            System.out.println("Depart Date is Enabled");
            Assert.assertTrue(true); // true degeri gönder ve testi basarıyla gecir
        }else
        {
            Assert.assertTrue(false); // false degeri gönder ve testi fail et.
        }





    }








    @AfterClass
    public void tearDown(){
        driver.quit();
    }



}
