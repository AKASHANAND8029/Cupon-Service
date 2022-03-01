package com.example.cuponservice.service;

import com.example.cuponservice.dto.CouponDto;

import java.util.List;

public interface CouponService {
    public CouponDto createCoupon(CouponDto  couponDto);
    public List<CouponDto> getCoupons();
    public CouponDto findCouponByCouponCode(String couponCode);
}
