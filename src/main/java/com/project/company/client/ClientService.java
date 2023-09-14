package com.project.company.client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

	List<Client> getAllClients();

	Client saveClient(Client client);

	Optional<Client> getClientById(Long id);

	void deleteClient(Long id);

	Client updateClient(Long id, Client client);
}
