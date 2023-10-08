package com.chrispbacon.chrispbaconend.util;

import com.chrispbacon.chrispbaconend.exception.IllegalInputException;

public class Guard {
    public static void againstNull(Object object, String errorMessage){
        if (object == null){
            throw new IllegalInputException(errorMessage);
        }
    }
}
