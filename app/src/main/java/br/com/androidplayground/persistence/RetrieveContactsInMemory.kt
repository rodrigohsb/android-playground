package br.com.androidplayground.persistence

import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.R
import com.google.gson.Gson

/**
 * @rodrigohsb
 */
class RetrieveContactsInMemory : RetrieveContacts {

    override fun fetchAll(): List<Client> {
        return fromFile()
    }

    private fun fromFile(): List<Client> {
        val jsonMock = ReadFile().read(R.raw.mock_clients)
        return convertJsonToClients(jsonMock).toList()
    }

    private fun convertJsonToClients(json: String) =
            Gson().fromJson(json, Array<Client>::class.java)
}