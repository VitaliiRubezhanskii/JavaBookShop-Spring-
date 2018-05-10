package helpers;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ua.rubezhanskii.javabookshop.model.Category;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class CategoryRowHandler extends BeanListHandler {


        public CategoryRowHandler(Connection conn) {
            super(Category.class,new BasicRowProcessor(
                    new BeanProcessor(getColumnsToFieldsMap())
            ));
        }
        private static Map<String, String> getColumnsToFieldsMap() {
            Map<String, String> columnsToFieldsMap = new HashMap<>();
            columnsToFieldsMap.put("categoryId", "categoryId");
            columnsToFieldsMap.put("category", "category");
            return columnsToFieldsMap;
        }
    }

