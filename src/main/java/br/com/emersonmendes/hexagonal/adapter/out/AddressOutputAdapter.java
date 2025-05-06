package br.com.emersonmendes.hexagonal.adapter.out;

import br.com.emersonmendes.hexagonal.adapter.out.client.AddressClient;
import br.com.emersonmendes.hexagonal.application.core.domain.Address;
import br.com.emersonmendes.hexagonal.application.ports.out.AddressOutputPort;
import org.springframework.stereotype.Component;

import static br.com.emersonmendes.hexagonal.adapter.out.client.mapper.AddressResponseMapper.toAddress;

@Component
public class AddressOutputAdapter implements AddressOutputPort {

    private final AddressClient addressClient;

    public AddressOutputAdapter(AddressClient addressClient) {
        this.addressClient = addressClient;
    }

    @Override
    public Address findByCep(String cep) {
        var addressResponse = addressClient.findAddressByCep(cep);
        return toAddress(addressResponse);
    }

}
