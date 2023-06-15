package com.response.pointofsale.service.impl;

import com.response.pointofsale.dto.ItemDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseActiveItemDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseItemDTO;
import com.response.pointofsale.dto.request.ItemSaveRequestDTO;
import com.response.pointofsale.dto.request.ItemsUpdateRequestDTO;
import com.response.pointofsale.dto.response.ItemResponseByCustomerDTO;
import com.response.pointofsale.entity.Item;
import com.response.pointofsale.exception.EntryDuplicateException;
import com.response.pointofsale.exception.EntryNotFoundException;
import com.response.pointofsale.repo.ItemRepo;
import com.response.pointofsale.service.ItemService;
import com.response.pointofsale.util.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Override // add items
    public String addItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = itemMapper.RequestDtoToEntity(itemSaveRequestDTO);
        item.setActiveState(true);
        if (!itemRepo.existsById(item.getItemId())) {
            return itemRepo.save(item).getItemName();
        } else
            throw new EntryDuplicateException("Already Exist");
    }

    @Override
    public List<ItemDTO> getAllItemsByState(boolean b) {
        List<Item> items = itemRepo.findAllByActiveStateEquals(b);
        List<ItemDTO> itemDTOS = itemMapper.entityListToDtoList(items);
        return itemDTOS;
    }

    @Override // get all items
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        List<ItemDTO> itemDTOS = itemMapper.entityListToDtoList(items);
        return itemDTOS;
    }

    @Override // check availability by a customer
    public List<ItemResponseByCustomerDTO> checkAvailability() {
        List<Item> items = itemRepo.findAllByActiveStateEquals(true);
        if (items.size() != 0) {
            List<ItemResponseByCustomerDTO> dtos = itemMapper.itemEntityListToDtoList(items);
            return dtos;
        } else
            throw new EntryNotFoundException("Items not found");

    }

    @Override // items update query
    public String updateItems(ItemsUpdateRequestDTO dto, int id) {
        if (itemRepo.existsById(id)) {
            itemRepo.updateItem(
                    dto.getBalanceQty(), dto.getSellingPrice(), dto.isActiveState(), id);
            return "updated";
        }else
            throw new EntryNotFoundException("Incorrect id");
    }

    @Override // item delete query
    public String deleteItem(int id) {
        if(itemRepo.existsById(id)){
            itemRepo.deleteItem(id);
            return "deleted";
        }else
            throw new EntryNotFoundException("Incorrect id...");
    }

    @Override // get item count
    public int countAllItems() {
        int count = itemRepo.countAllByActiveStateEquals(true);
        return count;
    }

    @Override // display items in pages
    public PaginatedResponseItemDTO getAllItemPagination(int page, int size) {
        Page<Item> getAllItemPagination = itemRepo.findAll(PageRequest.of(page, 5));
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllItemPagination),
                itemRepo.count()
        );
    }

    @Override // get pages with active items
    public PaginatedResponseActiveItemDTO getAllActiveItemPagination(int page, int size) {
            List<Item> items =itemRepo.findAllByActiveStateEquals(true);
            if(items.size() != 0){
                Page<Item> getAllItemPagination = itemRepo.findAllByActiveStateEquals(true,PageRequest.of(page, 5));
                return new PaginatedResponseActiveItemDTO(
                        itemMapper.activeItemResponsePage(getAllItemPagination),
                        itemRepo.countAllByActiveStateEquals(true)
                );
            }
            throw new EntryNotFoundException("No active items available");
    }


//    @Override // set pagination
//    public PaginatedResponseItemDTO getItemPagination(int page, int size) {
//        Page<Item> getAllItemPagination = itemRepo.findAll(PageRequest.of(page, size));
//        return new PaginatedResponseItemDTO(
//                itemMapper.pageToList(getAllItemPagination),
//                itemRepo.count()
//        );
//    }



}
