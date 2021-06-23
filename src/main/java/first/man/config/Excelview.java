package first.man.config;

import first.man.model.User;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

public class Excelview extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
									  HttpServletResponse response) throws Exception {

		Sheet sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth(30);
		Row row = sheet.createRow(0);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		row.createCell(0).setCellValue("user id");
		row.getCell(0).setCellStyle(style);
		row.createCell(1).setCellValue("user type");
		row.createCell(2).setCellValue("user code");
		row.createCell(3).setCellValue("user for");
		row.createCell(4).setCellValue("user email");
		row.createCell(5).setCellValue("user contact");
		row.createCell(6).setCellValue("user id Type");
		row.createCell(7).setCellValue("user if other");
		row.createCell(8).setCellValue("user Id Number");

		if (model.get("list") != null) { // create data in the respective rows ie coloums
			List<User> user = (List<User>) model.get("list");
			int rowcount = 1;
			for (User use : user) {
				Row row2 = sheet.createRow(rowcount++);
				row2.createCell(0).setCellValue(use.getIdnumber());
				row2.createCell(1).setCellValue(use.getUseridtype());
				row2.createCell(2).setCellValue(use.getUsercode());
				row2.createCell(3).setCellValue(use.getUserfor());
				row2.createCell(4).setCellValue(use.getUseremail());
				row2.createCell(5).setCellValue(use.getUsercontact());
				row2.createCell(6).setCellValue(use.getIfother());
				row2.createCell(7).setCellValue(use.getIdnumber());
				row2.createCell(8).setCellValue(use.getUseridtype());
			}
		} else {
			// single user view
			User use = (User) model.get("user");
			Row row2 = sheet.createRow(1);
			row2.createCell(0).setCellValue(use.getIdnumber());
			row2.createCell(1).setCellValue(use.getUseridtype());
			row2.createCell(2).setCellValue(use.getUsercode());
			row2.createCell(3).setCellValue(use.getUserfor());
			row2.createCell(4).setCellValue(use.getUseremail());
			row2.createCell(5).setCellValue(use.getUsercontact());
			row2.createCell(6).setCellValue(use.getIfother());
			row2.createCell(7).setCellValue(use.getIdnumber());
			row2.createCell(8).setCellValue(use.getUseridtype());

		}

	}

}
