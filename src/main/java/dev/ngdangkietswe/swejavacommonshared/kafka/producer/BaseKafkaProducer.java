package dev.ngdangkietswe.swejavacommonshared.kafka.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author ngdangkietswe
 * @since 12/14/2024
 */

@Log4j2
public abstract class BaseKafkaProducer<T> {

    protected final KafkaTemplate<String, T> kafkaTemplate;

    public BaseKafkaProducer(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(final String topic, T message) {
        CompletableFuture<SendResult<String, T>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (Objects.isNull(ex)) {
                log.info("Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message=[{}] due to {}", message, ex.getMessage());
            }
        });
    }
}
