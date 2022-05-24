package com.example.logservice.repository;

import com.example.logservice.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("from Log log join fetch log.infos info")
    public List<Log> findAllLogsWithInfo();
}
