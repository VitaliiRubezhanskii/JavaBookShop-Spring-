package helpers;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Category;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class BookRowHandler extends BeanListHandler {
    public BookRowHandler(Connection conn) {
        super(Book.class,new BasicRowProcessor(
                new BeanProcessor(getColumnsToFieldsMap())
        ));
    }
    private static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("bookId", "bookId");
        columnsToFieldsMap.put("coverImage", "coverImage");
        columnsToFieldsMap.put("price", "coverImage");
        columnsToFieldsMap.put("bookTitle", "coverImage");
        columnsToFieldsMap.put("categoryId", "category");
        columnsToFieldsMap.put("publisher", "publisher");
        columnsToFieldsMap.put("ISBN", "ISBN");
        columnsToFieldsMap.put("lang", "language");
        columnsToFieldsMap.put("details", "details");
        columnsToFieldsMap.put("author", "authorId");
        columnsToFieldsMap.put("bookQuantity", "bookQuantity");
        columnsToFieldsMap.put("inventoryStock", "inventoryStock");
        return columnsToFieldsMap;
    }
}
