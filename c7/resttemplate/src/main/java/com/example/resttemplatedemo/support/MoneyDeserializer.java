package com.example.resttemplatedemo.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
@Slf4j
@JsonComponent
public class MoneyDeserializer extends StdDeserializer<Money> {

    protected MoneyDeserializer() {
        super (Money.class);
    }

    @Override
    public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Money cny = Money.of (CurrencyUnit.of ("CNY"), jsonParser.getDecimalValue ());
        log.info ("des被执行.... Money : {}",cny.toString ());
        return cny;
    }
}
