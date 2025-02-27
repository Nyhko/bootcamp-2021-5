package selenium.ebalcaldi.grupo4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;


public class Paquete {
    static final String url = "https://www.viajesfalabella.cl/";
    WebDriver driver;
    @BeforeClass
    public static void init(){
        WebDriverManager.edgedriver().setup();

    }

    @Before
    public void setup(){
        driver = new EdgeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }
    @After
    public void close(){
        System.out.println("After");
        if(driver !=null){
          //  driver.close();
        }
    }

    @Test
    public void prohibirBusqueda() throws InterruptedException {


        //atributos
        WebDriverWait exwait = new WebDriverWait(driver, 10);

        //atributos Localizadores
        By inputOrigen = By.xpath("//body/app-root[1]/app-searchbox-container[1]/div[1]/app-searchbox[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]");
        By inputDestino = By.xpath("//body/app-root[1]/app-searchbox-container[1]/div[1]/app-searchbox[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]");
        By locbtnPaquete = By.xpath("//a[contains(@class,'shifu-3-button-circle PACKAGES paint-circle')]");

        //1. ir a url
        driver.get(url); //cargo la url

        //2. boton pestana principal paquete
        WebElement WeBtnPaquete = driver.findElement(locbtnPaquete);
        WeBtnPaquete.click();

        //acceder elemento y enviamos palabra
        WebElement weInputorigen = driver.findElement(inputOrigen);
        weInputorigen.sendKeys("Santiago");
        weInputorigen.sendKeys(Keys.ENTER);


        //




        //Select s = new Select();
        //driver.findElement(inputOrigen).sendKeys("Santiago");
        // driver.findElement(inputOrigen).sendKeys(Keys.ENTER);
        driver.findElement(inputOrigen).sendKeys("Santiago");
        driver.findElement(inputOrigen).sendKeys(Keys.TAB);
        //exwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Santiago')]")));
        //exwait.until(ExpectedConditions.elementToBeClickable(inputOrigen));
        driver.findElement(inputDestino).sendKeys("Viña del mar");
        driver.findElement(inputDestino).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//body/app-root[1]/app-searchbox-container[1]/div[1]/app-searchbox[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[6]/div[1]/a[1]")).click();

    }
}
