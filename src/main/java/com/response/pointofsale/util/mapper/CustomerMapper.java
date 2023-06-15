package com.response.pointofsale.util.mapper;

import com.response.pointofsale.dto.CustomerDTO;
import com.response.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.response.pointofsale.dto.response.ResponseCustomerByNameDTO;
import com.response.pointofsale.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer RequestDtoToEntity(CustomerSaveRequestDTO customerSaveRequestDTO);

    List<CustomerDTO> entityListToDtoList(List<Customer> customers);

    CustomerDTO entityToDto(Customer customer);

    List<CustomerDTO> pageToList(Page<Customer> customerPage);

    List<ResponseCustomerByNameDTO> customerNamePage(Page<Customer> getPage);
}
