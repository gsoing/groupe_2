package com.episen.tp2.business.dto;

import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lock {

    private String owner;
    private Timestamp created;

}
