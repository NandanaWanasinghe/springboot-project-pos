package com.response.pointofsale.service.impl;

import com.response.pointofsale.dto.CustomerDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseCustomerDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseCustomerNameDTO;
import com.response.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.response.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.response.pointofsale.entity.Customer;
import com.response.pointofsale.exception.EntryDuplicateException;
import com.response.pointofsale.exception.EntryNotFoundException;
import com.response.pointofsale.repo.CustomerRepo;
import com.response.pointofsale.service.CustomerService;
import com.response.pointofsale.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = customerMapper.RequestDtoToEntity(customerSaveRequestDTO);
        customer.setActiveState(true);
        if (!customerRepo.existsById(customer.getCustomerId())) {
            return customerRepo.save(customer).getCustomerName();
        } else
            throw new EntryDuplicateException("Already Exist");
    }

    @Override // update customers
    public String updateCustomer(CustomerUpdateRequestDTO dto, int id) {
        if (customerRepo.existsById(id)) {
            customerRepo.updateCustomer(
                    dto.getCustomerName(), dto.getCustomerAddress(), dto.getCustomerSalary(), dto.isActiveState(), id);
            return dto.getCustomerName();
        } else
            throw new EntryNotFoundException("Invalid customer id");
    }

    @Override // delete customer
    public String deleteCustomer(int id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteCustomer(id);
            return "Customer id " + id + " deleted successfully";
        } else
            throw new EntryNotFoundException("Invalid customer id");
    }

    @Override // get all active customers
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> dtos = customerMapper.entityListToDtoList(customers);
        return dtos;
    }

    @Override // get all customers in same name
    public List<CustomerDTO> getCustomersBySameName(String name) {
        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(name);
        if (customers.size() != 0) {
            List<CustomerDTO> dtos = customerMapper.entityListToDtoList(customers);
            return dtos;
        } else
            throw new EntryNotFoundException("Customers not in named by " + name);
    }

    @Override // get active customers
    public List<CustomerDTO> getCustomersByActiveState() {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(true);
        List<CustomerDTO> dtos = customerMapper.entityListToDtoList(customers);
        return dtos;
    }

    @Override // get active customers by name
    public List<CustomerDTO> getCustomersByName(String name) {
        List<Customer> customers = customerRepo.findAllByCustomerNameEqualsAndActiveStateEquals(name, true);
        if (customers.size() != 0) {
            List<CustomerDTO> dtos = customerMapper.entityListToDtoList(customers);
            return dtos;
        } else
            throw new EntryNotFoundException("Customer name doesn't match");
    }

    @Override // get customers by state
    public List<CustomerDTO> getCustomersByState(boolean status) {
        List<Customer> customers = customerRepo.findAllByActiveStateEquals(status);
        List<CustomerDTO> dtos = customerMapper.entityListToDtoList(customers);
        return dtos;
    }

    @Override
    public CustomerDTO getCustomersByNic(String nic) {
        Customer customer = customerRepo.findAllByNicEquals(nic);
        CustomerDTO dto = customerMapper.entityToDto(customer);
        return dto;
    }

    @Override // count active customers
    public int getCustomerCount() {
        int count = customerRepo.countCustomerByActiveStateEquals(true);
        return count;
    }

    @Override // get customers by page
    public PaginatedResponseCustomerDTO getCustomerPage(int page, int size) {
        Page<Customer> customerPage = customerRepo.findAll(PageRequest.of(page, 2));
        return new PaginatedResponseCustomerDTO(
                customerMapper.pageToList(customerPage),
                customerRepo.count()
        );
    }

    @Override
    public PaginatedResponseCustomerNameDTO getCustomerPageByName(String name, int page, int size) {
        List<Customer> customers = customerRepo.findAllByCustomerNameEquals(name);
        if(customers.size() != 0){
            Page<Customer> getPage = customerRepo.findAllByCustomerNameEquals(name, PageRequest.of(page, 5));
            return new PaginatedResponseCustomerNameDTO(
                    customerMapper.customerNamePage(getPage),
                    customerRepo.countCustomerByCustomerNameEquals(name)
            );
        }
        throw new EntryNotFoundException("No customers in same name");
    }

}
