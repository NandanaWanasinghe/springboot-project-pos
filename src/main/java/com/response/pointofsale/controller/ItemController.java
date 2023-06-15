package com.response.pointofsale.controller;

import com.response.pointofsale.dto.ItemDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseActiveItemDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseItemDTO;
import com.response.pointofsale.dto.request.ItemSaveRequestDTO;
import com.response.pointofsale.dto.request.ItemsUpdateRequestDTO;
import com.response.pointofsale.dto.response.ItemResponseByCustomerDTO;
import com.response.pointofsale.service.ItemService;
import com.response.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Add Items
    @PostMapping(path = "/add-item")
    public ResponseEntity<StandardResponse> addItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String id = itemService.addItem(itemSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, id + " successfully saved", id),
                HttpStatus.CREATED
        );
    }


    // get all items
    @GetMapping(path = {"/get-all-items"})
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemDTO> itemDTOS = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "items get successfully", itemDTOS),
                HttpStatus.OK
        );
    }

    // check availability by customer
    @GetMapping(path = {"/check-availability"})
    public ResponseEntity<StandardResponse> checkAvailability() {
        List<ItemResponseByCustomerDTO> dtos = itemService.checkAvailability();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Available items...", dtos),
                HttpStatus.OK
        );
    }

    //update items by query
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<StandardResponse> updateItems(@RequestBody ItemsUpdateRequestDTO dto,
                                                        @PathVariable(value = "id") int id) {
        String update = itemService.updateItems(dto, id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated Successfully...", dto),
                HttpStatus.ACCEPTED
        );

    }

    // Delete item by query
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<StandardResponse> deleteItems(@PathVariable(value = "id") int id) {
        String delete = itemService.deleteItem(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Item delete Successfully...", delete),
                HttpStatus.ACCEPTED
        );
    }

    //Get items by State value
    @GetMapping(path = "/get-items-by state/{state}")
    public ResponseEntity<StandardResponse> getItemsByState(@PathVariable (value = "state")String state){
        if(state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")){
            boolean b = state.equalsIgnoreCase("active") ? true : false;
            List<ItemDTO> itemDTOS = itemService.getAllItemsByState(b);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Get all " + state + " items successfully", itemDTOS),
                    HttpStatus.ACCEPTED
            );
        }else if(state.equalsIgnoreCase("all")){
            List<ItemDTO> dtos = itemService.getAllItems();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Get all " + state + " items successfully", dtos),
                    HttpStatus.ACCEPTED
            );
        }else {
            String error = "error";
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Invalid state entered", error),
                    HttpStatus.FAILED_DEPENDENCY
            );
        }
    }

    // ------ Day 09 -------------------------------
    @GetMapping(path = "get-item-count")
    public ResponseEntity<StandardResponse> getAllItemCount(){
        int itemCount = itemService.countAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All active items...", itemCount),
                HttpStatus.ACCEPTED
        );
    }

//    @GetMapping(path ={ "get-item-page/"},
//    params = {"page","size"})
//    public ResponseEntity<StandardResponse> getAllItemPaginated(
//            @RequestParam (value = "page") int page,
//            @RequestParam (value = "size") @Max(50) int size){
//
//        PaginatedResponseItemDTO dto = itemService.getItemPagination(page, size);
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(201, "All active items...", dto),
//                HttpStatus.ACCEPTED
//        );
//    }
    @GetMapping(path = {"get-items-page/{page}"}) // get all items by page
    public ResponseEntity<StandardResponse> getItemPaginate(@PathVariable (value = "page")int page){
        int size = 0;
        PaginatedResponseItemDTO dto = itemService.getAllItemPagination(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All active items...", dto),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(path = {"get-active-items-page/{page}"}) // get all active items by page
    public ResponseEntity<StandardResponse> getActiveItemPaginate(@PathVariable (value = "page")int page){
        int size = 0;
        PaginatedResponseActiveItemDTO dto = itemService.getAllActiveItemPagination(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All active items...", dto),
                HttpStatus.ACCEPTED
        );
    }
}
