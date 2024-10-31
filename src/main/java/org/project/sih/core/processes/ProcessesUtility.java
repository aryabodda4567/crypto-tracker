package org.project.sih.core.processes;

import org.project.sih.core.neo4j.Models.WalletModel;
import org.project.sih.core.neo4j.dto.TransactionDTO;
import org.project.sih.core.neo4j.dto.WalletDTO;
import org.project.sih.core.utility.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProcessesUtility {

    public  static  Boolean isExchange(WalletModel walletModel){
        return  (walletModel.getWalletType().equals(Wallet.EXCHANGE));
    }

    public  static WalletDTO getWallet(String walletAddress) {
        return new WalletDTO();
    }

    public  static HashMap<String, List<String>> getInteractedWallets(String walletAddress){
        //fetch from server and return
        return new HashMap<>();
    }

    public  static List<TransactionDTO> getTransactions(String fromWallet, String toWallet){
        return new ArrayList<>();
    }

    public  static  long getTransactions(String walletAddress){
        return 0;
    }


    public  static  Double getTransactionVolume(String walletAddress){
            return  0.d;
    }

}
