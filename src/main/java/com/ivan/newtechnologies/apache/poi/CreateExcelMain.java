package com.ivan.newtechnologies.apache.poi;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateExcelMain {

    public static void main(String[] args) throws IOException {
        final XSSFWorkbook workbook = new XSSFWorkbook();
        final XSSFSheet technologiesSheet = workbook.createSheet("Technologies");

        int rowNum = 0;
        createRow(technologiesSheet, rowNum++, "Name", "Level");
        createRow(technologiesSheet, rowNum++, "Java", "Advanced");
        createRow(technologiesSheet, rowNum++, "Spring", "Advanced");
        createRow(technologiesSheet, rowNum, "JavaScript", "Intermediate");

        technologiesSheet.setColumnWidth(0, 20 * 256);
        technologiesSheet.setColumnWidth(1, 20 * 256);
        // or use auto size
//        technologiesSheet.autoSizeColumn(0);
//        technologiesSheet.autoSizeColumn(1);

        save(workbook);
        workbook.close();
    }

    private static void createRow(final Sheet sheet, final int rowNum, final String s1, final String s2) {
        final Row row = sheet.createRow(rowNum);

        row.createCell(0, CellType.STRING).setCellValue(s1);
        row.createCell(1, CellType.STRING).setCellValue(s2);
    }

    private static void save(final Workbook workbook) {

        final File file = new File("d:\\Learning\\new-technologies\\src\\main\\resources\\poi\\tech.xlsx");

        try (final FileOutputStream fileOutputStream = new FileOutputStream(file);) {

            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
