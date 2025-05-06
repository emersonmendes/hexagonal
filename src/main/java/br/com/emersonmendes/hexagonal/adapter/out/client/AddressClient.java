package br.com.emersonmendes.hexagonal.adapter.out.client;

import br.com.emersonmendes.hexagonal.adapter.out.client.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "AddressClient", url = "${external.client.address.url}" )
public interface AddressClient {

    @GetMapping("/{cep}")
    AddressResponse findAddressByCep(@PathVariable("cep") String cep);

}
