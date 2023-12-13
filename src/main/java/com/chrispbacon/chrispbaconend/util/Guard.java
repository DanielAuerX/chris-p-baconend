package com.chrispbacon.chrispbaconend.util;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;

import java.util.List;

public class Guard {
    public static void againstNull(Object object, String errorMessage){
        if (object == null){
            throw new IllegalInputException(errorMessage);
        }
    }

    public static void againstEmptyList(List list, String errorMessage){
        if (list.isEmpty()){
            throw new RuntimeException(errorMessage);
        }
    }
}
