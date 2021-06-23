package first.man.config;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import first.man.model.Shipmentmodel;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class Shipmentpdfview extends AbstractPdfView {

    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        PdfPTable pdfPTable = new PdfPTable(5);
        Font font = new Font();
        font.setSize(12);


        pdfPTable.addCell("Shipment -Mode");
        pdfPTable.addCell("Shipment -Code");
        pdfPTable.addCell("Shipment -Grade");
        pdfPTable.addCell("Shipment -Enabled");
        pdfPTable.addCell("Shipment -Description");


        List<Shipmentmodel> shipmentmodel = (List<Shipmentmodel>) model.get("getall");

        for (Shipmentmodel sh : shipmentmodel) {

            pdfPTable.addCell(sh.getShipmentmode());
            pdfPTable.addCell(sh.getShipmentcode());
            pdfPTable.addCell(sh.getShipmentgrade());
            pdfPTable.addCell(sh.getShipmentenabled());
            pdfPTable.addCell(sh.getDescription());


        }
        document.add(pdfPTable);


    }
}
