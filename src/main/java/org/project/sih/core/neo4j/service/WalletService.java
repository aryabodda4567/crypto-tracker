package org.project.sih.core.neo4j.service;


import jakarta.transaction.Transactional;
import org.project.sih.core.neo4j.Models.TransactionModel;
import org.project.sih.core.neo4j.Models.WalletModel;
import org.project.sih.core.neo4j.Models.response_models.PathResponse;
import org.project.sih.core.neo4j.dto.TransactionDTO;
import org.project.sih.core.neo4j.dto.WalletDTO;
import org.project.sih.core.neo4j.repos.WalletRepo;
import org.project.sih.core.utility.ResponseModel;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class WalletService {


    WalletRepo walletRepo;
    public  WalletService(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    public ResponseModel createWallet(String walletAddress) {
        WalletModel wallet = new WalletModel();
        wallet.setWalletAddress(walletAddress);
        walletRepo.save(wallet);
        return new ResponseModel(1,"Wallet created",wallet);
    }
    public ResponseModel createWallet(WalletDTO walletModel) {
        WalletModel wallet = new WalletModel();
        wallet.setWalletAddress(walletModel.getWalletAddress());
        wallet.setWalletType(walletModel.getWalletType());
        walletRepo.save(wallet);
        return new ResponseModel(1,"Wallet created",wallet);
    }



    public  ResponseModel getWallet(String walletAddress) {
        Optional<WalletModel> walletModelOptional = Optional.ofNullable(walletRepo.findByWalletAddress(walletAddress));
        if (walletModelOptional.isPresent()) {
            WalletModel wallet = walletModelOptional.get();
            return new ResponseModel(1,"Wallet found",wallet);
        }else{
            return new ResponseModel(0,"Wallet not found",null);
        }
    }


    @Transactional
    public  ResponseModel addTransaction(TransactionDTO transactionDTO) {
        WalletModel fromWallet , toWallet;
        fromWallet = (WalletModel) getWallet(transactionDTO.getFromAddress()).getData();
        toWallet = (WalletModel) getWallet(transactionDTO.getToAddress()).getData();
         try {
            walletRepo.createTransaction(fromWallet, toWallet, transactionDTO);
            return new ResponseModel(1,"Transaction added",null);
        }catch (Exception e) {

            return new ResponseModel(0,"Transaction could not be added",e.getMessage());
        }
    }

    public boolean isTransacted(String fromWalletAddress, String toWalletAddress) {
        int result = walletRepo.isTransacted(fromWalletAddress,toWalletAddress);
        return result >= 1;

    }

    public List<PathResponse> findPath(String fromWalletAddress, String walletType) {
        return  walletRepo.findPathFromWalletToWalletType(fromWalletAddress,walletType);
    }





}
