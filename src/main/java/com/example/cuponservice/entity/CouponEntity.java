package com.example.cuponservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.util.Random;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name="coupon_service")
public class CouponEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer couponId;
    @Column(name = "coupon_code",unique = true,nullable = false)
    private String couponCode;
    @Column(name = "discount",nullable = false)
    private Double discount;

     }
