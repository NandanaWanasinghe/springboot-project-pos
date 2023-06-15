package com.response.pointofsale.repo;

import com.response.pointofsale.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@EnableJpaRepositories
@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    @Modifying // update query
    @Query(value = "update customer set customer_name=?1, customer_address=?2, customer_salary=?3, active_state=?4 where customer_id=?5", nativeQuery = true)
    void updateCustomer(String customerName, String customerAddress, double customerSalary, boolean activeState, int id);

    @Modifying // delete query
    @Query(value = "delete from customer where customer_id=?1", nativeQuery = true)
    void deleteCustomer(int id);

    List<Customer> findAllByActiveStateEquals(boolean b);

    List<Customer> findAllByCustomerNameEquals(String name);

    List<Customer> findAllByCustomerNameEqualsAndActiveStateEquals(String name, boolean b);

    Customer findAllByNicEquals(String nic);

    int countCustomerByActiveStateEquals(boolean b);

    Page<Customer> findAllByCustomerNameEquals(String name, Pageable pageable);

    long countCustomerByCustomerNameEquals(String name);
}
