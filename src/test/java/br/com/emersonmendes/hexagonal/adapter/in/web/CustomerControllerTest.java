package br.com.emersonmendes.hexagonal.adapter.in.web;

import br.com.emersonmendes.hexagonal.adapter.in.web.request.CustomerRequest;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.in.CustomerInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static br.com.emersonmendes.hexagonal.adapter.in.web.mapper.CustomerControllerMapper.toCustomer;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    private MockMvc mockMvc;

    @MockitoBean
    private CustomerInputPort customerInputPort;

    private final WebApplicationContext webApplicationContext;

    public CustomerControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void shouldSaveCustomer() throws Exception {

        // Arrange

        var customerRequest = Instancio.of(CustomerRequest.class).create();
        var customerResponse = toCustomer(customerRequest);
        customerResponse.setId("1");

        when(customerInputPort.save(any())).thenReturn(customerResponse);

        // Act

        ResultActions resultActions = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/v1/customers")
                .content(new ObjectMapper().writeValueAsString(customerRequest))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert

        resultActions
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/v1/customers/1"));

    }

    @Test
    void shouldFindCustomerById() throws Exception {

        // Arrange
        var customerResponse = Instancio.of(Customer.class).create();

        when(customerInputPort.findById(eq("1"))).thenReturn(customerResponse);

        // Act
        ResultActions resultActions = this.mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isNotEmpty());

    }

    @Test
    void shouldFindAllCustomers() throws Exception {

        // Arrange
        var customerResponse = List.of(Instancio.of(Customer.class).create());

        when(customerInputPort.findAll()).thenReturn(customerResponse);

        // Act
        ResultActions resultActions = this.mockMvc.perform(
            MockMvcRequestBuilders
                .get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id").isNotEmpty())
            .andExpect(jsonPath("$.[0].name").isNotEmpty())
            .andExpect(jsonPath("$.[0].address").isNotEmpty());

    }

}