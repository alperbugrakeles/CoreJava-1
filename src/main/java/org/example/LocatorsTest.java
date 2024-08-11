package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LocatorsTest {

    WebDriver driver;

        @BeforeClass
        public void setUp() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }

        @Test
        public void test1() throws InterruptedException {
            driver.get("https://rahulshettyacademy.com/locatorspractice/");
           // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           //driver.findElement(By.id("inputUsername"));
            driver.findElement(By.id("inputUsername")).click();
           driver.findElement(By.id("inputUsername")).sendKeys("Alper");
            Thread.sleep(5000);
            driver.findElement(By.id("inputUsername")).clear();
            Thread.sleep(2000);
            driver.findElement(By.id("inputUsername")).sendKeys("Giz");
            driver.findElement(By.name("inputPassword")).sendKeys("hello123");
            //driver.findElement(By.className("submit signInBtn")); // Class name de bosluk var ise bu 2 class var anlamına gelir. Daha uniq olanı kullnabiliriz.
            // yukarıdaki class name i kullanınca alınan hata : org.openqa.selenium.InvalidSelectorException: Compound class names not permitted
            driver.findElement(By.className("signInBtn")).click();
            //Thread.sleep(3000); // beklemeyi kaldırırsak no such element hatası alır , implicitly wait taanımladıktan sonra silebilriz
            // implicity wait ile islemi tamamlamadan önce 5 saniye bekle diyoruz. Implicity wait tanımşadıktan sonra test basarılı geciyoptr
            System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
            driver.findElement(By.linkText("Forgot your password?")).click();
            driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("AlperNBugra");
            driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("AlperBugra@gmail.com");
            driver.findElement(By.xpath("//input[@type='text'][3]")).sendKeys("3483987932");
            Thread.sleep(1000); //statik bekleme olmadan .click metodu hata veriyor.
            driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
            String text = driver.findElement(By.cssSelector("p[class='infoMsg']")).getText();
            System.out.println(text);
            Assert.assertEquals(text,"Please use temporary password 'rahulshettyacademy' to Login.");

            driver.findElement(By.xpath("//button[contains (@class,'go')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
            driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
            driver.findElement(By.cssSelector("#chkboxOne")).click();
            driver.findElement(By.xpath("//button[@class='submit signInBtn']")).click();






            /* Ders
            // <input type="text" placeholder="Username" id="inputUsername" value=""
            //CSS selector -
            // Class name -> tagname.classname -> Button.signInBtn ->
            //id -> tagname#id  -> input#inputUsername , ayrıca basına input koymayada gerek yok #inputUsername ok
            // id ve class name yok ise -> <input type="text" placeholder="Username" value="">
            // Tagname[attribute='value']
            //     input[placeholder='Username'] *> this is css selector

            //xpath
            //  //Tagname[@attribute='value'] -> //input[@placeholder='Username']
            //Örnek calısma: <input type="text" placeholder="Email" ---> //input[@placeholder='Email']

            // liste Xpath içinden element alma
            //input[@type='text'] -> bu kod 4 tane element geri gönderiyor bizim ulasmak istediğimiz 2 sırada olan o yüzden //input[@type='text'][2] yazıp ulasıyoruz

            // xpath ve css selector tanımladıktan sonra consoledan verify edebiliriz.
            //css için $() kullanırken xpath için $x() kullanıyoruz
            //Yukarıda email alanı için yazdıgımız xpathi consolde valide edelim $('//input[@placeholder="Email"]')

            // en son kullanılacak yontem!!! nth clild of index
            // Tagname[@attribute='value']:nth-child(index)

            //Bazen attribute olmayaca ve parent - child ilişkisi kurmak gerecek mesela //form/h2
            // //form -> formun altında ara , //form/input -> 3 tane input tag name li olan element geri gönerecek ama ben 3. sırada olanı istiyorum
            // //from/input[3] -> bana 3. sırada olan elementşi veriyor

            css regular expression örnek
            <input type="password" placeholder="Password" name="inputPassword" value="learning" style="cursor: auto;" xpath="1">
            diyelimki type degerini alacağız ama word degeri dinamik olarak değişiyor password1, password2 gibi
            burada input[type*='pass'] yaparak içinde pass geceni al diyoruz. consoldanda dogruluyoruz $('input[type*="pass"]') ile

            xpath regular expression örnek
            //button[contains(@class,'submit')]  -> diyoruzki classinda submit içereni bul


            */

            /* Ders

            // implicit wait - 5 seconds it will wait while executing
            */







            Thread.sleep(5000);



    }

        @AfterClass
        public void terDown(){
            if (driver != null){
                driver.quit();
            }

        }


}
