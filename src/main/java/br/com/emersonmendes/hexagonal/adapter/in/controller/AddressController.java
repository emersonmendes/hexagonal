package br.com.emersonmendes.hexagonal.adapter.in.controller;

import br.com.emersonmendes.hexagonal.adapter.in.controller.response.AddressResponse;
import br.com.emersonmendes.hexagonal.application.ports.in.AddressInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.emersonmendes.hexagonal.adapter.in.controller.mapper.AddressControllerMapper.toAddressResponse;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private final AddressInputPort addressInputPort;

    public AddressController(AddressInputPort addressInputPort) {
        this.addressInputPort = addressInputPort;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<AddressResponse> find(@PathVariable String cep) {
        var address = addressInputPort.findByCep(cep);
        return ResponseEntity.ok(toAddressResponse(address));
    }

}
