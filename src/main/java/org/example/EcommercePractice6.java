package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class EcommercePractice6 {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void additemstoCard() throws InterruptedException {

        String[] names = {"Cucumber", "Brocolli","Beetroot","Mushroom"};

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        //Sayfada product name aradığımızda 30 isim cıkıyor. (h4.product-name) bunu yazarsak sayfada buldugu ilk elementi alacak ama biz cucumber isyitoruz
        List<WebElement> products= driver.findElements(By.cssSelector("h4.product-name")); // web elementleri liste olarak alıyorum
        for (int i=0;i<products.size();i++){
         //   System.out.println(products.get(i).getText());
            String name = products.get(i).getText();
            if (name.contains("Cucumber")){
            driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click(); // get(i) belirmez isek yine ilk buldugu elemente tıklayacak
            // addto chart için baska bir xpath yontemi -> //div[@class='products-wrapper']//div[3]//div[3] ama index belirtmek saglıklı degil tabi cunku degisebilir
            Thread.sleep(3000);
            break;
            // bu kod daki problem product namei statik olarak veriyoruz baska bir product eklemek istersek sıkıntı mesela brokoli o yuzden bir array olusturualım en yukarıda
            }
        }

        Thread.sleep(3000);

        // Array olusturalım en yukarıda ve liste içindeki itemları ekleyelim
        // Arrayi array liste donusturelim
        List itemsList = Arrays.asList(names);

        for (int i=0;i<products.size();i++){
            //   System.out.println(products.get(i).getText());
            String name = products.get(i).getText();
            if (itemsList.contains(name)){
                driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click(); // get(i) belirmez isek yine ilk buldugu elemente tıklayacak
                // addto chart için baska bir xpath yontemi -> //div[@class='products-wrapper']//div[3]//div[3] ama index belirtmek saglıklı degil tabi cunku degisebilir
                // Eger baska ürünleri eklemek istersem artık sadece arrayime eklerim kod yapısında değişiklik yapmama gerek yok.
            }
        }

        Thread.sleep(3000);

    }


    // bir cok veriyi liste üzerinden ekleme  --ONEMLİ SINAV PRATİĞİ
    @Test
    public void  addItemListToCard () throws InterruptedException {

        String[] names ={"Cucumber", "Brocolli","Beetroot","Mushroom"};

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        // Arrayi array liste donusturelim
        List itemsList = Arrays.asList(names);
        for (int i=0;i<products.size();i++){
            //   System.out.println(products.get(i).getText());
            String name = products.get(i).getText().split("-")[0].trim();
            if (itemsList.contains(name)){
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click(); // get(i) belirmez isek yine ilk buldugu elemente tıklayacak
                // addto chart için baska bir xpath yontemi -> //div[@class='products-wrapper']//div[3]//div[3] ama index belirtmek saglıklı degil tabi cunku degisebilir
                // Eger baska ürünleri eklemek istersem artık sadece arrayime eklerim kod yapısında değişiklik yapmama gerek yok.
            }
        }
        driver.findElement(By.cssSelector(".cart-icon")).click();
        Thread.sleep(3000);

    }




    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
