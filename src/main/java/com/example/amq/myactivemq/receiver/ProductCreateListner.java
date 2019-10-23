package com.example.amq.myactivemq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.amq.myactivemq.model.MyMessage;
import com.example.amq.myactivemq.model.Product;
import com.example.amq.myactivemq.restclient.RestClient;

@Component
public class ProductCreateListner {

    private static Logger log = LoggerFactory.getLogger(ProductCreateListner.class);
    
    @Autowired
    private RestClient restClient;

    @JmsListener(destination = "${product.create.queue}")
    public void receiveMessage(@Payload MyMessage message,
                               @Headers MessageHeaders headers) {
    	log.info("######          Message Details           #####");
        log.info("received <" + message + ">");

        log.info("headers: " + headers);
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        
        Product product = new Product();
		product.setProdId("PROD1-2019");
		product.setProdName("Laptop");
		product.setPrice("200000");
		restClient.create(product);
		
    }

}