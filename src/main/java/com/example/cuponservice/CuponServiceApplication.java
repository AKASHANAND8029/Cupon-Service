package com.example.cuponservice;

import com.example.cuponservice.entity.CouponEntity;
import com.example.cuponservice.repo.CouponRepository;
import com.example.cuponservice.ui.ErrorResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.UUID;

@EnableEurekaClient
@SpringBootApplication
public class CuponServiceApplication implements CommandLineRunner {
    private final CouponRepository couponRepository;

    public CuponServiceApplication(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CuponServiceApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    @Bean
    public ErrorResponseModel errorResponseModel()
    {
        return  new ErrorResponseModel();
    }


    @Override
    public void run(String... args) throws Exception {
couponRepository.save(new CouponEntity(new Random().nextInt(1000), UUID.randomUUID().toString(),10.8d));
        couponRepository.save(new CouponEntity(new Random().nextInt(1000), UUID.randomUUID().toString(),11.8d));
        couponRepository.save(new CouponEntity(new Random().nextInt(1000), UUID.randomUUID().toString(),12.8d));
        couponRepository.save(new CouponEntity(new Random().nextInt(1000), UUID.randomUUID().toString(),13.8d));
    }
}
