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

        return createInMemory()
//        return fetchFromFile()
    }

    private fun createInMemory(): List<Client>? {

        val company = Company()
        company.since = Date()
        company.cnpj = "123456789"
        company.fantasyName = "Haus SA"
        company.isMei = true

        val client = Client()
        client.prefix = "ABC"
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

    private fun fetchFromFile(): List<Client> {
        val jsonMock = ReadFile().read(R.raw.mock_clients_no_content)
        val mockClients = convertJsonToClients(jsonMock)

        mockClients?.forEach { it.prefix = extractLetters(it.name) }
        return mockClients.toList()
    }

    private fun convertJsonToClients(json: String) =
            Gson().fromJson(json, Array<Client>::class.java)

    private fun extractLetters(name: String) : String{

        val letters = firstLetters(name)

        var result = ""
        letters.forEach { result += it.value.toUpperCase() }

        if(result.length > 3) result = result.substring(0,3)

        return result
    }

    private fun firstLetters(name: String): Sequence<MatchResult> =
            """\b\w""".toRegex().findAll(name)
}