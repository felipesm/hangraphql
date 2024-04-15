package com.ifdeveloper.model.input;

import lombok.Data;

@Data
public class VoucherInput {
    private String id;
    private Float percentage;
    private String expire;
}
