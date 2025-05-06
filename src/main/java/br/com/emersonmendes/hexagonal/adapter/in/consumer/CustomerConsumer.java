package br.com.emersonmendes.hexagonal.adapter.in.consumer;

import br.com.emersonmendes.hexagonal.adapter.in.consumer.request.CustomerRequest;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.in.CustomerInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static br.com.emersonmendes.hexagonal.adapter.in.consumer.mapper.CustomerConsumerMapper.toCustomer;

@Component
public class CustomerConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CustomerConsumer.class);

    private final CustomerInputPort customerInputPort;

    public CustomerConsumer(CustomerInputPort customerInputPort) {
        this.customerInputPort = customerInputPort;
    }

    @KafkaListener(topics = "customers", groupId = "customer-group")
    public void consume(CustomerRequest request) {
        logger.info("Consumer received: {}", request);
        Customer customer = toCustomer(request);
        customerInputPort.save(customer);
        logger.info("Consumed customer: {}", customer);
    }

}
