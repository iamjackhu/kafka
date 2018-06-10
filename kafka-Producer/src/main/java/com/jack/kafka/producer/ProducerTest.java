package com.jack.kafka.producer;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerTest {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 1000);
		props.put("linger.ms", 1);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		
		Random rnd = new Random();
		long runtime = new Date().getTime();
		String ip = "192.168.2." + String.valueOf(rnd.nextInt(255));
		String msg = runtime + ",www.example.com," + ip;
		ProducerRecord<String, String> data = new ProducerRecord<String, String>("test", ip, msg);
		for (int i = 0; i < 100; i++) {
			producer.send(data);
			System.out.println(i + "sent");
		}

		producer.close();
	}

}
