package com.response.pointofsale.service.impl;

import com.response.pointofsale.dto.request.RequestOrderSaveDTO;
import com.response.pointofsale.entity.Order;
import com.response.pointofsale.entity.OrderDetails;
import com.response.pointofsale.exception.EntryNotFoundException;
import com.response.pointofsale.repo.CustomerRepo;
import com.response.pointofsale.repo.ItemRepo;
import com.response.pointofsale.repo.OrderDetailsRepo;
import com.response.pointofsale.repo.OrderRepo;
import com.response.pointofsale.service.OrderService;
import com.response.pointofsale.util.mapper.OrderMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO dto) {
        Order order = new Order(
                customerRepo.getById(dto.getCustomer()),
                dto.getDate(),
                dto.getTotal()
        );

        orderRepo.save(order);
        if(orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.map(dto.getRequestOrderDetailsSaveDTOS(),
                    new TypeToken<List<OrderDetails>>(){}.getType());

            for(int i = 0; i < orderDetails.size(); i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(dto.getRequestOrderDetailsSaveDTOS().get(i).getItems()));
            }

            if(orderDetails.size() > 0) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        throw new EntryNotFoundException("Invalid entry...");
    }
}
