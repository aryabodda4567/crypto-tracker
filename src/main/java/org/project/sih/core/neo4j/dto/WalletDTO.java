package org.project.sih.core.neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {

    String walletAddress;
    String walletType;
    long transactionCount;

    List<String> interactedWalletAddresses;
    List<String>  sentWalletAddresses;
    List<String>  receivedWalletAddresses;


}
