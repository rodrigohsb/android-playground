package br.com.androidplayground.contacts

import br.com.androidplayground.home.handler.ContactsHandler
import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.*
import kotlin.collections.ArrayList

/**
 * @rodrigohsb
 */
class ContactsProviderTest {

    private lateinit var contactHandler: ContactsHandler

    private lateinit var company : Company
    private lateinit var client : Client
    private lateinit var clients : ArrayList<Client>

    @Before
    fun setup(){

        contactHandler = ContactsHandler()

        createClient()
    }

    private fun createClient() {
        company = Company(fantasyName = "Haus SA",
                cnpj = anyString(),
                since = Date(),
                isMei = true)

        client = Client(id = 0,
                name = anyString(),
                email = anyString(),
                phone = anyString(),
                company = company)

        clients = ArrayList<Client>()
        clients.add(client)
    }

    @Test
    fun `test prefix with one letter`(){

        client.name = "Rodrigo"

        val clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "R")
    }

    @Test
    fun `test prefix with two letters`(){

        client.name = "Rodrigo Haus"

        val clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "RH")
    }

    @Test
    fun `test prefix with three letters`(){

        client.name = "Rodrigo Haus da"

        val clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "RHD")
    }

    @Test
    fun `test prefix with max letters`(){

       client.name = "Rodrigo Haus da Silva Bacellar"

        val clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "RHD")
        assertEquals(clientEntryModels[0].companyName, "Haus SA")
        assertEquals(clientEntryModels[0].name, "Rodrigo Haus da Silva Bacellar")
    }
}