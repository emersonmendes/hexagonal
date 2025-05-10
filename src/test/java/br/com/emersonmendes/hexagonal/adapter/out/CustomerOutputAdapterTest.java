package br.com.emersonmendes.hexagonal.adapter.out;

import br.com.emersonmendes.hexagonal.adapter.out.repository.CustomerRepository;
import br.com.emersonmendes.hexagonal.adapter.out.repository.entity.CustomerEntity;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

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

        var response = this.adapter.save(customer);

        // Assert

        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(customer.getName());

    }

    @Test
    void shouldFindCustomerById() {

        // Arrange
        var customer = Instancio.of(CustomerEntity.class).create();
        when(this.repository.findById(eq("1"))).thenReturn(Optional.of(customer));

        // Act
        var optionalCustomer = this.adapter.findById("1");

        // Assert
        assertThat(optionalCustomer.isPresent()).isTrue();
        assertThat(optionalCustomer.get().getName()).isEqualTo(customer.getName());
        assertThat(optionalCustomer.get().getId()).isEqualTo(customer.getId());

    }

    @Test
    void shouldFindAllCustomers() {

        // Arrange
        var customer = Instancio.of(CustomerEntity.class).create();
        when(this.repository.findAll()).thenReturn(List.of(customer));

        // Act
        var customers = this.adapter.findAll();

        // Assert
        assertThat(customers).isNotEmpty();

    }

}