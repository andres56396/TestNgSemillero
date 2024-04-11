package PageObjet;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import MapsObjet.MapsObjetHome;

public class PagsObjetHome extends MapsObjetHome{

	
	String videncia="Si";
	public PagsObjetHome(WebDriver driver) {
		super(driver);
		
	}
	
	public void urlAcceso(String url)
	{
		
		driver.get(url);
		
	}
	
	
	
	
	public void IngresarPageFormulario(File rutacarpeta) throws Exception 
	{	
		
		
		tiempoEspera(2000);	
		
		// Hacer clic en el botón de inicio del formulario, generando evidencia
		click(btnHomeFormulario,rutacarpeta,videncia);
		tiempoEspera(2000);
		
	}
	
	public void IngresarPageAlert(File propiedades) throws Exception 
	{
				
		
		scrollWeb(100,2);
		tiempoEspera(2000);		
		click(btnAlertsFrameWindows,propiedades,videncia);
		tiempoEspera(1000);
		
	}
	


}
