package com.response.pointofsale.dto.pagination;

import com.response.pointofsale.dto.response.ActiveItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaginatedResponseActiveItemDTO {

    private List<ActiveItemResponseDTO> list;
    private long activeItemCount;

}
