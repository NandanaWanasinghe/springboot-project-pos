package com.response.pointofsale.dto.request;

import com.response.pointofsale.entity.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ItemsUpdateRequestDTO {

    private double balanceQty;
    private double sellingPrice;
    private boolean activeState;
}
