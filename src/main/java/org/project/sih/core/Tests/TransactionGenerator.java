package org.project.sih.core.Tests;

import org.project.sih.core.neo4j.dto.TransactionDTO;
import org.project.sih.core.neo4j.dto.WalletDTO;
import org.project.sih.core.utility.Utility;

import java.util.ArrayList;
import java.util.Random;

public class TransactionGenerator {


    public  static TransactionDTO createTransactionDTO(WalletDTO from, WalletDTO to) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionHash(Utility.generateRandomString(100));
        transactionDTO.setFromAddress(from.getWalletAddress());
        transactionDTO.setToAddress(to.getWalletAddress());
        transactionDTO.setAmount(Utility.generateRandomDouble(4));
        transactionDTO.setTimestamp(System.currentTimeMillis());

        from.getInteractedWalletAddresses().add(to.getWalletAddress());
        to.getInteractedWalletAddresses().add(from.getWalletAddress());

        return transactionDTO;


    }
}
