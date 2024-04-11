package PageObjet;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import MapsObjet.MapsObjetForm;
import org.junit.Assert;


public class PagsObjetForm extends MapsObjetForm{

	
	
	public PagsObjetForm(WebDriver driver) {
		super(driver);
		 
		
	}
	
	public void urlAcceso(String url)
	{
		
		driver.get(url);
		
	}
	
	public String returnPhone(String PHONE) {
		// Convertir la cadena a un objeto BigDecimal
        BigDecimal bigDecimal = new BigDecimal(PHONE);

        // Convertir el objeto BigDecimal a un objeto BigInteger
        BigInteger bigInteger = bigDecimal.toBigInteger();

        // Convertir el objeto BigInteger a una cadena
        String phone = bigInteger.toString();

        // Imprimir el resultado
        System.out.println("Resultado: " + phone);
		
		return phone;
		
		
	}
	public void selectGender(int genderNumber) {
        // Construye el selector CSS con el número de género proporcionado
        String cssSelector = String.format("label[for='gender-radio-%d']", genderNumber);
        // Encuentra el elemento usando el selector CSS
        WebElement genderElement = driver.findElement(By.cssSelector(cssSelector));
        // Haz clic en el elemento
        genderElement.click();
    }
	
	public void selectHobbie(String hobbieText) {
        // Construye el XPath con el texto del género proporcionado
        String xpath = String.format("//label[text()='%s']", hobbieText);
        // Encuentra el elemento usando el XPath
        WebElement genderElement = driver.findElement(By.xpath(xpath));
        // Haz clic en el elemento
        genderElement.click();
    }
	
	public void IngresarPageFormularioPractica(String EVIDENCIA,File rutacarpeta) throws Exception 
	{	
		
		// Click en el elemento del formulario
				click(spanPraticForm,rutacarpeta,EVIDENCIA);	
				
				// Desplazarse hacia abajo en la página
				scrollWeb(100,2);
				tiempoEspera(1000);	
		
	}
	
	
	
	
	public void ingresarFormularioDePractica(File rutacarpeta,String EVIDENCIA,String NAME,String LASTNAME, String EMAIL, String GENER,String ADRESS, String SUB, String PHONE) throws Exception 
	{	
		
		
		// Ingresar el nombre
		sendKey(NAME,txtfirstName ,rutacarpeta,EVIDENCIA);
		
		// Ingresar el apellido
		sendKey(LASTNAME,txtlastName ,rutacarpeta,EVIDENCIA );
		
		// Ingresar el correo electrónico
		sendKey(EMAIL,inputUserEmail,rutacarpeta,EVIDENCIA);		
		
		String genero= GENER;
		
		// Seleccionar género
		if(genero.equals("Male")) {
			
			selectGender(1);		
			
		}else {
			if(genero.equals("Female")) {
				selectGender(2);
			}
		}
		
		
		
		// Ingresar el número de usuario
		String phone = returnPhone(PHONE);
		sendKey(phone,txtuserNumber,rutacarpeta ,EVIDENCIA);
			
		 // Seleccionar la fecha de nacimiento
		click(inputdateBth,rutacarpeta,EVIDENCIA);		
		selectElement(SelectElemtmonth,"1",rutacarpeta,EVIDENCIA);		
		selectElement(SelectElemtyear,"1997",rutacarpeta,EVIDENCIA);		
		tiempoEspera(2000);			
		click(SelectElemenDay,rutacarpeta,EVIDENCIA);	
		
		tiempoEspera(1000);		
		
				
		
		
		
		sendKey(SUB,inputSubjects,rutacarpeta,EVIDENCIA);
		sendKeyBoard(Keys.ENTER, inputSubjects);
		
		
		
		 // Seleccionar pasatiempos
		click(inputHobbiesSports,rutacarpeta,EVIDENCIA);
		click(inputHobbiesMusic,rutacarpeta,EVIDENCIA);
		
		tiempoEspera(1000);	
		SearchElementPageSenkey(inputAddress,rutacarpeta,EVIDENCIA,ADRESS);
		tiempoEspera(1000);	
		
		
		SearchElementPage(btnSubmit,rutacarpeta,EVIDENCIA);
		tiempoEspera(2000);
		
		
		
		// Encuentra el elemento
        WebElement element = driver.findElement(valuesname);
        WebElement elementMail = driver.findElement(valuesEmail);
        WebElement elementGener = driver.findElement(valuesGener);
        WebElement elementPhone = driver.findElement(valuesPhone);
        WebElement elementSuject = driver.findElement(valuesSuject);
     // Obtiene el texto del elemento       
        
        String nombreCOmpleto = NAME + " " + LASTNAME;
        
     
      //ASSERT        
        
		Assert.assertEquals(nombreCOmpleto, element.getText());
		
		Assert.assertEquals(EMAIL, elementMail.getText());
		
		Assert.assertEquals(GENER, elementGener.getText());
		
		Assert.assertEquals(phone, elementPhone.getText());
		
		Assert.assertEquals(SUB, elementSuject.getText());
		
		click(btnclosed,rutacarpeta,EVIDENCIA);
		
	}
	
	
	

}
