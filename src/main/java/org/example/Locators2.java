package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Locators2 {

    WebDriver driver;

@BeforeClass
    public void setup(){

    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
//    WebDriverManager.safaridriver().setup();
//    driver= new SafariDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
}

@Test
    public void test1() throws InterruptedException {

    String name= "rahul";
    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys(name);
    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("rahulshettyacademy");
    driver.findElement(By.cssSelector("button.signInBtn")).click();
    Thread.sleep(2000); // koyladıgında hata alıyor sayfanın yuklenmesi için 2 saniye bekliyoruz. implicitly wait burada olmuyor cunku saydafa zaten p tagı olgudu için 5 saniye beklemiyoru
    System.out.println(driver.findElement(By.tagName("p")).getText());
    String successMes = driver.findElement(By.tagName("p")).getText();
    Assert.assertEquals(successMes,"You are successfully logged in.");

    // css yazarken parent dan child a ulasmak için ne yapılır*> busşluk bırak tag name yaz
    // div[class='login-container'] h2   -> div[class='login-container'] h2

    System.out.println(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText());
    Assert.assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(),"Hello "+name+",");

    /*
    Eger bir butona buton adındaki text den erişmek istiyor isem bunun yolu xpath ile olur
     Örnek olarak log out butonuna text üxerinden erişmek için -> //button[text()='Log Out'] xpathi yazılır CSS de bu mumlkun degil
     */
    driver.findElement(By.xpath("//button[text()='Log Out']")).click();
    System.out.println("Başarı ile log out olundu");
    //******************************************************************************
    // Ders: Sitede mesaj üzerinden texti alıp baska bir stepde input olarak kullanma

}

@Test
public void Test2() throws InterruptedException {

    String name= "rahul";
    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys(name);
    String Password = getPassword();
    driver.findElement(By.cssSelector(".go-to-login-btn")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(Password);
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("button.signInBtn")).click();

}




public String getPassword() throws InterruptedException {

    driver.get("https://rahulshettyacademy.com/locatorspractice/");
    driver.findElement(By.cssSelector(".forgot-pwd-container")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
    String Passtext = driver.findElement(By.xpath("//form/p")).getText();
    System.out.println(Passtext);
    //Please use temporary password 'rahulshettyacademy' to Login.
    //Buraya kadar texit aldım artık "rahulshettyacademy" bu textden cıkarmam lazım
    //Passtext.split("'"); // Burada diyorum ki bu cümleyi ' karakterine göre bölüyor ve dizi oluşturuyor
    // 0. index -> Please use temporary password
    // 1. indec -> rahulshettyacademy' to Login.
    String[] PassArray = Passtext.split("'");
    System.out.println(PassArray[0]+ " 0 inci ve 1i elemanlar: "+PassArray[1]);
    String[] PassArray2 = PassArray[1].split("'");
    //ine 2 array var. 0. rahulshettyacademy ve 1. to login.
    System.out.println(PassArray2[0]);
    String PassRealText = PassArray2[0];
    // Yada yukarıdaki kodlar yerine , PassArray[1].split("'")[0] da yapılabilir

    return PassRealText;
}

@AfterClass
    public void tearDown(){
    driver.quit();
}


}
