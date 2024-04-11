package PageObjet;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import MapsObjet.MapsObjetAlert;
import MapsObjet.MapsObjetForm;


public class PagsObjetAlert extends MapsObjetAlert {

	public PagsObjetAlert(WebDriver driver) {
		super(driver);
		
	}
	
	
	
	
	public void ClickAlerts(File rutacarpeta,String NOMBRE) throws Exception	
	{	
		
		String videncia="Si";
		
		click(spanAlert,rutacarpeta,videncia);  
		
      
		  // Hacer clic en el botón de alerta

		click(btnalertButton,rutacarpeta,videncia);			
		
		// Manejar la alerta inesperada (si ocurre)
		handleUnexpectedAlert(null,rutacarpeta,videncia,btnalertButton);		
		
		 // Esperar un tiempo
		tiempoEspera(2000);		
		
		
       click(btntimerAlertButton,rutacarpeta,videncia);			
		
       tiempoEspera(5000);
		
		handleUnexpectedAlert(null,rutacarpeta,videncia,btntimerAlertButton);		
		
		tiempoEspera(2000);	
		
		
       click(btnconfirmButton,rutacarpeta,videncia);	
		
      // Manejar la alerta inesperada (pasando un texto obtenido de una hoja de cálculo)
		handleUnexpectedAlert(null,rutacarpeta,videncia,btnconfirmButton);		
		
		tiempoEspera(2000);	

		
		click(btnpromtButton,rutacarpeta,videncia);
		
		handleUnexpectedAlert(NOMBRE,rutacarpeta,videncia,btnpromtButton);
		
		tiempoEspera(2000);	
		
		
		//ASSERT
		
		   WebElement confirm = driver.findElement(spanconfirm);
		   WebElement result = driver.findElement(spanresult);
		     
		    Assert.assertEquals(true, confirm.isDisplayed());	
		    
		    Assert.assertEquals(true, result.isDisplayed());
		     
		
		
		
		
		
	
		
	}
	

}
