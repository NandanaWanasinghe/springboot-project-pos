package com.response.pointofsale.service;

import com.response.pointofsale.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO dto);
}
