package com.response.pointofsale.dto.pagination;

import com.response.pointofsale.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaginatedResponseCustomerDTO {
    private List<CustomerDTO> list;
    private long customerCount;
}
