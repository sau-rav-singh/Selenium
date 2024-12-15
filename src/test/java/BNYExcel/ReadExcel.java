package BNYExcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadExcel {

    private static ThreadLocal<InputStream> fis=new ThreadLocal<>();
    private static ThreadLocal<XSSFSheet> xssfSheet=new ThreadLocal<>();

    private static void setup(String fileName, String sheetName) throws IOException {
        InputStream inputStreamXls = ReadExcel.class.getResourceAsStream("/data/" + fileName + ".xls");
        InputStream inputStreamXlsx = ReadExcel.class.getResourceAsStream("/data/" + fileName + ".xlsx");
        if (inputStreamXls != null) {
            fis.set(inputStreamXls);
        } else {
            if (inputStreamXlsx == null) {
                throw new IOException("ExcelDetails annotation may be missing or excel file/sheet doesn’t exists.");
            }
            fis.set(inputStreamXlsx);
        }

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook((InputStream)fis.get());
        xssfSheet.set(xssfWorkbook.getSheet(sheetName));
        xssfWorkbook.close();
    }

    public static List<HashMap<String, String>> readData(String excelName, String sheetName) {
        System.out.println("Before Read data: " + Thread.currentThread().getId());
        List<HashMap<String, String>> excelData = new ArrayList<>();

        try {
            setup(excelName, sheetName);
            int numRows = ((XSSFSheet)xssfSheet.get()).getLastRowNum();

            for(int i = 1; i <= numRows; ++i) {
                HashMap<String, String> inputValues = getHashMapDataFromRow((Sheet)xssfSheet.get(), i);
                excelData.add(inputValues);
            }
        } catch (IOException var9) {
            IOException e = var9;
        } finally {
            IOUtils.closeQuietly((Closeable)fis.get());
        }

        return excelData;
    }
    private static HashMap<String, String> getHashMapDataFromRow(Sheet sheet, int rowIndex) {
        HashMap<String, String> results = new HashMap<>();
        String[] columnHeaders = getDataFromRow(sheet, 0);
        String[] valuesFromRow = getDataFromRow(sheet, rowIndex);

        for (int i = 0; i < columnHeaders.length; ++i) {
            if (i >= valuesFromRow.length) {
                results.put(columnHeaders[i], "");
            } else {
                results.put(columnHeaders[i], valuesFromRow[i]);
            }
        }

        return results;
    }
    private static String[] getDataFromRow(Sheet sheet, int rowIndex) {
        FormulaEvaluator formulaEvaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        Row row = sheet.getRow(rowIndex);
        short numCells = row.getLastCellNum();
        String[] result = new String[numCells];

        for (int i = 0; i < numCells; i++) {
            result[i] = getValueAsString(row.getCell(i), formulaEvaluator);
        }

        return result;
    }
    private static String getValueAsString(Cell cell, FormulaEvaluator formulaEvaluator) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> formulaEvaluator.evaluate(cell).getStringValue(); // Fixed syntax
            default -> "";
        };
    }

}
