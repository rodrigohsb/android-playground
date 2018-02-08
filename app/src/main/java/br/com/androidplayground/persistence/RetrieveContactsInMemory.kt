package br.com.androidplayground.persistence

import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
import java.util.*

/**
 * @rodrigohsb
 */
class RetrieveContactsInMemory : RetrieveContacts {

    override fun fetchAll(): List<Client> {
        return createInMemory()
    }

    private fun createInMemory(): List<Client> {

        val company = Company()
        company.since = Date()
        company.cnpj = "123456789"
        company.fantasyName = "Haus SA"
        company.isMei = true

        val client = Client()
        client.email = "teste@teste.com.br"
        client.phone = "21999999999"
        client.id = 0
        client.name = "Rodrigo Haus da Silva Bacellar"
        client.company = company

        val clients = ArrayList<Client>()
        clients.add(client)
        clients.add(client)
        clients.add(client)
        clients.add(client)
        clients.add(client)
        clients.add(client)
        clients.add(client)
        clients.add(client)
        clients.add(client)

        return clients
    }
}