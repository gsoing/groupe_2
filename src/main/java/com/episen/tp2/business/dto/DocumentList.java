package com.episen.tp2.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentList {

    private int page;
    private int nbElements;
    private List<DocumentSummary> data;

}
