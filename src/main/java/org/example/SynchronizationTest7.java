package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SynchronizationTest7 {

    static WebDriver driver;

//    @BeforeClass
//    public void setup(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    }

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        Thread.sleep(3000);
        String[] names ={"Cucumber", "Brocolli","Beetroot","Mushroom"};
        addItems(driver,names);
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");



        driver.quit();


    }






    /* Synchronization in Selenium
    Diyelimki otel rez sitesi test ediyoruz tarihleri ve yeri girdik search butona bastık
    3 -5 saniye yükkleme süresi var

    Bu derste implicit Wait, Explicit Wait, Thread.sleep ve Fluent Wait konularını görecegiz
    implicit Wait-> Hata fırlatmadan önce X saniye bekle diyoruz. Mesela bir elemente tıkladık, hata fırlatmadan önce 3 saniye bekle diyoruz.Global olarak tanımlıyoruz
    Avantajları: Global olarak tanımlıyorsun. Diyelim searche tıkladın ve yüklenmesi 2 saniye sürdü. Sende implicit waiti 5 olarak tanımladın. 2 saniye bekler ve işlemine devam eder. 5 saniye beklemez

    Explicit Wait: Diyelimki bazı sayfaların gelmesi 10 saniye sürüyor. Bizde 5 saniye olan implicit waiti 15 e cektik. testler basarılı ama kod tarafında süre hataları var ve yakalayamıyoruz.
     Eger bazı durumlarda farklı bekleme süreleri olması gerekiyorsa örnegin bir senaryoda 20 sn beklemesi lazımç Ozaman bunu kullanabilriz.

     İkisi birlikte kullanılması en iyi cözüm olacaktır.

     Thread.Sleep starik olarak fonk verilen süre boyunca bekler(Java fonk dur) Cok tercih edilmez


Fluent Wait, Selenium WebDriver'da elementlerin bulunmasını beklemek için kullanılan bir diğer bekleme stratejisidir.
 Fluent Wait, explicit wait'e benzer şekilde çalışır, ancak bekleme süresi boyunca belirli aralıklarla elementin varlığını kontrol eder
 ve elementin mevcut olup olmadığını veya belirli bir durumu sağladığını kontrol etmek için daha esnek bir yapı sunar.

     */

    public static void  addItems(WebDriver driver, String[] names) throws InterruptedException {


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



//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//    }

}
