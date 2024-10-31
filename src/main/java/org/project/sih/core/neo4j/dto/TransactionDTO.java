package org.project.sih.core.neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransactionDTO {
    private String transactionHash;
    private String fromAddress;
    private String toAddress;
    private double amount;
    private Long timestamp;
}
