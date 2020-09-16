package com.shoppingcar.demo.apierror;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class CustomError extends Throwable {

    private HttpStatus status;
    private int code;
    private String message;
    private String backendMessage;

    public CustomError(HttpStatus status, int code, String message, String backendMessage) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.backendMessage = backendMessage;
    }

}
