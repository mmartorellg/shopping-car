package com.shoppingcar.demo.service;

import com.shoppingcar.demo.dto.ClientDto;
import rx.Single;

import java.util.List;

/**
 * Service interface for the Client entity.
 */
public interface ClientService {

    ClientDto save(ClientDto clientDto);

    List<ClientDto> findAll();

    Single<Object> findAllById(Long id);
}
