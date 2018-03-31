package ua.rubezhanskii.javabookshop.reports;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import ua.rubezhanskii.javabookshop.model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("salesExcelView")
public class SalesExcelView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"Sales.xlsx\"");

        @SuppressWarnings("unchecked")
        List<Order> orders = (List<Order>) model.get("orders");
        Sheet sheet = workbook.createSheet("Sales");
        Row header = sheet.createRow(0);
        List<String>headers= Arrays.asList("First Name","Last Name","Email","Title","Quantity","Price","Total");

        for(int i=0;i<=6;i++) header.createCell(i).setCellValue(headers.get(i));
        int rowCount = 1;
        for (Order order : orders){
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(order.getCustomer().getFirstName());
            row.createCell(1).setCellValue(order.getCustomer().getLastName());
            row.createCell(2).setCellValue(order.getCustomer().getLastName());
            row.createCell(3).setCellValue(order.getBook().getBookTitle());
            row.createCell(4).setCellValue(order.getBook().getBookQuantity());
            row.createCell(5).setCellValue(order.getBook().getPrice());
            row.createCell(6).setCellValue(order.getBook().getBookQuantity()*order.getBook().getPrice());


        }

    }
}
