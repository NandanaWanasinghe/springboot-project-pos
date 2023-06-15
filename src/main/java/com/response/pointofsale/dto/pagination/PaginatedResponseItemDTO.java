package com.response.pointofsale.dto.pagination;

import com.response.pointofsale.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaginatedResponseItemDTO {
    private List<ItemDTO> list;
    private long dataCount;

}
