package com.blanke.purebook_android.web;

import java.io.IOException;

public class ErrorResponseException extends IOException {
    public ErrorResponseException(){
        super();
    }

    public ErrorResponseException(String detailMessage){
        super(detailMessage);
    }
}
