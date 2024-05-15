package com.exl_convertor.model;

public class ExcelGeneratorEntity {

    private String filename;
    private String path;
    private String columnName;
    private String columnName2;

    public ExcelGeneratorEntity(String filename, String path, String columnName, String columnName2) {
        this.filename = filename;
        this.path = path;
        this.columnName = columnName;
        this.columnName2 = columnName2;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName2() {
        return columnName2;
    }

    public void setColumnName2(String columnName2) {
        this.columnName2 = columnName2;
    }
}
