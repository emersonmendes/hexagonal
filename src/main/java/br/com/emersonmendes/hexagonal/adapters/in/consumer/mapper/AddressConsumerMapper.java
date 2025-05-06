package br.com.emersonmendes.hexagonal.adapters.in.consumer.mapper;

import br.com.emersonmendes.hexagonal.adapters.in.consumer.request.AddressRequest;
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
