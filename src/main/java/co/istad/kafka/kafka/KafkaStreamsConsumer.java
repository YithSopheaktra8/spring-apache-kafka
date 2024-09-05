package co.istad.kafka.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class KafkaStreamsConsumer {

    @Bean
    public KStream<String, String> kStream(StreamsBuilder builder) {

        KStream<String, String> stream = builder.stream("input-topic");

//         Convert the values to uppercase
        KStream<String, String> transformedStream = stream.mapValues(value -> value.toUpperCase());
        // Send the transformed messages to the output topic
        transformedStream.to("output-topic", Produced.with(Serdes.String(), Serdes.String()));


        KStream<String, String> stream1 = builder.stream("input-topic");
        KStream<String, String> stream2 = builder.stream("output-topic");

        KStream<String, String> joined = stream1.join(stream2,
                (value1, value2) -> value1 + " " + value2,
                JoinWindows.of(Duration.ofSeconds(10))
        );

        joined.to("joined-topic", Produced.with(Serdes.String(), Serdes.String()));

        return joined;
    }
}