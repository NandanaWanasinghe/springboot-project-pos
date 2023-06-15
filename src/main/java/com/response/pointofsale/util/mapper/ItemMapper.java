package com.response.pointofsale.util.mapper;

import com.response.pointofsale.dto.ItemDTO;
import com.response.pointofsale.dto.request.ItemSaveRequestDTO;
import com.response.pointofsale.dto.response.ActiveItemResponseDTO;
import com.response.pointofsale.dto.response.ItemResponseByCustomerDTO;
import com.response.pointofsale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemDTO> entityListToDtoList(List<Item> items);

    List<ItemResponseByCustomerDTO> itemEntityListToDtoList(List<Item> items);

    List<ItemDTO> pageToList(Page<Item> page);

    List<ActiveItemResponseDTO> activeItemResponsePage(Page<Item> page);


}
