package br.com.emersonmendes.hexagonal.application.ports.out;

import br.com.emersonmendes.hexagonal.application.core.domain.Address;

public interface AddressOutputPort {

    Address findByCep(String cep);

}
