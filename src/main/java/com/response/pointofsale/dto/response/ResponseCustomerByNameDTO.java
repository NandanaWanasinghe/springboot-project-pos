package com.response.pointofsale.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ResponseCustomerByNameDTO {

    private String customerName;
    private String nic;
    private boolean activeState;
}
