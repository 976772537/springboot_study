package com.example.redis_respositorydemo.repository;

import com.example.redis_respositorydemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
