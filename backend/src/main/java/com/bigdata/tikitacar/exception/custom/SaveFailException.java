package com.bigdata.tikitacar.exception.custom;

public class SaveFailException extends RuntimeException{
    public SaveFailException(String msg, Throwable t){
        super(msg, t);
    }

    public SaveFailException(String msg){
        super(msg);
    }

    public SaveFailException(){
        super();
    }
}
