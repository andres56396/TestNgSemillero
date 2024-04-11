package MapsObjet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.ClaseBase;

public class MapsObjetAlert extends ClaseBase {

	public MapsObjetAlert(WebDriver driver) {
		super(driver);
		
	}
	
	// Selectores para distintos elementos de la página 
	protected By spanAlert = By.xpath("//span[text()='Alerts']");	
	
	
	protected By btnalertButton = By.xpath("//button[@id='alertButton']");
	
	protected By btntimerAlertButton = By.xpath("//button[@id='timerAlertButton']");
	
	
	protected By btnconfirmButton = By.xpath("//button[@id='confirmButton']");	

	
	protected By btnpromtButton = By.xpath("//button[@id='promtButton']");
	
	protected By spanconfirm = By.id("confirmResult");
	
	protected By spanresult = By.id("promptResult");
}
