package com.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@ToString(callSuper = true)
@Table(name = "T_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CoffeeOrder extends  BaseEntity implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name="T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
