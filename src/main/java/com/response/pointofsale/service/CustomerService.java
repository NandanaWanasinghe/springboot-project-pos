package com.response.pointofsale.service;

import com.response.pointofsale.dto.CustomerDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseCustomerDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseCustomerNameDTO;
import com.response.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.response.pointofsale.dto.request.CustomerUpdateRequestDTO;

import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    String updateCustomer(CustomerUpdateRequestDTO dto, int id);

    String deleteCustomer(int id);

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getCustomersBySameName(String name);

    List<CustomerDTO> getCustomersByActiveState();

    List<CustomerDTO> getCustomersByName(String name);

    List<CustomerDTO> getCustomersByState(boolean status);

    CustomerDTO getCustomersByNic(String nic);

    int getCustomerCount();

    PaginatedResponseCustomerDTO getCustomerPage(int page, int size);

    PaginatedResponseCustomerNameDTO getCustomerPageByName(String name, int page, int size);
}
