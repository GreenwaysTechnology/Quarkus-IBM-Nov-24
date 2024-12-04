package com.ibm.event.sourcing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class StockDeserializer implements Deserializer<Stock> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Stock deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Stock.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing order", e);
        }
    }

    @Override
    public void close() {
    }
}
