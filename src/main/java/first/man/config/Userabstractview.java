package first.man.config;


import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import first.man.model.User;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class Userabstractview extends AbstractPdfView
{

    protected void buildPdfDocument(Map<String, Object> model, com.lowagie.text.Document document, com.lowagie.text.pdf.PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users =(List<User>) model.get("list");
        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell("First Name");
        table.addCell("Last Name");
        table.addCell("Email");

        for (User user : users) {
            table.addCell(user.getIdnumber());
            table.addCell(user.getUseridtype());
            table.addCell(user.getUseremail());
        }
        document.add(table);

    }
}
