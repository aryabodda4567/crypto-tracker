package org.project.sih.core.neo4j.repos;


import org.project.sih.core.neo4j.Models.TransactionModel;
import org.project.sih.core.neo4j.Models.WalletModel;
import org.project.sih.core.neo4j.Models.response_models.PathResponse;
import org.project.sih.core.neo4j.dto.TransactionDTO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletRepo extends Neo4jRepository<WalletModel, String> {

    @Query("MATCH (from:WalletModel {walletAddress: $fromAddress}), (to:WalletModel {walletAddress: $toAddress}) " +
            "MERGE (from)-[t:Transacted]->(to) " +
            "ON CREATE SET t.transactionCount = 1, t.totalAmount = $amount, " +
            "t.transactionDates = [$timestamp], t.transactionHashes = [$transactionHash] " +
            "ON MATCH SET t.transactionCount = t.transactionCount + 1, " +
            "t.totalAmount = t.totalAmount + $amount, " +
            "t.transactionDates = CASE WHEN t.transactionDates IS NULL THEN [$timestamp] ELSE t.transactionDates + $timestamp END, " +
            "t.transactionHashes = CASE WHEN t.transactionHashes IS NULL THEN [$transactionHash] ELSE t.transactionHashes + $transactionHash END " +
            "RETURN t")
    TransactionModel createOrUpdateTransaction(@Param("fromAddress") String fromAddress,
                                               @Param("toAddress") String toAddress,
                                               @Param("amount") double amount,
                                               @Param("timestamp") long timestamp,
                                               @Param("transactionHash") String transactionHash);

    @Query("MATCH (w1:WalletModel {walletAddress: $fromAddress})-[r:Transacted]->(w2:WalletModel {walletAddress: $toAddress})" +
            " RETURN COUNT(r)")
    int isTransacted(@Param("fromAddress") String fromWalletAddress,
                         @Param("toAddress") String toWalletAddress);

    default TransactionModel createTransaction(WalletModel fromWallet, WalletModel toWallet, TransactionDTO transactionDTO) {
        return createOrUpdateTransaction(fromWallet.getWalletAddress(), toWallet.getWalletAddress(),
                transactionDTO.getAmount(), transactionDTO.getTimestamp(),
                transactionDTO.getTransactionHash());
    }

    default TransactionModel updateTransaction(WalletModel fromWallet, WalletModel toWallet, TransactionDTO transactionDTO) {
        return createOrUpdateTransaction(fromWallet.getWalletAddress(), toWallet.getWalletAddress(),
                transactionDTO.getAmount(), transactionDTO.getTimestamp(),
                transactionDTO.getTransactionHash());
    }

    @Query("MATCH (w:WalletModel {walletAddress: $address}) " +
            "RETURN w")
    WalletModel findByWalletAddress(@Param("address") String address);

    @Query("MATCH p=(from:WalletModel {walletAddress: $fromAddress})-[*]->(to:WalletModel {walletType: $targetType}) " +
            "RETURN p")
    List<PathResponse> findPathFromWalletToWalletType(@Param("fromAddress") String fromWalletAddress,
                                    @Param("targetType") String targetType);


    @Query(" MATCH path = (start:WalletModel {walletAddress: $start})-[r:Transacted*]-(end:WalletModel {walletAddress: $end})" +
            "RETURN path")
    List<PathResponse> findPathBetweenTwoWallets(@Param(value = "start") String startAddress,
                                                 @Param(value = "end") String endAddress);
}