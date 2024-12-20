package com.project.shopapp.entity;

import jakarta.persistence.*;

public class CartItemAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    @Column(name = "att_id")
    private VariantAttribute attribute;
}
