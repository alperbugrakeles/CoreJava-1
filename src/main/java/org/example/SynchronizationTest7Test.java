package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SynchronizationTest7Test {

    WebDriver driver;
    WebDriverWait explicitlyWait;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        explicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }


    @Test
    public void addItemsTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        String [] names = {"Cucumber", "Brocolli","Beetroot","Mushroom"};
        addItems(driver,names);
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

//        arguments[0].click() genellikle aşağıdaki durumlarda kullanılır:
//        Gizli veya Görünmeyen Öğelere Tıklama:
//        WebDriver’ın normal click() komutu, bazı durumlarda öğenin görünür olmaması nedeniyle başarısız olabilir.
//        arguments[0].click() ise doğrudan JavaScript ile öğeye tıkladığı için bu sorunu aşabilir.
//        Standart WebDriver Click Komutunun Başarısız Olduğu Durumlar:
//
//        Bazı durumlarda, örneğin karmaşık sayfa düzenlerinde, standart click() komutu öğeye tıklamakta başarısız olabilir.
//        Bu gibi durumlarda JavaScript Executor ile tıklama yapmak daha güvenilir olabilir.
        //driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click(); komutu basarısız oldu

        driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector("button.promoBtn")).click();
        System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText()); //implicitly wait 2 tanımladık diyelişm o zaman hata vericek
        //ımplicitly wait performans sorunlarını gizleyebilir. Diyelimki default tnaımlı 5, gereksinim:sayfanın yüklenmesi max 4 saniye olmalı, test burada success gececek ama aslında fail olmalı
}


    @Test
    public void explicitlyWaitTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        String [] names = {"Cucumber", "Brocolli","Beetroot","Mushroom"};
        addItems(driver,names);
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector("button.promoBtn")).click();

        // diyelimli problem burada ve yüklemesi uzun zaman alıyor o zaman explicitly wait kullanabilirim (Avant:direkt elemente uygulanır, Dez Ava: More code)
        explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo"))); // bu elemen görünür olana kadar 10 saniye bekle diyoruz
        System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
    }






    public void  addItems(WebDriver driver, String[] names) throws InterruptedException {
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        // Arrayi array liste donusturelim
        List<String> itemsList = Arrays.asList(names);
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


