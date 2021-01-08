package com.springboot.async.springBootAsync.Entities;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class PostMatchReq implements Serializable {

    private String dobMatchingScore;
    private String clientId;
    private String requestId;
    private String nameMatchingScore;
    private String error;
    private String errorCode;
    private String errorMessage;
    private String transactionStatus;
    private String addressMatchingScore;

}
