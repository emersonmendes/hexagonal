package br.com.emersonmendes.hexagonal.adapter.in.web.mapper;

import br.com.emersonmendes.hexagonal.adapter.in.web.request.AddressRequest;
import br.com.emersonmendes.hexagonal.adapter.in.web.request.CustomerRequest;
import br.com.emersonmendes.hexagonal.adapter.in.web.response.CustomerResponse;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import jakarta.validation.Valid;

import static br.com.emersonmendes.hexagonal.adapter.in.web.mapper.AddressControllerMapper.toAddress;
import static br.com.emersonmendes.hexagonal.adapter.in.web.mapper.AddressControllerMapper.toAddressResponse;

public class CustomerControllerMapper {

    public static Customer toCustomer(@Valid CustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        AddressRequest addressRequest = request.getAddress();
        customer.setAddress(toAddress(addressRequest));
        return customer;
    }

    public static CustomerResponse toCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setName(customer.getName());
        customerResponse.setAddress(toAddressResponse(customer.getAddress()));
        return customerResponse;
    }
}
