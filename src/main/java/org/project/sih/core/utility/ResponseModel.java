package org.project.sih.core.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseModel {

    int status;
    String message;
    Object data;

    public ResponseModel(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
