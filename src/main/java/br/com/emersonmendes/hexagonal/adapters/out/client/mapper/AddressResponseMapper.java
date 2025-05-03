package br.com.emersonmendes.hexagonal.adapters.out.client.mapper;

import br.com.emersonmendes.hexagonal.adapters.out.client.response.AddressResponse;
import br.com.emersonmendes.hexagonal.application.core.domain.Address;

import java.util.Objects;

public class AddressResponseMapper {

    public static Address toAddress(AddressResponse addressResponse) {
        if(Objects.isNull(addressResponse)) {
            return null;
        }
        Address address = new Address();
        address.setCep(addressResponse.getCep());
        address.setCity(addressResponse.getCity());
        address.setStreet(addressResponse.getStreet());
        return address;
    }

}
