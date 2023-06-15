package com.response.pointofsale.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "customer")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @Column(name = "customer_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 150)
    private String customerAddress;

    @Column(name = "customer_salary", length = 15)
    private double customerSalary;

    // set contact numbers as json
    @Column(name = "contact_numbers", columnDefinition = "json",length = 12)
    @Type(type = "json")
    private ArrayList contactNumbers;

    @Column(name = "nic", length = 12, unique = true)
    private String nic;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy="customer")
    private Set<Order> orders;

    public Customer(String customerName, String customerAddress, double customerSalary, ArrayList contactNumbers, String nic, boolean activeState) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
        this.contactNumbers = contactNumbers;
        this.nic = nic;
        this.activeState = activeState;
    }
}

