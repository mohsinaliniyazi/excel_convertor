package com.exl_convertor;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class L1_Out {

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String[] args) throws IOException, CsvException {

        String FilePath = "D:/Excel_file/L1_OUT.csv";
        File inputFile = new File(FilePath);

        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> csvBody = reader.readAll();
        ArrayList<Integer> arrlist = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("L1_OUT");
        int count = 0;
        for (int i = 0; i < csvBody.size(); i++) {
            String[] strArray = csvBody.get(i);

            if (strArray.length > 2) {
                HSSFRow row = sheet.createRow((short)count++);
                for (int j = 0; j < strArray.length; j++) {
                    if (strArray[j].contains("VSMU-2")){
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
        FileOutputStream fileOut = new FileOutputStream(path+"/L1_OUT_converted.xls");

        workbook.write(fileOut);
        fileOut.close();
        System.out.println("Excel file has been generated successfully.");
        reader.close();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}


