package com.shoppingcar.demo.controller;

import com.shoppingcar.demo.dto.ClientDto;
import com.shoppingcar.demo.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Single;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Slf4j
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public static void writeLog(String text) {

        log.error(text);

    }


    /**
     * POST  /clients : Create a new client.
     *
     * @param clientDTO the clientDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clientDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDTO) throws URISyntaxException {
        log.debug("REST request to save Client : {}", clientDTO);

        ClientDto result = clientService.save(clientDTO);
        return ResponseEntity.created(new URI("/api/clients/" + result.getId()))
                .body(result);
    }

    /**
     * GET  /clients : get all the clients.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of clients in body
     */
    @GetMapping("")
    public List<ClientDto> getAllClients() {
        log.debug("REST request to get all clients");
        return clientService.findAll();
    }


    @GetMapping(
            value = "{clientId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<Object> findAllById(@PathVariable("clientId") String id) {
        log.debug("REST request to get all clients");
        return clientService.findAllById(Long.parseLong(id));
    }

}
