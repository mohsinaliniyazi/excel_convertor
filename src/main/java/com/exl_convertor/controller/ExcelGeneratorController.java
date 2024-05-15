package com.exl_convertor.controller;


import com.exl_convertor.model.ExcelGeneratorEntity;
import com.exl_convertor.service.ExcelGeneratorService;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ExcelGeneratorController {

    private final Logger LOGGER = LoggerFactory.getLogger(ExcelGeneratorController.class);

    @Autowired
    ExcelGeneratorService excelGeneratorService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public String saveEmployeeData(@RequestBody ExcelGeneratorEntity excelGeneratorEntity) throws IOException, CsvException {
        LOGGER.info("saveEmployeeData is method within EmployeeController {}");
        excelGeneratorService.generateExcelFile(excelGeneratorEntity);
        return "generated successfully";
    }
}
