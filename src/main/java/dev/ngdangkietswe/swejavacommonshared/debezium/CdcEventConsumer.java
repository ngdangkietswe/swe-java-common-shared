package dev.ngdangkietswe.swejavacommonshared.debezium;

import dev.ngdangkietswe.swejavacommonshared.json.JsonConverter;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.function.Consumer;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@Log4j2
public abstract class CdcEventConsumer implements Consumer<ConsumerRecord<String, String>> {

    @Override
    public void accept(ConsumerRecord<String, String> record) {
        try {
            CdcEvent cdcEvent = JsonConverter.fromJson(record.value(), CdcEvent.class);
            assert cdcEvent != null;
            log.debug("Request to handle {} event in the topic {}", cdcEvent.getOperationType().name(), record.topic());
            this.process(cdcEvent, record.topic());
        } catch (Exception e) {
            log.error("Error when processing CdcEvent: {}", e.getMessage());
        }
    }

    protected abstract void process(CdcEvent cdcEvent, String topic);
}
