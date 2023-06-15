package com.response.pointofsale.dto.pagination;

import com.response.pointofsale.dto.response.ResponseCustomerByNameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedResponseCustomerNameDTO {
    private List<ResponseCustomerByNameDTO> list;
    private long count;
}
