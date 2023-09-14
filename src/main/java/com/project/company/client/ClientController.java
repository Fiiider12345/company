package com.project.company.client;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {

	private ClientService clientService;

	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping
	public List<Client> getAllClients() {
		return clientService.getAllClients();
	}

	@PostMapping
	public Client createClient(@RequestBody Client client) {
		return clientService.saveClient(client);
	}

	@GetMapping("{id}")
	public Optional<Client> getClientById(@PathVariable("id") Long id) {
		return clientService.getClientById(id);
	}

	@DeleteMapping("{id}")
	public void deleteClient(@PathVariable("id") Long id) {
		clientService.deleteClient(id);
	}

	@PutMapping("{id}")
	public Client updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
		return clientService.updateClient(id, client);
	}
}
