package Base;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.DocumentException;

import utilidades.GenerarReportePdf;

public class ClaseBase {
	
protected static WebDriver driver;
Alert alert;



	//CLASS BUILDER
	public ClaseBase(WebDriver driver) {
		super();
	}
	
	
	//METHOD OF THE BROWSER
	public WebDriver chromeDriverConnection() {
		
		// SET OPTIONS BROWSER
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		// SET BROWSER PROPERTIES
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		// MAXIMIZE BROWSER
		driver.manage().window().maximize();
		
		
		return driver;
	}
	

	//METHOD CLICK
	public void click(By locator, File rutacarpeta, String generarEvidencia) throws InterruptedException{
		try {
			
			captureScreen(rutacarpeta,locator,generarEvidencia);
			driver.findElement(locator).click();
			tiempoEspera(1000);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//METHOD DELETE
	public void delete(By locator, File rutacarpeta, String generarEvidencia) {
		try {
			driver.findElement(locator).clear();
			captureScreen(rutacarpeta,locator,generarEvidencia);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	// METHOD SEND TEXT
	public void sendKey(String inputText, By locator, File rutacarpeta, String generarEvidencia) {
		try {
			driver.findElement(locator).sendKeys(inputText);
			captureScreen(rutacarpeta,locator,generarEvidencia);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//METHOD SUBMIT
	public void submit(By locator, File rutacarpeta, String generarEvidencia) {
		try {
			driver.findElement(locator).submit();
			captureScreen(rutacarpeta,locator,generarEvidencia);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	//METHOD STAND BY TIME
	public void tiempoEspera(long time) throws InterruptedException{
		Thread.sleep(time);
	}
	
	
	// METHOD GET DATE FOR SCREENSHOT
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	public static void scrollWeb(int y, int numMovimiento) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int i = 0; i <= numMovimiento; i++) {
				js.executeScript("window.scrollBy(0," + y + ")");
				//standByTime(i);
				//captureScreen(rutacarpeta);
			}
		} catch (Exception e) {
			throw new InterruptedException();
		}
		
	}
	
	
	public void selectElement(By locator,String value, File rutacarpeta, String generarEvidencia) throws IOException, DocumentException 
	{
		
		// Identifica el elemento de la lista desplegable (por ejemplo, mediante su ID)
        WebElement selectElement = driver.findElement(locator);

        // Crea un objeto Select utilizando el elemento identificado
        Select dropdown = new Select(selectElement);
        
     // Selecciona una opción por su Value 
        dropdown.selectByValue(value);
        captureScreen(rutacarpeta,locator,generarEvidencia);
	}
	
	
	
		
	public void SearchElementPage(By locator, File rutacarpeta, String generarEvidencia) throws IOException, DocumentException 
	{
		
		// Encontrar el elemento deseado en el calendario
        WebElement elementoCalendario = driver.findElement(locator);

        // Desplazarse hasta el elemento deseado
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementoCalendario);
        
		elementoCalendario.click();
		captureScreen(rutacarpeta,locator,generarEvidencia);

	}
	
	
	public void SearchElementPageSenkey(By locator, File rutacarpeta, String generarEvidencia,String Value) throws IOException, DocumentException 
	{
		
		// Encontrar el elemento deseado en el calendario
        WebElement elementoCalendario = driver.findElement(locator);

        // Desplazarse hasta el elemento deseado
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementoCalendario);
        
		elementoCalendario.sendKeys(Value);;
		captureScreen(rutacarpeta,locator,generarEvidencia);

	}
	
	
	
	public static void sendKeyBoard(Keys key, By locator) {
		try {
			driver.findElement(locator).sendKeys(key);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public String fechaHora() 
	{
		//Tomamos la fecha del sistema 
		LocalDateTime fechaSistema = LocalDateTime.now();
		
		//Definir formato de fecha
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		
		//Dar Formato a la fecha del Sistema
		String formatFecha = fecha.format(fechaSistema);	
		
		
		
		return formatFecha;
		
	}
	
	
	
	public String HoraSistema() 
	{
		//Tomamos la fecha del sistema
		LocalTime horaSistema = LocalTime.now();
		
		//Definir Formato fecha
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("HHmmss");
		
		//Dar formato a la fecha del sistema
		String hora = fecha.format(horaSistema);
		
		return hora;
		
	}
	
	
	
	public void captureScreen(File rutaCarpeta, By locator, String generarEvidencia) throws IOException, DocumentException 	
	{
		if(generarEvidencia.equals("Si")) {
			
		String hora = HoraSistema();
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile,new File(rutaCarpeta + "\\"+hora+".png"));
		
		String rutaImagen = new File(rutaCarpeta + "\\"+hora+".png").toString();
		
		//Instanciar la clase Generar PDF
		
		GenerarReportePdf infromePdf = new GenerarReportePdf();
		infromePdf.crearbody(locator, rutaImagen);
		
		
		//Eliminar imagen creada
		eliminarArchivo(rutaImagen);
		
		}
	}
	
	
	public File crearCarpeta(Properties propiedades,String nomTest) 
	{
		String pathScreen = propiedades.getProperty("outputFile");
		
		//Almacenamos la fecha del sistema
		String fecha = fechaHora();
		
		//Creamos el nombre de la Carpeta
		String nomCarpeta = nomTest+"-"+fecha;
		
		//Obtenemos la ruta de alojamiento de salida y el nombre des test a ejecutar
		File directorio = new File(pathScreen+nomCarpeta);
		
		//Creamos la Carpeta
		directorio.mkdir();
		
		return directorio;		
		
	}
	
	
	public static String HoraSistema2() 
	{
		//Tomamos la fecha del sistema
		LocalDateTime fechaSistema = LocalDateTime.now();
		
		//Definir Formato fecha
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		//Dar formato a la fecha del sistema
		String formatFecha = fecha.format(fechaSistema);
		
		return formatFecha;
		
	}
	
	public void eliminarArchivo(String rutaImagen) {
		
		File fichero = new File(rutaImagen);
		fichero.delete();
		
	}
	
	
	



public void handleUnexpectedAlert(String Texto,File rutacarpeta, String generarEvidencia,By locator ) throws InterruptedException, IOException, DocumentException {
    try {    	
    	
    	
    	 // Manejar la alerta inesperada
         alert = driver.switchTo().alert();  
        
      // Si hay un texto proporcionado, ingresarlo en la alerta

        if(Texto !=null) {
        	alert.sendKeys(Texto);
        }
            
     // Aceptar la alerta
        alert.accept(); 
     // Capturar pantalla para evidencia
        captureScreen(rutacarpeta,locator,generarEvidencia);

    } catch (UnhandledAlertException e) {
    	// Manejar la alerta inesperada si no se pudo manejar de la forma esperada
        System.out.println("Se encontró una alerta inesperada: " + e.getMessage());
    }
}



	
	
	
	
	   
	
	
	

}
