package br.com.emersonmendes.hexagonal.adapter.out.repository.mapper;

import br.com.emersonmendes.hexagonal.adapter.out.repository.entity.AddressEntity;
import br.com.emersonmendes.hexagonal.adapter.out.repository.entity.CustomerEntity;
import br.com.emersonmendes.hexagonal.application.core.domain.Address;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;

import static br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.AddressRepositoryMapper.toAddress;
import static br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.AddressRepositoryMapper.toAddressEntity;

public class CustomerRepositoryMapper {

    public static CustomerEntity toCustomerEntity(Customer customer) {
        Address address = customer.getAddress();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        AddressEntity addressEntity = toAddressEntity(address);
        customerEntity.setAddress(addressEntity);
        return customerEntity;
    }

    public static Customer toCustomer(CustomerEntity customerEntity) {
        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setAddress(toAddress(customerEntity.getAddress()));
        return customer;
    }

}
