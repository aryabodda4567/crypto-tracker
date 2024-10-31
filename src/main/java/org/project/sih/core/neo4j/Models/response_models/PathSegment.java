package org.project.sih.core.neo4j.Models.response_models;


import lombok.Data;

@Data
public class PathSegment {
    private PathWalletModel start;
    private PathWalletModel end;
    private TransactionResponseModel relationship ;
}
