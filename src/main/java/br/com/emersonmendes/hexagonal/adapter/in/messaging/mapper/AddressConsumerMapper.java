package br.com.emersonmendes.hexagonal.adapter.in.messaging.mapper;

import br.com.emersonmendes.hexagonal.adapter.in.messaging.request.AddressRequest;
import br.com.emersonmendes.hexagonal.application.core.domain.Address;

public class AddressConsumerMapper {

    public static Address toAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setNumber(addressRequest.getNumber());
        address.setStreet(addressRequest.getStreet());
        address.setCep(addressRequest.getCep());
        return address;
    }

}
