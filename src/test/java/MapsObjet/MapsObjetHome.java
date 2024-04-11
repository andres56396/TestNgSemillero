package MapsObjet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.ClaseBase;

public class MapsObjetHome extends ClaseBase {

	public MapsObjetHome(WebDriver driver) {
		super(driver);
		
	}
	
	protected By btnHomeFormulario = By.xpath("//h5[text()='Forms']");	
	
	protected By btnAlertsFrameWindows = By.xpath("//h5[text()='Alerts, Frame & Windows']");	
	

}
