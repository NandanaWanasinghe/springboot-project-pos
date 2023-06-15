package com.response.pointofsale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemSaveRequestDTO {

    private String itemName;
    private String measuringUnit;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;

}
