package first.man.config;


import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import first.man.model.Purchasedtl;
import first.man.model.Purchaseorder;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class Vendorinvoicepdf extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read purchase order from model

        Purchaseorder purchaseorder = (Purchaseorder) model.get("purchaseorder");
//        response.addHeader("Content-Disposition","attachment;filename="+purchaseorder.getOrdercode());
        //FIRST PDFPTABLE

        PdfPTable pdfPTable = new PdfPTable(4);
        List<Purchasedtl> purchasedtls = purchaseorder.getPurchasedtls();
        double finalcost = purchasedtls.stream().mapToDouble(ob -> ob.getQty() * ob.getPart().getBasecost()).sum();

        pdfPTable.addCell("Vendor Code");
        pdfPTable.addCell(purchaseorder.getUser().getUsercode());
        pdfPTable.addCell("Order Code");
        pdfPTable.addCell(purchaseorder.getOrdercode());
        pdfPTable.addCell("Final Cost");
        pdfPTable.addCell(String.valueOf(finalcost));
        pdfPTable.addCell("Shipment type");

        pdfPTable.addCell(purchaseorder.getShipmentmodel().getShipmentcode());
        document.add(new Paragraph("Items Row"));
        document.add(pdfPTable);
        //ADDED TO TABLE
document.add(new Paragraph("Items Row"));
        PdfPTable items = new PdfPTable(4);
        items.addCell("Part Code");
        items.addCell("Base Cost");
        items.addCell("Qty");
        items.addCell("Line Total");
        for (Purchasedtl  dtls:purchasedtls)
        {
            items.addCell(dtls.getPart().getPartcode());
            items.addCell(String.valueOf(dtls.getPart().getBasecost()));
            items.addCell(dtls.getQty().toString());
            items.addCell(String.valueOf(dtls.getPart().getBasecost()*dtls.getQty()));

        }
        document.add(items);


    }
}
