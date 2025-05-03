package br.com.emersonmendes.hexagonal.application.core.usecase;

import br.com.emersonmendes.hexagonal.application.core.domain.Address;
import br.com.emersonmendes.hexagonal.application.ports.in.AddressInputPort;
import br.com.emersonmendes.hexagonal.application.ports.out.AddressOutputPort;
import org.springframework.stereotype.Component;

@Component
public class AddressUseCase implements AddressInputPort {

    private final AddressOutputPort addressOutputPort;

    public AddressUseCase(AddressOutputPort addressOutputPort) {
        this.addressOutputPort = addressOutputPort;
    }

    @Override
    public Address findByCep(String cep) {
        return addressOutputPort.findByCep(cep);
    }

}
