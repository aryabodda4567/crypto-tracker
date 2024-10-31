package org.project.sih.core.neo4j.Models.response_models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class TransactionResponseModel {
    private long identity;
    private double totalAmount =0.d;
    private List<Long> transactionDates;
    private List<String> transactionHashes;
    private int transactionCount;
    private String startNodeElementId;
    private String endNodeElementId;


}
