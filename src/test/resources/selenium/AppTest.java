package selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Unit test for simple App.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest
{
    private WebDriver driver;
    private By titulo, ahorro, sueldo, calculo, diezPorciento, saldo, impuesto, detalle, porcentajeImpuesto;

    public AppTest(){
        titulo = By.xpath("//h1[contains(text(),'2do retiro')]");
        ahorro = By.id("ahorro");
        sueldo = By.id("sueldo");
        calculo = By.id("btnCalcularRetiro");
        diezPorciento = By.id("lbl10");
        detalle = By.id("lblDetalleImpuesto");
        saldo = By.id("lblSaldo");
        impuesto = By.id("lblImpuesto");
        porcentajeImpuesto = By.id("lblPorcentajeImp");
        
    }

    @Before
    public void setUp(){
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:9090/");
        System.out.println("Iniciando Pruebas...");

        ChromeOptions options = new ChromeOptions();  
		options.addArguments("--disable-notifications");	
//      Descomentar para realizar prueba no GUI.
		options.addArguments("--incognito");
		options.addArguments("--start-maximized");
		options.addArguments("--no-sanbox"); options.addArguments("--disable-gpu");
		options.addArguments("window-size=1920x1080"); //SIN UI
	    options.addArguments("--headless"); //SIN UI
        
    }

    @Test
    public void t1_despliegePagina()
    {
        System.out.println("CP1: Despliegue de la pagina...");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(titulo));
        assertEquals("2do retiro 10%", driver.findElement(titulo).getText());
        System.out.println("CP1: Despligue Exitoso...");
    }

    @Test
    public void t2_validarMontoRetiro()
    {
        System.out.println("CP2: Validar función de Calculo del 10%...");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ahorro));
        driver.findElement(ahorro).sendKeys("20000000");
        driver.findElement(sueldo).sendKeys("750000");
        driver.findElement(calculo).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(diezPorciento));
        assertEquals("2000000", driver.findElement(diezPorciento).getText());
        System.out.println("CP2: Calculo Exitoso del 10%...");
    }

    @Test
    public void t3_validarCalculoSinImpuesto()
    {
        System.out.println("CP3: Validar función de Calculo sin impuesto...");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ahorro));
        driver.findElement(ahorro).sendKeys("78500000");
        driver.findElement(sueldo).sendKeys("1400000");
        driver.findElement(calculo).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(detalle));
        assertEquals("libre de impuesto", driver.findElement(detalle).getText());
        System.out.println("CP3: El retiro se encuentra libre de impuesto...");
    }

    @Test
    public void t4_validarCalculoConImpuesto()
    {
        System.out.println("CP4: Validar función de Calculo con impuesto...");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(ahorro));
        driver.findElement(ahorro).sendKeys("78500000");
        driver.findElement(sueldo).sendKeys("1500000");
        driver.findElement(calculo).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(detalle));
        assertEquals("renta anual entre $17.8 y $29.7 millones de pesos anuales", driver.findElement(detalle).getText());
        System.out.println("CP4: El retiro se encuentra sujeto a impuesto...");
    }


    @After
    public void setOff()
    {
        driver.close();
    }
}
