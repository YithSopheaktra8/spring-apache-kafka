package co.istad.kafka.kafka;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final StreamBridge streamBridge;

    public KafkaProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void sendMessage(String message) {
        streamBridge.send("output-topic",   message);
    }
}
