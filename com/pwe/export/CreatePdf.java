package com.pwe.export;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class CreatePdf {
    public static final List<String> names = new LinkedList<>();
    public static final List<String> values = new LinkedList<>();
    public static File file;
    public static String type;
    public static String workType;
    public static String[] title;
    public static String projectTitle;
    private static int anInt = 0;
    private static int getAnInt = 1;
    private static final int pad = 5;
    private static final int pad_left = 7;

    /*  Inner   */
    static class HeaderFooter extends PdfPageEventHelper {
        Phrase[] header = new Phrase[3];

        public void onOpenDocument(PdfWriter writer, Document document) {
            DateTimeFormatter nTZ = DateTimeFormatter.ofPattern("E, d MMMM, yyyy ");
            Font dateFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.ITALIC, BaseColor.BLACK);
            Paragraph date = new Paragraph(ZonedDateTime.now().format(nTZ), dateFont);
            Paragraph paperType = new Paragraph(workType, dateFont);
            Paragraph sponsor = new Paragraph("Powered by Sasakraft", dateFont);
            header[0] = new Phrase(date);
            header[1] = new Phrase(paperType);
            header[2] = new Phrase(sponsor);
        }

        public void onEndPage(PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_RIGHT, header[0],
                    rect.getRight(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_LEFT, header[1],
                    rect.getLeft(), rect.getTop(), 0);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, header[2],
                    (rect.getRight() + rect.getLeft()) / 2,
                    rect.getBottom() - 18, 0);
        }
    }

    /*    End Inner     */
    public static void printPdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        HeaderFooter event = new HeaderFooter();

        writer.setBoxSize("art", new Rectangle(36, 54, 559, 795));
        writer.setPageEvent(event);
        writer.setUserunit(75000);
        writer.setPdfVersion(PdfWriter.VERSION_1_7);

        document.setPageSize(PageSize.A4);
        document.setMargins(50, 50, 70, 45);
        document.open();

        Font font__title = new Font(Font.FontFamily.TIMES_ROMAN, 17, Font.BOLD, BaseColor.BLACK);
        Font font = new Font(Font.FontFamily.HELVETICA, (float) 11.5, Font.NORMAL, BaseColor.WHITE);
        Font font_title = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);

        int space = 15;

        Paragraph title_ = new Paragraph(projectTitle + " " + "Estimate", font__title);
        title_.setSpacingAfter(10);
        document.add(title_);

        Paragraph elements_n = new Paragraph("Name", font);
        Paragraph element_v = new Paragraph("Value", font);

        PdfPCell cell_1 = new PdfPCell(elements_n);
        PdfPCell cell_2 = new PdfPCell(element_v);
        cell_1.setPaddingLeft(pad_left);
        cell_2.setPaddingLeft(pad_left);

        Paragraph paragraph = new Paragraph(title[0], font_title);
        String name = "Number of reams";
        populate(document, cell_1, cell_2, paragraph, space, name);

        if (type.equals("PM") || type.equals("P")) {
            Paragraph paragraph_1 = new Paragraph(title[1], font_title);
            String name_1 = "Total material cost";
            populate(document, cell_1, cell_2, paragraph_1, space, name_1);
        }

        if (type.equals("P")) {
            Paragraph paragraph_2 = new Paragraph(title[2], font_title);
            String name_2 = "Cost of labour";
            populate(document, cell_1, cell_2, paragraph_2, space, name_2);

            if (workType.equals("Book Work")) title[3] = "The unit cost per one finished book";

            Paragraph paragraph_3 = new Paragraph(title[3], font_title);
            String name_3 = "";
            populate(document, cell_1, cell_2, paragraph_3, space, name_3);
        }
        document.close();
        anInt = 0;
        getAnInt = 1;
    }

    private static void populate(Document document, PdfPCell cell_1, PdfPCell cell_2, Paragraph paragraph, float space,
                                 String string) throws DocumentException {
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingAfter(space);
        table.setSpacingBefore(space);

        float[] colWith_1 = {2f, 2f};
        table.setWidths(colWith_1);

        BaseColor color = new BaseColor(84, 121, 165);
        BaseColor colorBackground = new BaseColor(145, 166, 196);
        BaseColor color_h = new BaseColor(208, 217, 230);

        cell_1.setBackgroundColor(color);
        cell_1.setBorderColor(colorBackground);
        cell_2.setBackgroundColor(color);
        cell_2.setBorderColor(colorBackground);

        table.addCell(cell_1);
        table.addCell(cell_2);

        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font font_ = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.BLACK);

        for (int i = anInt; i < names.size(); i++) {
            Paragraph cell_p_n = new Paragraph(names.get(i), font);
            PdfPCell cell_n = new PdfPCell(cell_p_n);

            cell_n.setPaddingTop(pad);
            cell_n.setPaddingBottom(pad);
            cell_n.setPaddingLeft(pad_left);
            cell_n.setBorderColor(colorBackground);

            String value = values.get(i);
            Paragraph cell_p_v = new Paragraph(value, font_);
            PdfPCell cell_v = new PdfPCell(cell_p_v);

            cell_v.setPaddingTop(pad);
            cell_v.setPaddingBottom(pad);
            cell_v.setPaddingLeft(pad_left);
            cell_v.setBorderColor(colorBackground);

            if ((i + 1) / 2 == getAnInt) {
                cell_v.setBackgroundColor(color_h);
                cell_n.setBackgroundColor(color_h);
                ++getAnInt;
            }

            table.addCell(cell_n);
            table.addCell(cell_v);

            if (names.get(i).equals(string)) {
                anInt = i;
                ++anInt;
                break;
            }
        }
        document.add(table);
    }
}
