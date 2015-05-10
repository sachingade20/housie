package com.housie.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil
{
    public static List<String> inputList = new ArrayList<String>();
    public static final String EXCEL_WITH_NUMBERS = "input_with_numbers.xlsx";
    public static final String EXCEL_WITHOUT_NUMBERS = "input.xlsx";

    public static void read()
    {
        try
        {
            ;
            // Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(ExcelUtil.class.getClassLoader().getResourceAsStream(EXCEL_WITH_NUMBERS));

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            StringBuffer str = new StringBuffer();
            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int count = 36;

            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    if (null != cell.getStringCellValue() && !cell.getStringCellValue().isEmpty())
                    {
                        inputList.add(cell.getStringCellValue());
                    }
                }
                count++;

            }
            str.append(";");
            System.out.println(str.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
