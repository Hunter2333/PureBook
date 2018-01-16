package com.blanke.purebook_android.web;

import java.io.IOException;

/**
 * Created by chrischen on 2018/1/16.
 */

public class ErrorResponseException extends IOException {
    public ErrorResponseException(){
        super();
    }

    public ErrorResponseException(String detailMessage){
        super(detailMessage);
    }
}
