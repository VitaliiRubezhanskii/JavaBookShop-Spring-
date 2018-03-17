package ua.rubezhanskii.javabookshop.reports;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.BookJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView {
    @Autowired
    private BookJdbcTemplate bookJdbcTemplate;

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xlsx-file.xls\"");

        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) model.get("listBooks");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Spring MVC AbstractXlsxView");

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Book Id");
        header.createCell(1).setCellValue("ISBN");
        header.createCell(2).setCellValue("Title");
        header.createCell(3).setCellValue("Author");
        header.createCell(4).setCellValue("Category");
        header.createCell(5).setCellValue("Publisher");
        header.createCell(6).setCellValue("Language");
        header.createCell(7).setCellValue("InventoryStock");
        header.createCell(8).setCellValue("Sales");

        // Create data cells
        int rowCount = 1;
        for (Book book : books){
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(book.getBookId());
            row.createCell(1).setCellValue(book.getISBN());
            row.createCell(2).setCellValue(book.getBookTitle());
            row.createCell(0).setCellValue(book.getAuthor().getAuthor1());
            row.createCell(1).setCellValue(book.getCategory().getCategory());
            row.createCell(2).setCellValue(book.getPublisher());
            row.createCell(0).setCellValue(book.getLanguage());
            row.createCell(1).setCellValue(book.getInventoryStock());
            row.createCell(2).setCellValue(book.getBookQuantity());

        }

    }
}
