package br.com.emersonmendes.hexagonal.application.core.usecase;

import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.out.CustomerOutputPort;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


class CustomerUseCaseTest {

    private CustomerUseCase useCase;
    private CustomerOutputPort port;

    @BeforeEach
    public void setup(){
        this.port = Mockito.mock(CustomerOutputPort.class);
        this.useCase = new CustomerUseCase(this.port);
    }

    @Test
    void shouldSaveCustomer() {

        // Arrange
        var customer = Instancio.of(Customer.class).create();
        when(this.port.save(eq(customer))).thenReturn(customer);

        // Act
        Customer response = this.useCase.save(customer);

        // Assert
        assertThat(response).isNotNull();

    }

    @Test
    void shouldFindCustomerById() {

        // Arrange
        var customer = Instancio.of(Customer.class).create();
        when(this.port.findById(eq("1"))).thenReturn(Optional.of(customer));

        // Act
        Customer response = this.useCase.findById("1");

        // Assert
        assertThat(response).isNotNull();

    }

    @Test
    void shouldFindAllCustomers() {

        // Arrange
        var customer = Instancio.of(Customer.class).create();
        when(this.port.findAll()).thenReturn(List.of(customer));

        // Act
        var response = this.useCase.findAll();

        // Assert
        assertThat(response).isNotEmpty();

    }

}