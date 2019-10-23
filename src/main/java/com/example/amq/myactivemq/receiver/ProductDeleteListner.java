package com.example.amq.myactivemq.receiver;

import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.amq.myactivemq.model.MyMessage;
import com.example.amq.myactivemq.model.Product;
import com.example.amq.myactivemq.restclient.RestClient;

@Component
public class ProductDeleteListner {

    private static Logger log = LoggerFactory.getLogger(ProductDeleteListner.class);
    
    @Autowired
    private RestClient restClient;
    
    @JmsListener(destination = "${product.delete.queue}")
    public void receiveMessage(@Payload MyMessage message,
                               @Headers MessageHeaders headers) {
    	log.info("######          Message Details           #####");
        log.info("received <" + message + ">");

        log.info("headers: " + headers);
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        
        restClient.delete("PROD1-2019");
    }

}