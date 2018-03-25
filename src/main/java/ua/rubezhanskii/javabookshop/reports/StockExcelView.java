package ua.rubezhanskii.javabookshop.reports;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import ua.rubezhanskii.javabookshop.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("excelView")
public class StockExcelView extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,Workbook workbook,HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"Books.xls\"");

            @SuppressWarnings("unchecked")
            List<Book> books = (List<Book>) model.get("listBooks");
                 Sheet sheet = workbook.createSheet("JavaBooks Stock");
                    Row header = sheet.createRow(0);
                        List<String>headers= Arrays.asList("Book Id","ISBN","Title","Author","Category","Publisher","Language","InventoryStock","Sales");

        for(int i=0;i<=8;i++) header.createCell(i).setCellValue(headers.get(i));
        int rowCount = 1;
        for (Book book : books){
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(book.getBookId());
            row.createCell(1).setCellValue(book.getISBN());
            row.createCell(2).setCellValue(book.getBookTitle());
            row.createCell(3).setCellValue(book.getAuthor().getAuthor1());
            row.createCell(4).setCellValue(book.getCategory().getCategory());
            row.createCell(5).setCellValue(book.getPublisher());
            row.createCell(6).setCellValue(book.getLanguage());
            row.createCell(7).setCellValue(book.getInventoryStock());
            row.createCell(8).setCellValue(book.getBookQuantity());

        }

    }
}
