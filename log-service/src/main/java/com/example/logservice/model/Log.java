package com.example.logservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "LOGS")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INDEX")
    private long id;

    @Column(name = "LOG_TYPE")
    @Enumerated(EnumType.STRING)
    private LogType logType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "log")
    private List<Info> infos;
}
