package ui.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import UI.UIConsultationPanel;
import modele.beans.Consultation;
import modele.beans.LigneOrdonnance;
import modele.beans.Patient;
import modele.beans.User;
import models.dao.DAOConsultation;
import models.dao.DAOLigneOrdonnance;
import models.dao.DAOUser;

@SuppressWarnings("unused")
public class PDFutils {
	
public static void createPDF(LigneOrdonnance o) throws IOException {
	   
	    long id = Long.parseLong(UIConsultationPanel.model.getValueAt(UIConsultationPanel.table.getSelectedRow(),0).toString()) ;
	    Consultation c = DAOConsultation.getByID(id);
	   	Patient p = UIConsultationPanel.listItems.getSelectedValue();
	   	String ch = "/home/melissa/Bureau/O"+id+p.getNom();
	   	
	   	
//		String chemin ="/home/melissa/Bureau/Ordonnance" ;
		PdfWriter pdfWriter = new PdfWriter(ch);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);
		Paragraph onesp = new Paragraph("\n");
		
		
		
		float twocol=285f;
		float twocol150 = twocol+125f;
		float twocolumWidth[] = {twocol150,twocol};
		float threecolumWidth[] = {150f,160f,150f};
		float fivecolumWidth[] = {150f,160f,150f,150f,150f};
		float fullWidth[]= {twocol150,twocol};
		Table table = new Table(twocolumWidth);
//	    ImageIcon imgLogo= new ImageIcon( IconConstants.class.getResource("/resources/icons/page_prev_icon.png") );
//		ImageIcon imglogo25 = ImageUtils.resizeIconTo( imgLogo,25,25);
		
	
		table.addCell(new Cell().add(new Paragraph("imgLogo")).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(new Paragraph("Cabinet médical").setBold().setFontSize(20f)).setBorder(Border.NO_BORDER));
		
		Border gb = new SolidBorder(2f/2f);
		//look how to set a color to the border =
		Table divider = new Table(fullWidth);
		divider.setBorder(gb);
		//table.setBorderBottom(gb);
		
		
		//L'ajout de la ligne bejaia le :....
        Table twocolTable = new Table(twocolumWidth);
		Table nestedTable = new Table(new float[] {twocol/2,twocol/2});
		
		nestedTable.addCell(getHeaderTextCell("Béjaia le    :"));
		nestedTable.addCell(getHeaderTextValue(c.getDate().toString()));
		
		
		twocolTable.addCell(getHeaderTextCell(""));
		twocolTable.addCell(new Cell().add(nestedTable).setBorder(Border.NO_BORDER));
		
		
		// lajout de la ligne des informations avec trois colonnes
		
		Table threecoltable = new Table(threecolumWidth);
		Table nomtable = new Table(new float[] {2*125f/3,125f/2});
		nomtable.addCell(getHeaderTextCell("Nom   :  "));
		nomtable.addCell(getHeaderTextValue(p.getNom()));		
		
		
		Table prenomtable = new Table(new float[] {2*125f/3,125f/3});
		prenomtable.addCell(getHeaderTextCell("Prénom  :  "));
		prenomtable.addCell(getHeaderTextValue(p.getPrenom()));	
		
		Table agetable = new Table(new float[] {2*125f/3,125f/3});
		agetable.addCell(getHeaderTextCell("Age  :  "));
		agetable.addCell(getHeaderTextValue(p.getAge().toString()));	
		
		
		//faire les couleurs avec device rgb
		
		threecoltable.addCell(new Cell().add(nomtable.setFontColor(Color.convertRgbToCmyk( new DeviceRgb(255,0,0))))
				.setBorder(Border.NO_BORDER));   
		threecoltable.addCell(new Cell().add(prenomtable).setBorder(Border.NO_BORDER));
		threecoltable.addCell(new Cell().add(agetable).setBorder(Border.NO_BORDER));
		
		Cell cpOrdo =new Cell().add(new Paragraph("ORDONNANCE").setFontSize(18f).setBold()).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER);
		//.setBackgroundColor(Color.convertRgbToCmyk( new DeviceRgb(0xCC, 0xCC, 0xCC))
		
		
		List<LigneOrdonnance> ligneordo =DAOLigneOrdonnance.getListByIDconsu(id);
		
	    Table fivecolTable = new Table(fivecolumWidth);
	    
	    Color headerBg = new DeviceRgb(0xA6, 0xCB, 0x0B);
	    
	    
	   

	    
	    fivecolTable.addCell(new Cell().add(new Paragraph("Médicament")).setBorder(Border.NO_BORDER));
	    fivecolTable.addCell(new Cell().add(new Paragraph("Quantité")).setBorder(Border.NO_BORDER));
	    fivecolTable.addCell(new Cell().add(new Paragraph("Nb Prise/Jour")).setBorder(Border.NO_BORDER));
	    fivecolTable.addCell(new Cell().add(new Paragraph("Nb jours")).setBorder(Border.NO_BORDER));
	    fivecolTable.addCell(new Cell().add(new Paragraph("Observation")).setBorder(Border.NO_BORDER));
		
			Iterator<LigneOrdonnance> it = ligneordo.iterator();

			while (it.hasNext()) {
				LigneOrdonnance ligne = (LigneOrdonnance) it.next();
				fivecolTable.addCell(new Cell().add(new Paragraph(ligne.getMedicament()) ).setBorder(Border.NO_BORDER));
			    fivecolTable.addCell(new Cell().add(new Paragraph(ligne.getQuantite().toString())).setBorder(Border.NO_BORDER));
			    fivecolTable.addCell(new Cell().add(new Paragraph (ligne.getNbPriseJour().toString())).setBorder(Border.NO_BORDER));
			    fivecolTable.addCell(new Cell().add(new Paragraph (ligne.getNbJours().toString())).setBorder(Border.NO_BORDER));
			    fivecolTable.addCell(new Cell().add(new Paragraph (ligne.getObservation().toString())).setBorder(Border.NO_BORDER));
				
			}
	    
		
		document.add(table);
		document.add(onesp);
		document.add(divider);
		document.add(onesp);
		document.add(twocolTable);
		document.add(onesp);
		document.add(threecoltable);
		document.add(onesp);
		document.add(onesp);
		document.add(cpOrdo);
		document.add(onesp);
		document.add(onesp);
		document.add(fivecolTable);
		 ManipulatePdf(pdfDocument, document ) ;
		document.close();
		//ouvrir l'emplcement du fichier en qst
		Desktop.getDesktop().open(new File(ch));
		
		
	}

//for labels like Num consu
    static Cell getHeaderTextCell(String textValue) {
    	return new Cell().add(new Paragraph(textValue)).setFontSize(14f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }
//for le nm de consu lui meme
    static Cell getHeaderTextValue(String textValue) {
    	return new Cell().add(new Paragraph(textValue)).setFontSize(14f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    
    static Cell getBillingGandShippingCEll(String textValue) {
    	return new Cell().add(new Paragraph(textValue)).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    
    
//verifier si on veut que la cellule soit en gras 	
    static Cell getCell10Left(String textValue,boolean isBold,float fontsize) {
   
    	Cell cell = new Cell().add(new Paragraph(textValue)).setFontSize(fontsize).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold?cell.setBold():cell;
    }
    
    public void handleEvent(Event event,Document doc) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdf = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdf.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();
       
        
        Paragraph p = new Paragraph()
            .add("Page ").add(String.valueOf(pageNumber)).add(" of");
        
       
        
        
    }

    
    public static void ManipulatePdf(PdfDocument pdfDoc, Document doc) 
    {
    	 User medecin = DAOUser.getMedecin();
    	 Rectangle pageSize = pdfDoc.getPage(1).getPageSize();
    	 PdfCanvas pdfCanvas = new PdfCanvas(
    			 pdfDoc.getPage(1).newContentStreamBefore(),  pdfDoc.getPage(1).getResources(), pdfDoc);
    	        @SuppressWarnings("resource")
				Canvas canvas = new Canvas(pdfCanvas, pageSize);

       
        Border gb = new SolidBorder(2f/2f);
        float onecolumWidth[] = {600f};
        Table onecolTable = new Table(onecolumWidth);
        Table divider = new Table(onecolumWidth);
		divider.setBorder(gb);
        Paragraph dvFotter = new Paragraph("").setBorderTop(gb).setBold();
        Paragraph space = new Paragraph("\n\n");
        Paragraph pAdr = new Paragraph("Adresse : "+medecin.getAdresse()).setFontSize(17f);
        Paragraph pNum = new Paragraph("N° de téléphone : "+medecin.getTel()).setFontSize(16f);

    
        
        
        
	    String footer ="telephone";
		
        
          
            
//            float coordX = ((pageSize.getLeft() + doc.getLeftMargin())
//                    + (pageSize.getRight() - doc.getRightMargin())) / 2;
			
//		   float coordX = ((pageSize.getLeft() + doc.getLeftMargin())
//                    + (pageSize.getRight() - doc.getRightMargin())) / 4;
	    
	    
           float coordX = 0;     
           float center = ((pageSize.getLeft() + doc.getLeftMargin())+ (pageSize.getRight() - doc.getRightMargin()))/2;
           float three = ((pageSize.getLeft() + doc.getLeftMargin())+ (pageSize.getRight() - doc.getRightMargin())) /3;
           float quarter = ((pageSize.getLeft() + doc.getLeftMargin())+ (pageSize.getRight() - doc.getRightMargin())) /4;
           float footerY = doc.getBottomMargin()+100;
          
           
           canvas.showTextAligned(dvFotter, coordX, footerY, TextAlignment.LEFT);
          
           //to add a space we add it in -50 thing
           canvas.showTextAligned(pAdr,quarter+20, footerY-50, TextAlignment.RIGHT);
           canvas.showTextAligned(pNum,center, footerY-50, TextAlignment.LEFT);
           
           pdfCanvas.release();
       
    }


    
  

}
