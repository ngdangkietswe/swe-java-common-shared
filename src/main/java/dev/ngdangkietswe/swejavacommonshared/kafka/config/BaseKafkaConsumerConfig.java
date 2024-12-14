package dev.ngdangkietswe.swejavacommonshared.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ngdangkietswe
 * @since 12/14/2024
 */

public class BaseKafkaConsumerConfig {

    protected Map<String, Object> getDefaultConsumerConfig(KafkaProperties properties) {
        Map<String, Object> configMap = new HashMap<>();

        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getConsumer().getGroupId());

        configMap.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);

        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return configMap;
    }

    protected Map<String, Object> getJsonConsumerConfig(KafkaProperties properties) {
        Map<String, Object> configMap = this.getDefaultConsumerConfig(properties);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configMap.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        return configMap;
    }

    protected Map<String, Object> getStringConsumerConfig(KafkaProperties properties) {
        Map<String, Object> configMap = this.getDefaultConsumerConfig(properties);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, StringDeserializer.class);
        return configMap;
    }

    protected ConsumerFactory<String, Object> getJsonConsumerFactory(KafkaProperties properties) {
        return new DefaultKafkaConsumerFactory<>(this.getJsonConsumerConfig(properties));
    }

    protected ConsumerFactory<String, Object> getStringConsumerFactory(KafkaProperties properties) {
        return new DefaultKafkaConsumerFactory<>(this.getStringConsumerConfig(properties));
    }

    protected KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> getJsonKafkaListenerContainerFactory(KafkaProperties properties) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.getJsonConsumerFactory(properties));
        return factory;
    }

    protected KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> getStringKafkaListenerContainerFactory(KafkaProperties properties) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.getStringConsumerFactory(properties));
        return factory;
    }
}
