package com.response.pointofsale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ActiveItemResponseDTO {
    private String itemName;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;
}
