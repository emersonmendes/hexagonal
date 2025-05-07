package br.com.emersonmendes.hexagonal.adapter.out;

import br.com.emersonmendes.hexagonal.adapter.out.repository.CustomerRepository;
import br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.CustomerRepositoryMapper;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.out.CustomerOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.CustomerRepositoryMapper.toCustomer;
import static br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.CustomerRepositoryMapper.toCustomerEntity;

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
        return optionalCustomerEntity.map(CustomerRepositoryMapper::toCustomer);
    }

    @Override
    public List<Customer> findAll() {
        var customerEntities = customerRepository.findAll();
        return customerEntities.stream()
            .map(CustomerRepositoryMapper::toCustomer)
            .collect(Collectors.toList());
    }

}
