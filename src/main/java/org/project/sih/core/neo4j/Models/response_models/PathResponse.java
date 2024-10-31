package org.project.sih.core.neo4j.Models.response_models;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PathResponse {
    private PathWalletModel start ;
    private PathWalletModel end ;
    private List<PathSegment> segments ;
    private double length;
}
