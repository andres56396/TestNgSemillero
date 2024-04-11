package utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


import Base.ClaseBase;

public class GenerarReportePdf {
	
	private WebDriver driver;
	static String nombre="prueba";
	static String fecha;
	static Document documento;
	static FileOutputStream archivo;
	static Paragraph titulo;
	
	String rutaImagen;
	String hora;
	String horaIni,horaFin;
	ClaseBase claseBase;
	
	
	public void setRutaImagen(String rutaImagen) 
	{
		this.rutaImagen = rutaImagen;
	}
	
	public void crearlantilla(String nomTest,File rutaCarpeta) throws DocumentException, MalformedURLException, java.io.IOException 
	{
		
		//Instanciar documento
		documento = new Document();		
		claseBase = new ClaseBase(driver);
		
		//Tomar la hora del sistema
		hora = claseBase.fechaHora();
		horaIni = claseBase.HoraSistema2();
		
		try {
			
		 //Crear ruta y nombre del pdf
		archivo = new FileOutputStream(rutaCarpeta+"\\"+"Reporte"+nomTest+"-"+hora+".pdf");
		PdfWriter.getInstance(documento, archivo);
		
		//Crear Encabezado
		//Ubicacion de la imagen
		Image header = Image.getInstance(rutaImagen);
		
		//Tamaño de la imagen
		header.scaleToFit(250,1000);
		
		header.setWidthPercentage(100);
		
		//Crear titulo del pdf
		titulo = new Paragraph(nomTest + "\n\n" + "Fecha Inicio:   "+ horaIni);
		titulo.setAlignment(1);
		
		// Crear Tabla de Encabezado
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		
		PdfPCell pos1 = new PdfPCell(header);
		pos1.setHorizontalAlignment(1);
		pos1.setVerticalAlignment(2);
		
		
		PdfPCell pos2 = new PdfPCell(titulo);
		pos1.setHorizontalAlignment(1);
		pos1.setVerticalAlignment(2);
		
		
		table.addCell(pos1);
		table.addCell(pos2);
		
		
		//Generar margen
		documento.setMargins(30,30, 30, 30);
		//Abrir Documento
		documento.open();
		
		
		//insertar la imagen
		documento.add(table);
		
		documento.add(Chunk.NEWLINE);
			
			
		}
		catch(FileNotFoundException e) {
			
			System.err.println(e.getMessage());
		}
       catch(DocumentException e) {
			
			System.err.println(e.getMessage());
		}	   
          catch(IOException e) {
			
			System.err.println("Error al Logo: "+ e.getMessage());
		}	
		
		
		
	}
	
	
	public void crearbody(By locator,String rutaImagen) throws DocumentException, MalformedURLException, java.io.IOException 
	{
		//Obtner el nombre del localizador 
		String locator1 = locator.toString();
		
		//Dar Formato a la fuente
		Paragraph parrafo= new Paragraph();		
		parrafo.setAlignment(Chunk.ALIGN_LEFT);		
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));		
		parrafo.add("Se realiza accion sobre el elemento"+ locator1);
		
		//Adicionar mensaje al pdf
		documento.add(parrafo);
		
		//Insertar imagen
		//Ubicacion de la imagen
		Image imagen = Image.getInstance(rutaImagen);
		imagen.setBorderColor(BaseColor.BLACK);
		
		//Tamaño de la imagen
		imagen.scaleToFit(550,1500);
		
		imagen.setAlignment(Chunk.ALIGN_CENTER);
		documento.add(imagen);	
		
		
		
		
		
	}
	
	
	public void crearbodyError(By locator,String rutaImagen,String msnError) throws DocumentException, MalformedURLException, java.io.IOException 
	{
		//Obtner el nombre del localizador 
		String locator1 = locator.toString();
		
		//Dar Formato a la fuente
		Paragraph parrafo= new Paragraph();		
		parrafo.setAlignment(Chunk.ALIGN_LEFT);		
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));		
		parrafo.add("Se realiza accion sobre el elemento"+ locator1);
		
		//Adicionar mensaje al pdf
		documento.add(parrafo);
		
		//Insertar imagen
		//Ubicacion de la imagen
		Image imagen = Image.getInstance(rutaImagen);
		//Tamaño de la imagen
		imagen.scaleToFit(700,1000);
		imagen.setAlignment(Chunk.ALIGN_LEFT);	
		documento.add(imagen);	
		
		//mENSAHE DE eRROR
		Paragraph parrafoError= new Paragraph();		
		parrafo.setAlignment(Chunk.ALIGN_LEFT);		
		parrafo.setFont(FontFactory.getFont("Arial",8,Font.NORMAL,BaseColor.BLACK));		
		parrafo.add("EL MENSAJE DE ERROR: "+"\n"+ msnError);
		
		documento.add(parrafoError);
		
		
  }
	
	public void cerrarPlantilla() throws DocumentException 
	{
		
		documento.add(Chunk.NEWLINE);
		
		//Dar fromato a la fuente
		Paragraph parrafo= new Paragraph();		
		parrafo.setAlignment(Chunk.ALIGN_RIGHT);		
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.BOLD));		
		parrafo.add("Fecha Inicio:  "+"\n" );
		
		//Adicionar mensaje al pdf
		horaFin = claseBase.HoraSistema2();
		parrafo.add("fecha fin: "+ horaFin);
		
		//Adicionar mensaje al pdf
		documento.add(parrafo);
		documento.close();
		
		
		
	}
	
	
	
	
	

}
