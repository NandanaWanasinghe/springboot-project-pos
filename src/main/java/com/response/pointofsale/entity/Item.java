package com.response.pointofsale.entity;

import com.response.pointofsale.entity.enums.MeasuringUnitType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "item")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Item {
    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_unit", length = 100, nullable = false)
    private MeasuringUnitType measuringUnit;

    @Column(name = "balance_qty", length = 100, nullable = false)
    private double balanceQty;

    @Column(name = "supplier_price", length = 100, nullable = false)
    private double supplierPrice;

    @Column(name = "selling_price", length = 100, nullable = false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy="items")
    private Set<OrderDetails> orderDetails;

    public Item(String itemName, MeasuringUnitType measuringUnit, double balanceQty, double supplierPrice, double sellingPrice, boolean activeState) {
        this.itemName = itemName;
        this.measuringUnit = measuringUnit;
        this.balanceQty = balanceQty;
        this.supplierPrice = supplierPrice;
        this.sellingPrice = sellingPrice;
        this.activeState = activeState;
    }
}

