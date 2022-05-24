package com.example.logservice.service;

import com.example.logservice.client.MarketPlaceClient;
import com.example.logservice.model.Info;
import com.example.logservice.model.Log;
import com.example.logservice.model.LogType;
import com.example.logservice.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RefreshScope
public class LogService {

    @Value("${rest.secret}")
    private String secret;

    private static final long ONE_MINUTE = 1000 * 60;

    private final MarketPlaceClient marketPlaceClient;

    private final LogRepository logRepository;

    @Autowired
    public LogService(MarketPlaceClient marketPlaceClient, LogRepository logRepository) {
        this.marketPlaceClient = marketPlaceClient;
        this.logRepository = logRepository;
    }


    /**
     * Осуществляем выгрузку логов с микросервиса магазина каждые 30 секунд в базу данных.
     */
    @Scheduled(initialDelay = ONE_MINUTE, cron = "*/30 * * * * *")
    public void getLogsAndSave() {
        List<Pair<LogType, Info>> logs = marketPlaceClient.findAllLogs(secret);

        if (logs.isEmpty()) {
            return;
        }

        Map<LogType, List<Info>> logsMaps = new HashMap<>();

        logs.forEach(pair -> {
            LogType logType = pair.getFirst();
            Info info = pair.getSecond();
            logsMaps.putIfAbsent(logType, new ArrayList<>());
            logsMaps.computeIfPresent(logType, (k, v) -> {
                v.add(info);
                return v;
            });
        });

        save(logsMaps);
    }


    /**
     * Метод формирует файл excel с логами
     * @param nameFile имя excel файла
     */
    public byte[] createLogFile(String nameFile) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        HSSFSheet sheet = hssfWorkbook.createSheet("Файл логов сервиса магазина компьютеров");

        List<Log> logs = logRepository.findAllLogsWithInfo();

        int rowCount = 0;

        Row row = sheet.createRow(rowCount);

        row.createCell(0).setCellValue("Статус");
        row.createCell(1).setCellValue("Сообщение");
        row.createCell(2).setCellValue("Время события");


        logs.forEach(log -> log.getInfos().forEach(info -> setSheetHeader(sheet, rowCount, log.getLogType(), info)));

        try (var out = new FileOutputStream(nameFile)) {
            hssfWorkbook.write(out);
        } catch (IOException e) {
            log.error("Неуспешное создание файла с логами");
        }

        log.info("Файл Excel успешно сформирован");

        return hssfWorkbook.getBytes();
    }

    private void setSheetHeader(HSSFSheet sheet, int rowNum, LogType logType, Info info) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(logType.toString());
        row.createCell(1).setCellValue(info.getMessage());
        row.createCell(2).setCellValue(info.getLogTime().toString());
    }

    private void save(Map<LogType, List<Info>> logs) {
        List<Log> listLogs = logs.entrySet()
                .stream()
                .map(log -> Log.builder()
                        .logType(log.getKey())
                        .infos(log.getValue())
                        .build())
                .collect(Collectors.toList());

        logRepository.saveAll(listLogs);
    }
}
