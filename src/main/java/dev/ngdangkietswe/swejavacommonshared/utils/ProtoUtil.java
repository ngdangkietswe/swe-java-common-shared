package dev.ngdangkietswe.swejavacommonshared.utils;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

import java.util.Objects;

/**
 * @author ngdangkietswe
 * @since 12/30/2024
 */

public class ProtoUtil {

    public static <B extends GeneratedMessageV3> Object getProtobufFieldValue(String fieldName, B protobufMessage) {
        var fieldDescriptor = protobufMessage.getDescriptorForType().findFieldByName(fieldName);
        return Objects.nonNull(fieldDescriptor) ? protobufMessage.getField(fieldDescriptor) : null;
    }

    public static <B extends GeneratedMessageV3.Builder<B>> Descriptors.FieldDescriptor getFieldDescriptor(String fieldName, B builder) {
        return builder.getDescriptorForType().findFieldByName(fieldName);
    }
}
