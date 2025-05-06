package br.com.emersonmendes.hexagonal.application.ports.out;

import br.com.emersonmendes.hexagonal.application.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerOutputPort {

    Customer save(Customer customer);

    Optional<Customer> findById(String id);

    List<Customer> findAll();

}
