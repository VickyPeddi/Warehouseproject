package first.man.config;

import first.man.model.Shipmentmodel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

public class Shipmentexcel extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
    	response.setHeader("Content-Disposition", "attachment; filename=Shipmentfile.xls");
        List<Shipmentmodel> shipment = (List<Shipmentmodel>) model.get("shipment");
        Sheet sheet = workbook.createSheet("Shipment Details");
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        setheader(sheet, cellStyle, font);
        setbody(sheet,font,shipment);
    }

	private void setbody(Sheet sheet, Font font,List<Shipmentmodel> shipment)
	{
		int rowno=1;
		for (Shipmentmodel sh:shipment)
		{
			Row row = sheet.createRow(rowno++);
			row.createCell(0).setCellValue(sh.getShipid());
			row.createCell(1).setCellValue(sh.getShipmentmode());
			row.createCell(2).setCellValue(sh.getShipmentcode());
			row.createCell(3).setCellValue(sh.getShipmentgrade());
			row.createCell(4).setCellValue(sh.getShipmentenabled());
			row.createCell(5).setCellValue(sh.getDescription());



		}
	}


	private void setheader(Sheet sheet, CellStyle style, Font font) {



		font.setFontName("Arial");

		style.setFillBackgroundColor(HSSFColor.DARK_YELLOW.index);
		font.setBold(true);
		font.setColor(HSSFColor.BLUE_GREY.index);
		style.setFont(font);

		Row row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("SHipment ID");
		row1.getCell(0).setCellStyle(style);
		row1.createCell(1).setCellValue("Shipmentmode");
		row1.getCell(1).setCellStyle(style);
		row1.createCell(2).setCellValue("SHipment Code");
		row1.getCell(2).setCellStyle(style);
		row1.createCell(3).setCellValue("Shipment Grade");
		row1.getCell(3).setCellStyle(style);
		row1.createCell(4).setCellValue("Shipment Enabled");
		row1.getCell(4).setCellStyle(style);
		row1.createCell(5).setCellValue("Shipment Description");
		row1.getCell(5).setCellStyle(style);

    }

}
