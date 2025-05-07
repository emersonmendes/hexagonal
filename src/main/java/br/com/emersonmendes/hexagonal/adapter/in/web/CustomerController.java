package br.com.emersonmendes.hexagonal.adapter.in.web;

import br.com.emersonmendes.hexagonal.adapter.in.web.mapper.CustomerControllerMapper;
import br.com.emersonmendes.hexagonal.adapter.in.web.request.CustomerRequest;
import br.com.emersonmendes.hexagonal.adapter.in.web.response.CustomerResponse;
import br.com.emersonmendes.hexagonal.application.core.domain.Customer;
import br.com.emersonmendes.hexagonal.application.ports.in.CustomerInputPort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static br.com.emersonmendes.hexagonal.adapter.in.web.mapper.CustomerControllerMapper.toCustomer;
import static br.com.emersonmendes.hexagonal.adapter.in.web.mapper.CustomerControllerMapper.toCustomerResponse;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerInputPort customerInputPort;

    public CustomerController(CustomerInputPort customerInputPort) {
        this.customerInputPort = customerInputPort;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerRequest request) {
        Customer customer = toCustomer(request);
        Customer customerResponse = customerInputPort.save(customer);
        return ResponseEntity
            .created(URI.create("/api/v1/customers/" + customerResponse.getId()))
            .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> find(@PathVariable String id) {
        var customer = customerInputPort.findById(id);
        return ResponseEntity.ok(toCustomerResponse(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        var customers = customerInputPort.findAll();
        return ResponseEntity.ok(
            customers.stream()
                .map(CustomerControllerMapper::toCustomerResponse)
                .toList()
        );
    }

}
