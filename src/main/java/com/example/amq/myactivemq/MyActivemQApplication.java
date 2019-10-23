package com.example.amq.myactivemq;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.amq.myactivemq.model.MyMessage;
import com.example.amq.myactivemq.sender.MessageSender;

@SpringBootApplication
public class MyActivemQApplication implements ApplicationRunner {

	private static Logger log = LoggerFactory.getLogger(MyActivemQApplication.class);

	@Autowired
	private MessageSender orderSender;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		log.info("Spring Boot Embedded ActiveMQ Configuration Example");

		MyMessage createRequest = new MyMessage("create");
		orderSender.send(createRequest);
		
//		MyMessage updateRequest = new MyMessage("update");
//		orderSender.send(updateRequest);
		
//		MyMessage deleteRequest = new MyMessage("delete");
//		orderSender.send(deleteRequest);

		log.info("Waiting for all ActiveMQ JMS Messages to be consumed");
		TimeUnit.SECONDS.sleep(5);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MyActivemQApplication.class, args);
	}
}
