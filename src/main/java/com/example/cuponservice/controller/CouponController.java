package com.example.cuponservice.controller;

import com.example.cuponservice.dto.CouponDto;
import com.example.cuponservice.service.CouponService;
import com.example.cuponservice.ui.CouponResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController

public class CouponController {
    private final ModelMapper modelMapper;
    private final CouponService couponService;

    public CouponController(ModelMapper modelMapper, CouponService couponService) {
        this.modelMapper = modelMapper;
        this.couponService = couponService;
    }
    @PostMapping("/coupons")
    public ResponseEntity<CouponResponseModel> createCoupon()
    {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Double discount=1.0* (new Random().nextInt(100));
        CouponDto couponDto=new CouponDto(UUID.randomUUID().toString(),discount);
        //couponDto.setCouponCode(UUID.randomUUID().toString());
        couponDto=couponService.createCoupon(couponDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(couponDto,CouponResponseModel.class));
    }
    @GetMapping("/coupons")
    public ResponseEntity<List<CouponResponseModel>> getCoupons()
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<CouponResponseModel> list=new ArrayList<>();
        List<CouponDto> dtos=couponService.getCoupons();
        for (CouponDto d:dtos)
        {
            list.add(modelMapper.map(d,CouponResponseModel.class));
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{couponCode}")
    public ResponseEntity<CouponResponseModel> findCouponByCouponCode(@PathVariable("couponCode") String couponCode)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return ResponseEntity.ok(modelMapper.map(couponService.findCouponByCouponCode(couponCode),CouponResponseModel.class));

    }
}
