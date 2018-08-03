package com.ivan.newtechnologies.apache.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.util.Iterator;

public class ReadExcelMain {

    public static void main(String[] args) throws Exception {

        final Workbook workbook = WorkbookFactory.create(InputStreams.people());

        final Sheet familySheet = workbook.getSheet("Family");

        final Iterator<Row> rowIterator = familySheet.rowIterator();
        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            print(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
        }

        workbook.close();
    }

    private static void print(final String s1, final String s2) {
        System.out.printf("| %-10s | %-10s |\n", s1, s2);
    }

}
