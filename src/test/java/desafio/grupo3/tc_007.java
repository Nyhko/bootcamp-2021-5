package desafio.grupo3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class tc_007 {


    WebDriver driver;

    @BeforeClass
    public static void init(){
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup () {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies(); // borrar cookies
        driver.manage().window().maximize();
    }

    @After
    public void close () {
        if (driver != null){
            driver.close();
        }
    }

    @Test
    public void test() {
        WebDriverWait exwait = new WebDriverWait(driver, 15);
        By origenPopUp = By.xpath("//div[@class='ac-container']");
        By destinoPopUp = By.xpath("//div[@class='ac-container']");
        driver.get("https://www.viajesfalabella.cl/");
        //click paquetes
        driver.findElement(By.xpath("//i[@class='shifu-icon-product shifu-3-icon-packages']")).click();

        //llenar campo "origen"
        String origen = "Buenos Aires";
        WebElement fieldDesde = driver.findElement(By.xpath("(//input[contains(@class,'input-tag sbox-main-focus')])[1]"));
        fieldDesde.click();
        fieldDesde.sendKeys(origen);
        exwait.until(ExpectedConditions.elementToBeClickable(origenPopUp));
        fieldDesde.sendKeys(Keys.ENTER);

        //llenar campo "destino"
        String destino = "Mendoza";
        WebElement fieldHasta = driver.findElement(By.xpath("(//input[contains(@class,'input-tag sbox-main-focus')])[2]"));
        fieldHasta.click();
        fieldHasta.sendKeys(destino);
        exwait.until(ExpectedConditions.elementToBeClickable(destinoPopUp));
        fieldHasta.sendKeys(Keys.ENTER);

        //elegir fecha ida
        By popUpFecha = By.xpath("(//div[@class='_dpmg2--controlsWrapper'])[3]");
        driver.findElement(By.xpath("//div[@class='input-container sbox-checkin-input-container']//input[@type='text']")).click();
        exwait.until(ExpectedConditions.elementToBeClickable(popUpFecha));
        WebElement fechaIda = driver.findElement(By.xpath("//div[@class='_dpmg2--wrapper _dpmg2--roundtrip _dpmg2--show-info _dpmg2--show']//div[@class='_dpmg2--months']//div[@class='_dpmg2--month _dpmg2--o-3 _dpmg2--month-active']//div[@class='_dpmg2--dates']/span[10]"));
        fechaIda.click();

        //elegir fecha vuelta
        driver.findElement(By.xpath("//body[@class='show-phone']/div[@class='datepicker-packages sbox-v4-components']/div[@class='_dpmg2--wrapper _dpmg2--roundtrip _dpmg2--show-info _dpmg2--show _dpmg2--transition-displacement']/div[@class='_dpmg2--months']/div[@class='_dpmg2--month _dpmg2--o-3 _dpmg2--has-start-range _dpmg2--month-active']/div[@class='_dpmg2--dates']/span[24]")).click();

        //click boton "aplicar"
        driver.findElement(By.xpath("(//em[@class='_dpmg2--desktopFooter-button-apply-text btn-text'])[5]")).click();

        String noches = driver.findElement(By.xpath("//label[@class='sbox-dates-label -label-days sbox-input-label sbox-3-label-form sbox-dates-range']")).getText();

        //click boton "buscar"
        driver.findElement(By.xpath("(//a[contains(@class,'sbox-3-btn -primary')])[3]")).click();

        By hotel = By.xpath("(//div[@class='cluster-description cluster-description-top'])[1]");
        exwait.until(ExpectedConditions.elementToBeClickable(hotel));
        //click boton "siguiente" en la lista de habitaciones
        driver.findElement(By.xpath("//div[@class='-eva-3-mb-xlg']//aloha-cluster-container//div[@class='eva-3-cluster-gallery -eva-3-bc-white -eva-3-shadow-line-hover']//div[@class='cluster-container -eva-3-shadow-line']//div[@class='cluster-pricebox-container']//aloha-cluster-pricebox-container//div[@class='eva-3-pricebox-cluster -responsive -pkg']//div[@class='pricebox-top-container']//div[@class='pricebox-action']//aloha-button//button[@class='eva-3-btn -md -primary -eva-3-fwidth']")).click();

        exwait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        // click siguiente para aceptar la habitacion
        By botonSiguienteHab = By.xpath("//div[@class='pricebox-action -eva-3-mt-lg pricebox-button']//aloha-next-step-button//aloha-button//button[@class='eva-3-btn -md -primary -eva-3-fwidth']");
        exwait.until(ExpectedConditions.elementToBeClickable(botonSiguienteHab));
        driver.findElement(botonSiguienteHab).click();

        // click seleccionar vuelo
        By botonSiguienteVuelo = By.xpath("//a[@class='-md eva-3-btn -primary']//div");
        exwait.until(ExpectedConditions.elementToBeClickable(botonSiguienteVuelo));
        driver.findElement(botonSiguienteVuelo).click();

        // click siguiente confirmar precio final
        By botonSiguienteFinal = By.xpath("//button[@class='eva-3-btn -lg pricebox-sticky-button -secondary']");
        exwait.until(ExpectedConditions.elementToBeClickable(botonSiguienteFinal));
        driver.findElement(botonSiguienteFinal).click();

        // comparar
        By detallePago = By.xpath("//ul[@class='detail-price']");
        exwait.until(ExpectedConditions.elementToBeClickable(detallePago));
        WebElement rutaDetalle = driver.findElement(By.xpath("//div[contains(text(),'Buenos Aires - Mendoza')]"));
        WebElement nochesDetalle = driver.findElement(By.xpath("/html/body/af-app/div/div/div/checkout-form/div/form-component/form/div[2]/div/purchase-detail-component/div/products-detail-component-v2/div[2]/div/product-description-v2/div/div/span"));

        if(rutaDetalle.getText().contains(origen) || rutaDetalle.getText().contains(destino) || nochesDetalle.getText().contains(noches) ){
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }

    }

}
