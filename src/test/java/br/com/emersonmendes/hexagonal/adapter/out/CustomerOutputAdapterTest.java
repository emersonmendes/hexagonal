package br.com.emersonmendes.hexagonal.adapter.out;

import br.com.emersonmendes.hexagonal.adapter.out.repository.CustomerRepository;
import br.com.emersonmendes.hexagonal.adapter.out.repository.entity.CustomerEntity;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static br.com.emersonmendes.hexagonal.adapter.out.repository.mapper.CustomerRepositoryMapper.toCustomerEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class CustomerOutputAdapterTest {

    private CustomerOutputAdapter adapter;
    private CustomerRepository repository;

    @BeforeEach
    public void setup(){
        this.repository = Mockito.mock(CustomerRepository.class);
        this.adapter = new CustomerOutputAdapter(this.repository);
    }

    @Test
    void shouldSaveCustomer() {

        // Arrange

        var customer = Instancio.of(Customer.class).create();
        var customerEntity = toCustomerEntity(customer);

        when(this.repository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        // Act

        Customer response = this.adapter.save(customer);

        // Assert

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(customer.getName());

    }

    @Test
    void shouldFindCustomerById() {

    }

    @Test
    void shouldFindAllCustomers() {

    }

}