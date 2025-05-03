package br.com.emersonmendes.hexagonal.application.ports.in;

import br.com.emersonmendes.hexagonal.application.core.domain.Address;

public interface AddressInputPort {

    Address findByCep(String cep);

}
