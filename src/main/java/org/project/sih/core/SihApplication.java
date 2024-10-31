package org.project.sih.core;

import org.neo4j.driver.Transaction;
import org.project.sih.core.neo4j.Models.TransactionModel;
import org.project.sih.core.neo4j.dto.TransactionDTO;
import org.project.sih.core.neo4j.service.WalletService;
import org.project.sih.core.processes.TransactionProcessor;
import org.project.sih.core.utility.ResponseModel;
import org.project.sih.core.utility.Wallet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.util.Date;

@SpringBootApplication
@EnableNeo4jRepositories
public class SihApplication {



   static WalletService walletService;
   static TransactionProcessor transactionProcessor;
    SihApplication(WalletService walletService, TransactionProcessor transactionProcessor)
    {
        SihApplication.walletService = walletService;
        SihApplication.transactionProcessor = transactionProcessor;
    }

    public static void main(String[] args) {
        SpringApplication.run(SihApplication.class, args);









        transactionProcessor.init();
        transactionProcessor.setSourceWallet("W1");
        transactionProcessor.start();




    }

}
