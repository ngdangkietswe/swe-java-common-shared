package dev.ngdangkietswe.swejavacommonshared.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import dev.ngdangkietswe.swejavacommonshared.debezium.CdcEvent;

import java.io.IOException;

/**
 * @author ngdangkietswe
 * @since 12/13/2024
 */

public class OperationTypeDeserializer extends JsonDeserializer<CdcEvent.OperationType> {

    @Override
    public CdcEvent.OperationType deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return CdcEvent.OperationType.from(jsonParser.getValueAsString());
    }
}
