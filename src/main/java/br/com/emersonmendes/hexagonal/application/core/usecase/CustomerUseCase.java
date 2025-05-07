package br.com.emersonmendes.hexagonal.application.core.usecase;

import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.in.CustomerInputPort;
import br.com.emersonmendes.hexagonal.application.ports.out.CustomerOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerUseCase implements CustomerInputPort {

    private final CustomerOutputPort customerOutputPort;

    public CustomerUseCase(CustomerOutputPort customerOutputPort) {
        this.customerOutputPort = customerOutputPort;
    }

    @Override
    public Customer save(Customer customer) {
        return customerOutputPort.save(customer);
    }

    @Override
    public Customer findById(String id) {
        return customerOutputPort.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer by id " + id + " not found"));
    }

    @Override
    public List<Customer> findAll() {
        return customerOutputPort.findAll();
    }

}
