package dev.ngdangkietswe.swejavacommonshared.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ngdangkietswe
 * @since 12/14/2024
 */

public class BaseKafkaProducerConfig {

    protected Map<String, Object> getDefaultProducerConfig(KafkaProperties properties) {
        Map<String, Object> configMap = new HashMap<>();

        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return configMap;
    }

    protected Map<String, Object> getJsonProducerConfig(KafkaProperties properties) {
        Map<String, Object> configMap = this.getDefaultProducerConfig(properties);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configMap;
    }

    protected Map<String, Object> getStringProducerConfig(KafkaProperties properties) {
        Map<String, Object> configMap = this.getDefaultProducerConfig(properties);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configMap;
    }

    protected ProducerFactory<String, Object> getJsonProducerFactory(KafkaProperties properties) {
        return new DefaultKafkaProducerFactory<>(this.getJsonProducerConfig(properties));
    }

    protected ProducerFactory<String, Object> getStringProducerFactory(KafkaProperties properties) {
        return new DefaultKafkaProducerFactory<>(this.getStringProducerConfig(properties));
    }

    protected KafkaTemplate<String, Object> getJsonKafkaTemplate(KafkaProperties properties) {
        return new KafkaTemplate<>(this.getJsonProducerFactory(properties));
    }

    protected KafkaTemplate<String, Object> getStringKafkaTemplate(KafkaProperties properties) {
        return new KafkaTemplate<>(this.getStringProducerFactory(properties));
    }
}
