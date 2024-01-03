package com.ifdeveloper.controller;

import com.ifdeveloper.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TaxController {

    @Autowired
    private TaxService taxService;

    @SchemaMapping(typeName = "Query", value = "shipping")
    public Float shipping(@Argument Float price, @Argument Float weight) {
        return taxService.shippingTax(price, weight);
    }
}
