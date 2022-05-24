package com.example.logservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "INFORMATIONS")
public class Info {

    @Id
    @Column(name = "INDEX")
    private long id;

    @Column(name = "EVENT_MESSAGE")
    private String message;

    @Column(name = "EVENT_DATE")
    private LocalDateTime logTime;

    @ManyToOne
    @JoinColumn(name = "LOG_TYPE_INDEX")
    private Log log;
}
