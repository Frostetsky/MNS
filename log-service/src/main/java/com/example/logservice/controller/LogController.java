package com.example.logservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    @GetMapping(value = "/loadLogs/{fileName}", headers = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public byte[] createExcelLog(@PathVariable("fileName") String fileName) {
        return new byte[]{};
    }

}
