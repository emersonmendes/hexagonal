package br.com.emersonmendes.hexagonal.adapter.in.consumer.mapper;

import br.com.emersonmendes.hexagonal.adapter.in.consumer.request.AddressRequest;
import br.com.emersonmendes.hexagonal.adapter.in.consumer.request.CustomerRequest;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import jakarta.validation.Valid;

import static br.com.emersonmendes.hexagonal.adapter.in.consumer.mapper.AddressConsumerMapper.toAddress;

public class CustomerConsumerMapper {

    public static Customer toCustomer(@Valid CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        AddressRequest addressRequest = request.getAddress();
        customer.setAddress(toAddress(addressRequest));
        return customer;
    }

}
