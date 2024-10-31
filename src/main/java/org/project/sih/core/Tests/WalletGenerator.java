package org.project.sih.core.Tests;

import org.project.sih.core.neo4j.dto.WalletDTO;
import org.project.sih.core.utility.Utility;
import org.project.sih.core.utility.Wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WalletGenerator {


        public static WalletDTO generateWallet() {
            WalletDTO wallet = new WalletDTO();
            wallet.setWalletAddress(Utility.generateRandomString(45));
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(Wallet.EXCHANGE);
            arrayList.add(Wallet.NORMAL);
            arrayList.add(Wallet.SUSPICIOUS);
            wallet.setWalletType(Utility.generateRandomStringFromList(arrayList));

            wallet.setSentWalletAddresses(new ArrayList<>());
            wallet.setReceivedWalletAddresses(new ArrayList<>());
            wallet.setInteractedWalletAddresses(new ArrayList<>());
            return  wallet;
        }

}
