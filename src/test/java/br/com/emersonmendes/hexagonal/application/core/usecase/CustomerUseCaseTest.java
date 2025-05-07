package br.com.emersonmendes.hexagonal.application.core.usecase;

import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.out.CustomerOutputPort;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    }

    @Test
    void shouldFindAllCustomers() {

    }

}