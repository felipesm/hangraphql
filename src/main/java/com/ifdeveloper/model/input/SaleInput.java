package com.ifdeveloper.model.input;

import lombok.Data;

@Data
public class SaleInput {
    private String id;
    private String date;
    private Float total;
    private String status;
    private String voucherId;
}
