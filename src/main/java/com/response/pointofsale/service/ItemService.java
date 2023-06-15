package com.response.pointofsale.service;

import com.response.pointofsale.dto.ItemDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseActiveItemDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseItemDTO;
import com.response.pointofsale.dto.request.ItemSaveRequestDTO;
import com.response.pointofsale.dto.request.ItemsUpdateRequestDTO;
import com.response.pointofsale.dto.response.ItemResponseByCustomerDTO;

import java.util.List;

public interface ItemService {
    String addItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemDTO> getAllItems();

    List<ItemResponseByCustomerDTO> checkAvailability();

    String updateItems(ItemsUpdateRequestDTO dto, int id);

    String deleteItem(int id);

    List<ItemDTO> getAllItemsByState(boolean b);

    int countAllItems();

    PaginatedResponseItemDTO getAllItemPagination(int page, int size);

    PaginatedResponseActiveItemDTO getAllActiveItemPagination(int page, int size);
}
