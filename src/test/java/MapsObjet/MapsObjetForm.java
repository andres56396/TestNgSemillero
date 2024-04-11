package MapsObjet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.ClaseBase;

public class MapsObjetForm extends ClaseBase{

	public MapsObjetForm(WebDriver driver) {
		super(driver);
		
	}
	
	
	
	
	// Selectores para los elementos de un formulario de práctica, incluyendo campos de texto, selecciones, casillas de verificación y botón de envío.
	protected By spanPraticForm = By.xpath("//span[text()='Practice Form']");
	
	protected By txtfirstName = By.id("firstName");
	protected By txtlastName = By.id("lastName");
	protected By inputUserEmail = By.xpath("//input[@id='userEmail']");
	protected By labelGender = By.cssSelector("label[for='gender-radio-1']");
	protected By txtuserNumber = By.xpath("//input[@id='userNumber']");
	
	protected By inputdateBth  = By.xpath("//div[@class='react-datepicker-wrapper']");	
	protected By SelectElemtmonth = By.xpath("//select[@class='react-datepicker__month-select']");
	protected By SelectElemtyear = By.xpath("//select[@class='react-datepicker__year-select']");	
	protected By SelectElemenDay = By.xpath("//div[starts-with(@class,'react-datepicker__day react-datepicker__day--018') and contains(@aria-label,'February ')]");
	protected By inputAddress = By.xpath("//textarea[@id='currentAddress']");
	
	protected By inputSubjects = By.xpath("//input[@id='subjectsInput']");
	
	protected By inputHobbiesSports = By.xpath("//label[text()='Sports']");
	protected By inputHobbiesMusic = By.xpath("//label[text()='Music']");
	
	//protected By state = By.xpath("//div[@id='state']");
	
	protected By btnSubmit = By.xpath("//button[@id='submit']");
	
	protected By btnclosed =By.id("closeLargeModal");
	
	//Values
	protected By valuesname =By.xpath("//table[@class ='table table-dark table-striped table-bordered table-hover']//tbody//tr[1]//td[2]");
	protected By valuesEmail =By.xpath("//table[@class ='table table-dark table-striped table-bordered table-hover']//tbody//tr[2]//td[2]");
	protected By valuesGener =By.xpath("//table[@class ='table table-dark table-striped table-bordered table-hover']//tbody//tr[3]//td[2]");
	protected By valuesPhone =By.xpath("//table[@class ='table table-dark table-striped table-bordered table-hover']//tbody//tr[4]//td[2]");
	protected By valuesSuject =By.xpath("//table[@class ='table table-dark table-striped table-bordered table-hover']//tbody//tr[6]//td[2]");
	
	
	
	
	

}
