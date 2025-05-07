package br.com.emersonmendes.hexagonal.adapter.out.repository.mapper;

import br.com.emersonmendes.hexagonal.adapter.out.repository.entity.AddressEntity;
import br.com.emersonmendes.hexagonal.application.core.domain.Address;

public class AddressRepositoryMapper {

    public static AddressEntity toAddressEntity(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCep(address.getCep());
        addressEntity.setCity(address.getCity());
        addressEntity.setNumber(address.getNumber());
        addressEntity.setStreet(address.getStreet());
        return addressEntity;
    }

    public static Address toAddress(AddressEntity addressEntity) {
        Address address = new Address();
        address.setCep(addressEntity.getCep());
        address.setCity(addressEntity.getCity());
        address.setNumber(addressEntity.getNumber());
        address.setStreet(addressEntity.getStreet());
        return address;
    }
}
