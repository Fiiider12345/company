package com.project.company.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.company.exception.ResourceNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	public Client saveClient(Client client) {
		return clientRepository.save(client);
	}

	public Optional<Client> getClientById(Long id) {
		return clientRepository.findByClientId(id);
	}

	public void deleteClient(Long id) {
		Client oldClient = clientRepository.findByClientId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id: " + id));

		clientRepository.delete(oldClient);
	}

	public Client updateClient(Long id, Client client) {
		Client oldClient = clientRepository.findByClientId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id: " + id));

		oldClient.setClientName(client.getClientName());

		return clientRepository.save(oldClient);
	}
}
