package br.com.emersonmendes.hexagonal.adapters.out;

import br.com.emersonmendes.hexagonal.adapters.out.repository.CustomerRepository;
import br.com.emersonmendes.hexagonal.adapters.out.repository.mapper.CustomerRespositoryMapper;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.out.CustomerOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.emersonmendes.hexagonal.adapters.out.repository.mapper.CustomerRespositoryMapper.toCustomer;
import static br.com.emersonmendes.hexagonal.adapters.out.repository.mapper.CustomerRespositoryMapper.toCustomerEntity;

@Component
public class CustomerOutputAdapter implements CustomerOutputPort {

    private final CustomerRepository customerRepository;

    public CustomerOutputAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        var customerEntity = toCustomerEntity(customer);
        return toCustomer(customerRepository.save(customerEntity));
    }

    @Override
    public Optional<Customer> findById(String id) {
        var optionalCustomerEntity = customerRepository.findById(id);
        return optionalCustomerEntity.map(CustomerRespositoryMapper::toCustomer);
    }

}
