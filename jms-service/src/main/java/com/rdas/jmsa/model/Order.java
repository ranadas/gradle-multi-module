package com.rdas.jmsa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by x148128 on 30/05/2017.
 */
@Data @AllArgsConstructor
public class Order implements Serializable{
    private final String id;
    private final String details;
    private BigDecimal amount;
    private String currency;
}
