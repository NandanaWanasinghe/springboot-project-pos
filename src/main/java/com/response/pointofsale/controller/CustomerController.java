package com.response.pointofsale.controller;

import com.response.pointofsale.dto.CustomerDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseCustomerDTO;
import com.response.pointofsale.dto.pagination.PaginatedResponseCustomerNameDTO;
import com.response.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.response.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.response.pointofsale.service.CustomerService;
import com.response.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Add Customer
    @PostMapping(path = "/add-customer")
    public ResponseEntity<StandardResponse> addCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO) {
        String id = customerService.addCustomer(customerSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, customerSaveRequestDTO.getCustomerName() + " saved successfully", id),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/update/{id}") // update customers
    public ResponseEntity<StandardResponse> updateItems(@RequestBody CustomerUpdateRequestDTO dto,@PathVariable(value = "id") int id) {
        String update = customerService.updateCustomer(dto, id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated Successfully...", update),
                HttpStatus.ACCEPTED
        );
    }

    // delete customer
    @DeleteMapping(path = {"/delete"},
            params = {"id"})
    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam(value = "id") int id) {
        String name = customerService.deleteCustomer(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Customer deleted successfully...", name),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(path = "/get-all-customers") // get all customers
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> dtos = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All Customers get successfully...", dtos),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(path = "/get-active-customer") // get active customers
    public ResponseEntity<StandardResponse> getActiveCustomers() {
        List<CustomerDTO> dtos = customerService.getCustomersByActiveState();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Get all active customers successfully...", dtos),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-active-customers-by-name/{name}") // get customers by name
    public ResponseEntity<StandardResponse> getCustomersByName(@PathVariable(value = "name") String name) {
        List<CustomerDTO> dtos = customerService.getCustomersByName(name);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Get all active customers successfully...", dtos),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-customer-by-name/{name}")
    public ResponseEntity<StandardResponse> getCustomerByName(@PathVariable(value = "name") String name) {
        List<CustomerDTO> dtos = customerService.getCustomersBySameName(name);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Get all active customers successfully...", dtos),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-active-customers-by-state/{state}") // get customers by state
    public ResponseEntity<StandardResponse> getCustomersByState(@PathVariable(value = "state") String state) {
        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {
            boolean status = state.equalsIgnoreCase("active") ? true : false;
            List<CustomerDTO> dtos = customerService.getCustomersByState(status);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Get all " + state + " customers successfully...", dtos),
                    HttpStatus.OK
            );
        } else if (state.equalsIgnoreCase("all")) {
            List<CustomerDTO> dtos = customerService.getAllCustomers();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Get all customers successfully...", dtos),
                    HttpStatus.OK
            );
        } else {
            String e = "error";
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(404, "Invalid state entry", e),
                    HttpStatus.NOT_FOUND
            );
        }
    }


    @GetMapping(path = {"/get-by-nic"}, // get customers by nic
            params = {"nic"})
    public ResponseEntity<StandardResponse> getByNic(@RequestParam (value = "nic") String nic){
        CustomerDTO dtos = customerService.getCustomersByNic(nic);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Get customer according to nic " + nic +" successfully...", dtos),
                HttpStatus.OK
        );
    }

    // Day 9 work ---------
    @GetMapping(path = {"/count-customers"})
    public ResponseEntity<StandardResponse> getCustomerCount() {
        int customerCount = customerService.getCustomerCount();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Get all active customers successfully ", customerCount),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-customer-pagination/{page}"}) // customer page
    public ResponseEntity<StandardResponse> getCustomerPage(@PathVariable (value = "page") int page) {
        int size = 0;
        PaginatedResponseCustomerDTO customerPage = customerService.getCustomerPage(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Get all active customers successfully ", customerPage),
                HttpStatus.OK
        );
    }

    @GetMapping(path = {"/get-customer-name-pagination/{name},{page}"}) // customer page
    public ResponseEntity<StandardResponse> getCustomerPageByName(
            @PathVariable (value = "name")String name,
            @PathVariable(value = "page") int page) {
        int size = 0;
        PaginatedResponseCustomerNameDTO customerPage = customerService.getCustomerPageByName(name, page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Get all active customers successfully ", customerPage),
                HttpStatus.OK
        );
    }
}
