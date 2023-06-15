package com.response.pointofsale.controller;


import com.response.pointofsale.dto.request.RequestOrderSaveDTO;
import com.response.pointofsale.service.OrderService;
import com.response.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/add-order") // add order
    public ResponseEntity<StandardResponse> addOrder(@RequestBody RequestOrderSaveDTO dto) {
        String id = orderService.addOrder(dto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,  id +" successfully saved",id),
                HttpStatus.CREATED
        );
    }
}
