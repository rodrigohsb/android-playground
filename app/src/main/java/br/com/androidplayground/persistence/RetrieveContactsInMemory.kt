package br.com.androidplayground.persistence

import br.com.androidplayground.R
import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
import com.google.gson.Gson
import java.util.*

/**
 * @rodrigohsb
 */
class RetrieveContactsInMemory : RetrieveContacts {

    override fun fetchAll(): List<Client>? {

        return fromMemory()
//        return fromFile()
    }

    private fun fromMemory(): List<Client>? {

        val company = Company()
        company.since = Date()
        company.cnpj = "123456789"
        company.fantasyName = "Haus SA"
        company.isMei = true

        val client = Client()
        client.email = "teste@teste.com.br"
        client.phone = "21999999999"
        client.id = 0
        client.name = "Rodrigo Haus"
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

    private fun fromFile(): List<Client> {
        val jsonMock = ReadFile().read(R.raw.mock_clients_no_content)
        return convertJsonToClients(jsonMock).toList()
    }

    private fun convertJsonToClients(json: String) =
            Gson().fromJson(json, Array<Client>::class.java)
}