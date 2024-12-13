package dev.ngdangkietswe.swejavacommonshared.debezium;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.ngdangkietswe.swejavacommonshared.deserializer.MillisToLocalDateTimeDeserializer;
import dev.ngdangkietswe.swejavacommonshared.deserializer.OperationTypeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

@Data
public class CdcEvent implements Serializable {

    private Source source;
    private Map<String, Object> before;
    private Map<String, Object> after;
    @JsonProperty("op")
    @JsonDeserialize(using = OperationTypeDeserializer.class)
    private OperationType operationType;

    @Data
    public static class Source {
        @JsonProperty("ts_ms")
        @JsonDeserialize(using = MillisToLocalDateTimeDeserializer.class)
        private LocalDateTime transactionTime;
        private String connector;
        private String name;
        private String db;
        private String schema;
        private String table;
        @JsonProperty("txId")
        private Long transactionId;
    }

    @Getter
    @AllArgsConstructor
    public enum OperationType {
        CREATE("c"),
        UPDATE("u"),
        DELETE("d"),
        READ("r");

        private final String type;

        public static OperationType from(String type) {
            return Arrays.stream(OperationType.values())
                    .filter(ot -> ot.getType().equals(type))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid operation type: " + type));
        }
    }
}
