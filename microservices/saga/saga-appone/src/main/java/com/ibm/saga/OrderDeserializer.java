package com.ibm.saga;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class OrderDeserializer implements Deserializer<Order> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Order deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Order.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing order", e);
        }
    }

    @Override
    public void close() {
    }
}
