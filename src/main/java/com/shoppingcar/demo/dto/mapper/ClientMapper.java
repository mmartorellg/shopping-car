package com.shoppingcar.demo.dto.mapper;

import com.shoppingcar.demo.dto.ClientDto;
import com.shoppingcar.demo.model.domain.Client;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDto, Client> {

}
