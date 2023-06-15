package com.response.pointofsale.dto.response;

import com.response.pointofsale.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemResponseByCustomerDTO {
    private String itemName;
    private MeasuringUnitType measuringUnit;
    private double balanceQty;
    private double sellingPrice;

}
