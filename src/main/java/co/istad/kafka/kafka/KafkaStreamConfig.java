package co.istad.kafka.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;

import java.util.Properties;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Value("${spring.cloud.stream.kafka.binder.brokers}")
    private String bootstrapServers;

    @Value("${spring.application.name}")
    private String applicationId;

    @Bean
    public StreamsBuilderFactoryBeanConfigurer streamsBuilderFactoryBeanConfigurer() {
        return factoryBean -> {
            Properties config = new Properties();
            config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            config.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
            config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
            config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
            factoryBean.setStreamsConfiguration(config);
        };
    }
}
