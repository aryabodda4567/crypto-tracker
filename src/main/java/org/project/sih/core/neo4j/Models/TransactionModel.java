package org.project.sih.core.neo4j.Models;

import lombok.*;
import org.project.sih.core.neo4j.Models.WalletModel;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RelationshipProperties
public class TransactionModel {

    @Id
    @GeneratedValue
    String id;
    @TargetNode
     WalletModel toWallet;

    String fromAddress;
    String toAddress;

    long transactionCount;

    double totalAmount = 0.0;
    List<Long> transactionDates ;
    List<String > transactionHashes ;

    public  void autoUpdate(){
        this.transactionCount++;
    }

    public  void addAmount(double amount){
         this.totalAmount+=amount;
    }

    public  void addDate(Long date){
        if(this.transactionDates==null)
            this.transactionDates=new ArrayList<>();
          this.transactionDates.add(date);

    }

    public  void addHash(String hash){
        if(this.transactionHashes==null)
            this.transactionHashes=new ArrayList<>();
        this.transactionHashes.add(hash);
    }




}
