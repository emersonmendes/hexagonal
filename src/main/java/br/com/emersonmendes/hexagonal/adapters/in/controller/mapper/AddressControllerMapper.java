package br.com.emersonmendes.hexagonal.adapters.in.controller.mapper;

import br.com.emersonmendes.hexagonal.adapters.in.controller.request.AddressRequest;
import br.com.emersonmendes.hexagonal.adapters.in.controller.response.AddressResponse;
import br.com.emersonmendes.hexagonal.application.core.domain.Address;

public class AddressControllerMapper {


    public static Address toAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setCity(addressRequest.getCity());
        address.setNumber(addressRequest.getNumber());
        address.setStreet(addressRequest.getStreet());
        address.setCep(addressRequest.getCep());
        return address;
    }

    public static AddressResponse toAddressResponse(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setCep(address.getCep());
        addressResponse.setCity(address.getCity());
        addressResponse.setStreet(address.getStreet());
        return addressResponse;
    }

}
