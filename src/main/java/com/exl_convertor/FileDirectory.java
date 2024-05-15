package com.exl_convertor;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileDirectory {

    static String changeMainFile = "T15";
    static String main_path = "D:/Excel_file/";
    static int fileCount = 4;
    static String generated_output_file = "D:\\Excel_file\\L1_TF_H1_Folder";
    static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String[] args) throws IOException, CsvException {
        File directoryPath;
        directoryPath = new File(main_path+changeMainFile);
        String contents[] = directoryPath.list();
        for (String dateString : contents) {
            if(dateString!=null) {
                directoryPath = new File(main_path+changeMainFile+"/"+dateString);
                String[] dir = directoryPath.list(); //["desktop.ini", "H1", "H2"]
                for (String h1_folder : dir) {
                    if (h1_folder != null && !h1_folder.equals("desktop.ini")){
                        if(h1_folder.contains("L")){
                            generate_excel(main_path+changeMainFile+"/"+dateString+"/", fileNameChange(dateString, null));
                        }
                        generate_excel(main_path+changeMainFile+"/"+dateString+"/"+h1_folder+"/", fileNameChange(dateString, h1_folder));
                    }
                }
            }
        }
    }

    private static String fileNameChange(String dateChange, String h1_dir){
        String newName = null;
        if(dateChange.contains(".")){
            String[] arr= dateChange.split("\\.");
             newName = arr[0] + arr[1];
        }
        if (h1_dir != null){
            newName = h1_dir+"_"+newName;
        }
        return newName;
    }

    private static void generate_excel(String directoryPath, String outputFileName)  throws IOException, CsvException {
        List<String> fileNames = new ArrayList<>();
        for(int k=1; k<=fileCount; k++){
            String FilePath = directoryPath+"L"+k+"/"+"L"+k+"_TF.csv";
            File inputFile = new File(FilePath);

            CSVReader reader = new CSVReader(new FileReader(inputFile));
            List<String[]> csvBody = reader.readAll();
            ArrayList<Integer> arrlist = new ArrayList<>();

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("L"+k+"_TF");

            int count = 2;
            int num = 2;
            HSSFRow row;
            String[] header1 = {"Vgs4", "Ids4",	"Vgs5",	"Ids5",	"Vgs6",	"Ids6", "Vgs1",	"Ids1",	"Vgs2",	"Ids2",	"Vgs3",	"Ids3"};
            String[] header2 = {"Vds= 0v", "Vds = 0v","Vds = 8v","Vds = 8v","Vds = 16v","Vds = 16v","Vds= 24v","Vds = 24v","Vds = 32v","Vds = 32v","Vds = 40v","Vds = 40v"};

            for(int j=0; j<num; j++){
                row = sheet.createRow((short)j);
                if(j == 0){
                    for(int firstRow=0; firstRow<header1.length; firstRow++){
                        row.createCell(firstRow).setCellValue(header1[firstRow]);
                    }
                } else {
                    for(int firstRow=0; firstRow<header2.length; firstRow++){
                        row.createCell(firstRow).setCellValue(header2[firstRow]);
                    }
                }
            }

            for (int i = 0; i < csvBody.size(); i++) {
                String[] strArray = csvBody.get(i);

                if (strArray.length > 2) {
                    row = sheet.createRow((short)count++);
                    for (int j = 0; j < strArray.length; j++) {
                        if (strArray[j].contains("VSMU-1 Voltage (V)") || strArray[j].contains("VSMU-2 Current (A)")){
                            arrlist.add(j);
                        }
                    }

                    int[] arr_cov = arrlist.stream().mapToInt(ii -> ii).toArray();
                    if (isNumeric(strArray[0])) {
                        for (int j = 0; j < arr_cov.length; j++) {
                            row.createCell(j).setCellValue(new BigDecimal(strArray[arr_cov[j]].toString()).doubleValue());
                        }
                    }
                }
            }

            removeRow(sheet, 2);
            String path = main_path+"csv-output";
            File dir = new File(path);
            if (!dir.exists()) dir.mkdirs();
            FileOutputStream fileOut = new FileOutputStream(path+"/L"+k+"_TF.xls");
            workbook.write(fileOut);
            fileNames.add(path+"/L"+k+"_TF.xls");
            fileOut.close();
            System.out.println("Excel file has been generated successfully.");
            reader.close();
        }
        mergeExcel(fileNames, outputFileName);
    }

    public static void removeRow(HSSFSheet sheet, int rowIndex) {
        int lastRowNum=sheet.getLastRowNum();
        if(rowIndex>=0&&rowIndex<lastRowNum){
            sheet.shiftRows(rowIndex+1,lastRowNum, -1);
        }
        if(rowIndex==lastRowNum){
            HSSFRow removingRow=sheet.getRow(rowIndex);
            if(removingRow!=null){
                sheet.removeRow(removingRow);
            }
        }
    }

    private static void mergeExcel(List<String> fileNames, String outputFileName) {

        try {
            // Create a new workbook to store merged data
            Workbook mergedWorkbook = new XSSFWorkbook();

            for (String fileName : fileNames) {
                FileInputStream inputStream = new FileInputStream(new File(fileName));
                Workbook workbook = WorkbookFactory.create(inputStream);
                // Iterate through each sheet in the workbook
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    copySheet(mergedWorkbook, sheet);
                }
                inputStream.close();
            }
            File dir = new File(generated_output_file);
            if (!dir.exists()) dir.mkdirs();
            // Write the merged workbook to a file
            FileOutputStream outputStream = new FileOutputStream(generated_output_file+"\\"+outputFileName+".xlsx");
            mergedWorkbook.write(outputStream);
            outputStream.close();

            System.out.println("Merged successfully! in the "+ generated_output_file+"\\"+outputFileName+ ".xlsx directory");
        } catch (IOException e) {

        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private static void copySheet(Workbook destWorkbook, Sheet sourceSheet) {
        Sheet destSheet = destWorkbook.createSheet(sourceSheet.getSheetName());

        for (int rowNum = 0; rowNum <= sourceSheet.getLastRowNum(); rowNum++) {
            Row sourceRow = sourceSheet.getRow(rowNum);
            Row destRow = destSheet.createRow(rowNum);

            if (sourceRow != null) {
                for (int colNum = 0; colNum < sourceRow.getLastCellNum(); colNum++) {
                    Cell sourceCell = sourceRow.getCell(colNum);
                    Cell destCell = destRow.createCell(colNum);

                    if (sourceCell != null) {
                        if (sourceCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                            destCell.setCellValue(sourceCell.getStringCellValue());
                        } else {
                            destCell.setCellValue(sourceCell.getNumericCellValue());
                        }

                    }
                }
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}
