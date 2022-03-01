package com.example.cuponservice.repo;

import com.example.cuponservice.entity.CouponEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends CrudRepository<CouponEntity,Integer> {
    @Query
    public CouponEntity findByCouponCode(String userId);
}
