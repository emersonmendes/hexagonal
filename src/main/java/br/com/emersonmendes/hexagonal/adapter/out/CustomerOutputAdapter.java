package br.com.emersonmendes.hexagonal.adapter.out;

import br.com.emersonmendes.hexagonal.adapter.out.repository.CustomerRepository;
import br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.CustomerRespositoryMapper;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.out.CustomerOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.CustomerRespositoryMapper.toCustomer;
import static br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.CustomerRespositoryMapper.toCustomerEntity;

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

    @Override
    public List<Customer> findAll() {
        var customerEntities = customerRepository.findAll();
        return customerEntities.stream()
            .map(CustomerRespositoryMapper::toCustomer)
            .collect(Collectors.toList());
    }

}
