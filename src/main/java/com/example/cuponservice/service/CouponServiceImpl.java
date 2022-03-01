package com.example.cuponservice.service;

import com.example.cuponservice.dto.CouponDto;
import com.example.cuponservice.entity.CouponEntity;
import com.example.cuponservice.repo.CouponRepository;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cuponservice.exception.CouponNotFoundException;
@Service
public class CouponServiceImpl implements CouponService{
    private final ModelMapper modelMapper;
    private final CouponRepository couponRepository;
@Autowired
    public CouponServiceImpl(ModelMapper modelMapper, CouponRepository couponRepository) {
        this.modelMapper = modelMapper;
        this.couponRepository = couponRepository;
    }

    @Override
    public CouponDto createCoupon(CouponDto couponDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CouponEntity couponEntity = modelMapper.map(couponDto,CouponEntity.class);
        couponEntity=couponRepository.save(couponEntity);
        return modelMapper.map(couponEntity,CouponDto.class);
    }

    @Override
    public List<CouponDto> getCoupons() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<CouponDto> list=new ArrayList<>();
        Iterable<CouponEntity> iterable= couponRepository.findAll();

        Iterator<CouponEntity> iterator= iterable.iterator();
        while (iterator.hasNext())
        {
            list.add(modelMapper.map(iterator.next(),CouponDto.class));
        }
        return list;
    }
    private CouponEntity findCouponCode(String couponCode) {

        CouponEntity couponEntity= couponRepository.findByCouponCode(couponCode);

        System.out.println(couponEntity);
        return couponEntity;


    }
    @Override
    public CouponDto findCouponByCouponCode(String couponCode) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CouponEntity couponEntity= findCouponCode(couponCode);
        System.out.println(couponEntity);
        if(couponEntity==null)
        {
            throw new CouponNotFoundException("Coupon with code: "+couponCode+" not found");
        }
        return modelMapper.map(couponEntity,CouponDto.class);
    }

}
