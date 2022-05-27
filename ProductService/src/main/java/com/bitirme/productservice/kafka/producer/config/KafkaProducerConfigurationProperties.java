package com.bitirme.productservice.kafka.producer.config;

import javax.validation.constraints.NotNull;

public class KafkaProducerConfigurationProperties {
    @NotNull
    private String bootstrapServers;
    @NotNull
    private String keySerializer;
    @NotNull
    private String valueSerializer;
    @NotNull
    private String acks;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getKeySerializer() {
        return keySerializer;
    }

    public void setKeySerializer(String keySerializer) {
        this.keySerializer = keySerializer;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }
}
