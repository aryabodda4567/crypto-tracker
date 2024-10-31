package org.project.sih.core.processes;

import lombok.Setter;
import org.project.sih.core.neo4j.dto.TransactionDTO;
import org.project.sih.core.neo4j.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TransactionProcessor implements Processes {

    @Setter
    public String sourceWallet;
    public ConcurrentLinkedDeque<String> stack;
    public ConcurrentLinkedDeque<String> visitedWallets;


    WalletService walletService;


    @Autowired
    public TransactionProcessor(WalletService walletService) {
        this.walletService = walletService;
    }


    @Override
    public void init() {
        stack = new ConcurrentLinkedDeque<>();
        visitedWallets = new ConcurrentLinkedDeque<>();


    }


    @Override
    public void start() {


        stack.push(sourceWallet);

        process();


    }

    @Override
    public void stop() {

    }


    @Override
    public void process() {

        while (!stack.isEmpty()) {
            String wallet = stack.pop();
            if (!visitedWallets.contains(wallet)) {

                visit(wallet);
            }
        }

    }

    @Override
    public void visit(String walletAddress) {

        //Create wallet
        walletService.createWallet(ProcessesUtility.getWallet(walletAddress));

        List<String> sentWallets, receivedWallets;


        HashMap<String, List<String>> interactedWallets = ProcessesUtility.
                getInteractedWallets(walletAddress);


        if (interactedWallets.containsKey("sent")) {
            sentWallets = interactedWallets.get("sent");
            //Create wallets in db
            for (String wallet : sentWallets) {
                walletService.createWallet(ProcessesUtility.getWallet(wallet));
                stack.push(wallet);

                //Establish sent transaction connection between the wallets
                List<TransactionDTO> transactionDTOList = ProcessesUtility.
                        getTransactions(walletAddress, wallet);
                for (TransactionDTO transactionDTO : transactionDTOList) {
                    walletService.addTransaction(transactionDTO);
                }
            }
        }

        //Create wallets in db
        if (interactedWallets.containsKey("received")) {
            receivedWallets = interactedWallets.get("received");
            for (String wallet : receivedWallets) {
                walletService.createWallet(ProcessesUtility.getWallet(wallet));
                stack.push(wallet);
            }
        }



        visitedWallets.add(walletAddress);

    }
}
