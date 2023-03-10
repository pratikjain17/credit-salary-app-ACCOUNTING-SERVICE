package com.pratik.www.salary.configuration;

import com.pratik.www.salary.model.EmployeeEntity;
import com.pratik.www.salary.model.EmployeeLeaveEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public ConsumerFactory<String, EmployeeEntity> EmployeeEntityConsumer()
    {

        // Creating a Map of string-object pairs
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                "group_id");
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"com.pratik.www.employee.db.entity.EmployeeEntity");
        config.put(JsonDeserializer.TYPE_MAPPINGS,"com.pratik.www.employee.db.entity.EmployeeEntity:com.pratik.www.salary.model.EmployeeEntity");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(EmployeeEntity.class));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmployeeEntity>
    EmployeeEntityListener()
    {
        ConcurrentKafkaListenerContainerFactory<
                String, EmployeeEntity> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(EmployeeEntityConsumer());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, EmployeeLeaveEntity> EmployeeLeaveEntityConsumer()
    {

        // Creating a Map of string-object pairs
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
                "group_id");
        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"com.pratik.www.employeeleave.db.entity.EmployeeLeaveEntity");
        config.put(JsonDeserializer.TYPE_MAPPINGS,"om.pratik.www.employeeleave.db.entity.EmployeeLeaveEntity:com.pratik.www.salary.model.EmployeeLeaveEntity");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(EmployeeLeaveEntity.class));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmployeeLeaveEntity>
    EmployeeLeaveEntityListener()
    {
        ConcurrentKafkaListenerContainerFactory<
                String, EmployeeLeaveEntity> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(EmployeeLeaveEntityConsumer());
        return factory;
    }
}
