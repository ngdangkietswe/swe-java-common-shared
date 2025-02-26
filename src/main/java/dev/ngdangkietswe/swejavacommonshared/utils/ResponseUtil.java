package dev.ngdangkietswe.swejavacommonshared.utils;

import dev.ngdangkietswe.sweprotobufshared.common.protobuf.Error;

import java.util.Map;

/**
 * @author ngdangkietswe
 * @since 2/26/2025
 */

public class ResponseUtil {

    public static Error.Builder asFailedResponse(int errorCode, String errorMessage) {
        return Error.newBuilder()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }

    public static Error.Builder asFailedResponse(int errorCode, String errorMessage, Map<String, String> errorDetails) {
        return Error.newBuilder()
                .setCode(errorCode)
                .setMessage(errorMessage)
                .putAllDetails(errorDetails);
    }
}
