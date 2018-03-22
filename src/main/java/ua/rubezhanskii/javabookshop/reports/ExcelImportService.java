package ua.rubezhanskii.javabookshop.reports;

import org.springframework.stereotype.Component;

@Component
public interface ExcelImportService {

    void importFile(ExcelFileBean fileBean);
}
