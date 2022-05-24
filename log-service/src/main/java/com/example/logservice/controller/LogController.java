package com.example.logservice.controller;

import com.example.logservice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping(value = "/loadLogs/{fileName}", headers = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public byte[] createExcelLog(@PathVariable("fileName") String fileName) {
        return logService.createLogFile(fileName);
    }

}
