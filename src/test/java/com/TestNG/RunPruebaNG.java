

package com.TestNG;

import org.testng.annotations.Test;

import Base.ClaseBase;
import PageObjet.PagsObjetAlert;
import PageObjet.PagsObjetForm;
import PageObjet.PagsObjetHome;
import utilidades.ExcelUtil;
import utilidades.GenerarReportePdf;
import utilidades.MyScreenRecorder;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class RunPruebaNG {
	
	
private WebDriver driver;
	
	PagsObjetHome paginas;
	PagsObjetForm paginaForm;
	PagsObjetAlert paginaAlert;
	
	Properties propiedades;
	
	ClaseBase claseBase;
	GenerarReportePdf generarReportePdf;
	
	
	public String path;

	@BeforeClass
	  public void beforeClass() throws IOException {
		  
		  //Instanciar archivo Properties de java util
			 propiedades = new Properties();
			 
			 generarReportePdf = new GenerarReportePdf();
			 
			
			
			InputStream entrada= null;
			try {
				entrada = new FileInputStream("./Properties/Properties");
				propiedades.load(entrada);
				
			}catch(FileNotFoundException e){
				
				e.printStackTrace();
				System.out.println(e);
			}
			
			//Instanciar clase PagsObjetHome
			paginas = new PagsObjetHome(driver);
			
			//Cargar propiedades del navagador
			driver = paginas.chromeDriverConnection();
			
			//Instanciar clase PagsObjetForm
			paginaForm = new PagsObjetForm(driver);
			
			//Instanciar clase PagsObjetAlert
			paginaAlert = new PagsObjetAlert(driver);
			
			claseBase = new ClaseBase(driver);
			
			paginas.urlAcceso(propiedades.getProperty("url"));		
			
			path = propiedades.getProperty("filePathExcel");
			
			generarReportePdf.setRutaImagen(propiedades.getProperty("rutaImagenReporte"));
		  
		  
	  }
	
	
  @Test(dataProvider = "excelDataFormt")
  public void TestForm(String EVIDENCIA,String NAME,String LASTNAME, String EMAIL, String GENER,String ADRESS, String SUB,String PHONE) throws Exception {  
	  
	  if (EVIDENCIA.equals("Si")) {	
		//Obtener el nombre del metodo a ejecutar
			 String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
					
			//Crear Carpeta para almacenamieto de imagines
			File rutacarpeta = claseBase.crearCarpeta(propiedades,nomTest);
			
			//Iniciar creacion de resporte pdf		
			generarReportePdf.crearlantilla(nomTest, rutacarpeta);
			
			//Iniciar GRabacion		
			MyScreenRecorder.stratRecording(nomTest,rutacarpeta);
					
			paginas.IngresarPageFormulario(rutacarpeta);
			paginaForm.IngresarPageFormularioPractica(EVIDENCIA,rutacarpeta);
			paginaForm.ingresarFormularioDePractica(rutacarpeta,EVIDENCIA,NAME,LASTNAME,EMAIL,GENER,ADRESS,SUB,PHONE);
			
			
			generarReportePdf.cerrarPlantilla();
			MyScreenRecorder.stopRecording();
	  } else {

		}

  }
  
  
  @Test(dataProvider = "excelAlerts")
  public void TestAlerts(String EVIDENCIA,String NOMBRE) throws Exception {  
	  
	  if (EVIDENCIA.equals("Si")) {	
		//Obtener el nombre del metodo a ejecutar
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			
			//Crear Carpeta para almacenamieto de imagines
			File rutacarpeta = claseBase.crearCarpeta(propiedades,nomTest);
			
	//Iniciar creacion de resporte pdf		
	generarReportePdf.crearlantilla(nomTest, rutacarpeta);
	//Iniciar GRabacion		
	MyScreenRecorder.stratRecording(nomTest,rutacarpeta);

	paginas.IngresarPageAlert(rutacarpeta);	
	paginaAlert.ClickAlerts(rutacarpeta,NOMBRE);
	
	
	generarReportePdf.cerrarPlantilla();
	MyScreenRecorder.stopRecording();
	  } else {

		}

  }
  
 
  
  
  
  
 
  @DataProvider(name = "excelDataFormt")
	public Object[] datos() throws Exception {
		Object[] arreglo = ExcelUtil.getTableArray(path, "Form");
		return arreglo;
	}
  
  @DataProvider(name = "excelAlerts")
 	public Object[] datosAlerts() throws Exception {
 		Object[] arreglo = ExcelUtil.getTableArray(path, "Alert");
 		return arreglo;
 	}


  @AfterClass
  public void afterClass() {
	  driver.close();
		driver.quit();
	  
  }
  
  
  

}
