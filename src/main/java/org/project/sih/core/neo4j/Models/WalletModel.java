package org.project.sih.core.neo4j.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node
public class WalletModel {

    @Id
    private String walletAddress;

    private  String walletType;

    @Relationship(type = "Transacted",direction = Relationship.Direction.OUTGOING)
    Set<WalletModel> sentToWallets = new HashSet<>();

    HashMap<String,TransactionModel> transactions;


 }
