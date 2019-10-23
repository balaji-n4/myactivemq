package com.example.amq.myactivemq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.example.amq.myactivemq.model.MyMessage;

@Service
public class MessageSender {

	private static Logger log = LoggerFactory.getLogger(MessageSender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${product.create.queue}")
	private String createqueue;

	@Value("${product.update.queue}")
	private String updatequeue;

	@Value("${product.delete.queue}")
	private String deletequeue;

	public void send(MyMessage myMessage) {
		log.info("sending with convertAndSend() to queue <" + myMessage + ">");
		if (myMessage.getAction().equalsIgnoreCase("create")) {
			jmsTemplate.convertAndSend(createqueue, myMessage);
		} else if (myMessage.getAction().equalsIgnoreCase("update")) {
			jmsTemplate.convertAndSend(updatequeue, myMessage);
		} else if (myMessage.getAction().equalsIgnoreCase("delete")) {
			jmsTemplate.convertAndSend(deletequeue, myMessage);
		}
	}
}