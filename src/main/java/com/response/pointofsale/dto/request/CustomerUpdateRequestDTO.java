package com.response.pointofsale.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerUpdateRequestDTO {

    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private boolean activeState;

}
