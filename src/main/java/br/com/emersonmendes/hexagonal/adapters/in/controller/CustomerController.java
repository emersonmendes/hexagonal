package br.com.emersonmendes.hexagonal.adapters.in.controller;

import br.com.emersonmendes.hexagonal.adapters.in.controller.request.CustomerRequest;
import br.com.emersonmendes.hexagonal.adapters.in.controller.response.CustomerResponse;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.in.CustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static br.com.emersonmendes.hexagonal.adapters.in.controller.mapper.CustomerControllerMapper.toCustomer;
import static br.com.emersonmendes.hexagonal.adapters.in.controller.mapper.CustomerControllerMapper.toCustomerResponse;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerInputPort customerInputPort;

    public CustomerController(CustomerInputPort customerInputPort) {
        this.customerInputPort = customerInputPort;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> save( @Valid @RequestBody CustomerRequest request) {
        Customer customer = toCustomer(request);
        Customer customerResponse = customerInputPort.save(customer);
        return ResponseEntity.created(URI.create("/api/v1/customers/" + customerResponse.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> find(@PathVariable String id) {
        var customer = customerInputPort.findById(id);
        return ResponseEntity.ok(toCustomerResponse(customer));
    }

}
