package br.com.emersonmendes.hexagonal.application.ports.in;

import br.com.emersonmendes.hexagonal.application.core.domain.Customer;

public interface CustomerInputPort {

    Customer save(Customer customer);

    Customer findById(String id);

}
