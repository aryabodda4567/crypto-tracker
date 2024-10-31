package org.project.sih.core.neo4j.Models.response_models;


import lombok.Data;

@Data
public class PathWalletModel {
    private long identity;
    private String walletAddress;
    private String walletType;
    private String elementId;
}