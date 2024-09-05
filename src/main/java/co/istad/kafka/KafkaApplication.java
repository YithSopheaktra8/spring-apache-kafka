package co.istad.kafka;

import co.istad.kafka.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
@RequiredArgsConstructor
public class KafkaApplication{

	private final KafkaProducer kafkaProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}


}
