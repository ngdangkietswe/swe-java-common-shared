package dev.ngdangkietswe.swejavacommonshared.constants;

/**
 * @author ngdangkietswe
 * @since 12/14/2024
 */

public class KafkaConstant {

    // TEMPLATE
    public static final String STRING_KAFKA_TEMPLATE = "StringKafkaTemplate";
    public static final String JSON_KAFKA_TEMPLATE = "JsonKafkaTemplate";

    // PRODUCER FACTORY
    public static final String JSON_KAFKA_PRODUCER_FACTORY = "JsonKafkaProducerFactory";
    public static final String STRING_KAFKA_PRODUCER_FACTORY = "StringKafkaProducerFactory";

    // CONSUMER FACTORY
    public static final String JSON_KAFKA_CONSUMER_FACTORY = "JsonKafkaConsumerFactory";
    public static final String STRING_KAFKA_CONSUMER_FACTORY = "StringKafkaConsumerFactory";

    // LISTENER FACTORY
    public static final String STRING_LISTENER_CONTAINER_FACTORY = "StringKafkaListenerContainerFactory";
    public static final String JSON_LISTENER_CONTAINER_FACTORY = "JsonKafkaListenerContainerFactory";

    // TOPIC
    public static final String CDC_AUTH_USER_TOPIC = "auth.sweauth.users";
}
