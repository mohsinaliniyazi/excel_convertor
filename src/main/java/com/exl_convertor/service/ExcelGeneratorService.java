package com.exl_convertor.service;

import com.exl_convertor.model.ExcelGeneratorEntity;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ExcelGeneratorService {

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public void generateExcelFile(ExcelGeneratorEntity excelGeneratorEntity) throws IOException, CsvException {
        String fileName = excelGeneratorEntity.getFilename();
        String FilePath = "D:/Excel_file/"+fileName+".csv";
        File inputFile = new File(FilePath);

        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
        ArrayList<Integer> arrlist = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(fileName);
        int count = 0;
        for (String[] strArray : csvBody) {
            if (strArray.length > 2) {
                HSSFRow row = sheet.createRow((short) count++);
                for (int j = 0; j < strArray.length; j++) {
                    String columnName1 = excelGeneratorEntity.getColumnName();
                    String columnName2 = excelGeneratorEntity.getColumnName2();
                    //if (strArray[j].contains("VSMU-1 Voltage (V)") || strArray[j].contains("VSMU-2 Current (A)")){
                    if (strArray[j].contains(columnName1) || strArray[j].contains(columnName2)) {
                        arrlist.add(j);
                    }
                }

                int[] arr_cov = arrlist.stream().mapToInt(ii -> ii).toArray();
                if (isNumeric(strArray[0])) {
                    for (int j = 0; j < arr_cov.length; j++) {
                        row.createCell(j).setCellValue(strArray[arr_cov[j]].toString());
                    }
                }
            }
        }
        String path = "D:/Excel_file";
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        FileOutputStream fileOut = new FileOutputStream(path+"/"+excelGeneratorEntity.getFilename()+"_converted.xls");

        workbook.write(fileOut);
        fileOut.close();
        System.out.println(excelGeneratorEntity.getFilename() + " file has been generated successfully.");
        reader.close();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
