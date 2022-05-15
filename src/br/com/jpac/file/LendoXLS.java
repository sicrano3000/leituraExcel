package br.com.jpac.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class LendoXLS {

	public static void main(String[] args) {

		/***********************************************/
		/**For XLSX file: use XSSFWorkbook & XSSFSheet**/
		/**For XLS file:  use HSSFWorkbook & HSSFSheet**/
		/***********************************************/
		
		FileInputStream flsPlanilha = null;
		File file = null;
		
		try {
			
			file = new File("C:\\Projetos\\teste.xls");
			flsPlanilha = new FileInputStream(file);
			
			//cria um workBook que lê todas as abas da planilha
			HSSFWorkbook workbook = new HSSFWorkbook(flsPlanilha);
			
			//seta a posição da primeira aba da planilha
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			//retorna todas as linhas da planilha aba 0
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext()) {
				//recebe a linha da vez
				Row row = rowIterator.next();
				
				//retorna todas as celulas da linhas row da vez
				Iterator<Cell> cellIterator = row.iterator();
				
				while(cellIterator.hasNext()) {
					//recebe a celula da vez
					Cell cell = cellIterator.next();
					
					switch(cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							System.out.println("Tipo String: " + cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							System.out.println("Tipo Numérico: " + cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							System.out.println("Tipo Fórmula: " + cell.getCellFormula());
							break;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			Logger.getLogger(LendoXLS.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(LendoXLS.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				flsPlanilha.close();
			} catch (IOException e) {
				Logger.getLogger(LendoXLS.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

}
