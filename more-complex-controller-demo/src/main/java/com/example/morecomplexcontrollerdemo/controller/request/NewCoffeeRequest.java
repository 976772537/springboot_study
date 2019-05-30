package com.example.morecomplexcontrollerdemo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class NewCoffeeRequest {
    @NotBlank
    private String name;
    @NotNull
    private Money price;
}
