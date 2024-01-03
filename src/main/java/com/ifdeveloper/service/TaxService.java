package com.ifdeveloper.service;

import org.springframework.stereotype.Service;

@Service
public class TaxService {

    public Float shippingTax(Float price, Float weight) {
        var percentage = getPercentage(weight);
        var shipping = price * ((weight * percentage) / 100);

        return (float) (Math.floor(shipping * 100) / 100);
    }

    private Float getPercentage(Float weight) {
        if (weight < 4F)
            return 10.2F;

        if (weight < 20F)
            return 21.9F;

        return 33.7F;
    }
}
