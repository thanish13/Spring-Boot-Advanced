package org.t13.app.config;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.t13.app.entity.Employee;

import java.util.Properties;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.app-id}")
    private String applicationID;

    @Value(value = "${spring.kafka.schema-url}")
    private String schemaRegistryAddress;

    @Value(value = "${spring.kafka.group-id}")
    private String groupId;

    @Bean
    public Properties properties() {
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, applicationID);
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProps.put(JsonDeserializer.VALUE_TYPE_METHOD, Employee.class);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return consumerProps;
    }

    @Bean
    public KafkaConsumer<String, String> consumer(Properties properties) {
        return new KafkaConsumer<>(properties);
    }
}