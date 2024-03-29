package com.example.aspect;

import com.example.aspect.model.Coffee;
import com.example.aspect.model.CoffeeOrder;
import com.example.aspect.model.OrderState;
import com.example.aspect.repository.CoffeeRepository;
import com.example.aspect.serivce.CoffeeOrderService;
import com.example.aspect.serivce.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@EnableAspectJAutoProxy
@SpringBootApplication
public class AspectApplication implements ApplicationRunner {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run (AspectApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info ("All Coffee: {}",coffeeRepository.findAll ());
        Optional<Coffee> latte = coffeeService.findOneCoffee ("Latte");
        if(latte.isPresent ()){
            CoffeeOrder order = orderService.createOrder ("Li Lei",latte.get ());
            log.info ("Update INIT to PAID :{}",orderService.updateState (order, OrderState.PAID));
            log.info ("Update INIT to INIT :{}",orderService.updateState (order, OrderState.INIT));
        }
    }
}
