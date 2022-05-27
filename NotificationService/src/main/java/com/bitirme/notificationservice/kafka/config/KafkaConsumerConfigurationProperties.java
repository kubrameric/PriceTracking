package com.bitirme.notificationservice.kafka.config;

import javax.validation.constraints.NotNull;

public class KafkaConsumerConfigurationProperties {

    @NotNull
    private String bootstrapServers;
    @NotNull
    private String keySerializer;
    @NotNull
    private String valueSerializer;
    @NotNull
    private String acks;
    @NotNull
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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
