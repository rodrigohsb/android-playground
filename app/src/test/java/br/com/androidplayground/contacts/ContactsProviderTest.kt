package br.com.androidplayground.contacts

import br.com.androidplayground.home.entryModel.ContactEntryModel
import br.com.androidplayground.home.handler.ContactsHandler
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import java.util.*
import kotlin.collections.ArrayList

/**
 * @rodrigohsb
 */
class ContactsProviderTest {

    @Mock
    lateinit var retrieveContacts : RetrieveContacts

    private lateinit var contactHandler: ContactsHandler

    private lateinit var company : Company
    private lateinit var client : Client
    private lateinit var clients : ArrayList<Client>

    private lateinit var clientEntryModels: ArrayList<ContactEntryModel>

    @Before
    fun setup(){

        initMocks(this)
        contactHandler = ContactsHandler()

        createClient()
        
        `when`(retrieveContacts.fetchAll()).thenReturn(clients)
    }

    private fun createClient() {
        company = Company(fantasyName = "Haus SA",
                cnpj = "123456789",
                since = Date(),
                isMei = true)

        client = Client(id = 0,
                name = "Rodrigo",
                email = "teste@teste.com.br",
                phone = "21999999999",
                company = company)

        clients = ArrayList<Client>()
        clients.add(client)
    }

    @Test
    fun `test prefix with one letter`(){

        client.name = "Rodrigo"

        clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "R")
    }

    @Test
    fun `test prefix with two letters`(){

        client.name = "Rodrigo Haus"

        clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "RH")
    }

    @Test
    fun `test prefix with three letters`(){

        client.name = "Rodrigo Haus da"

        clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "RHD")
    }

    @Test
    fun `test prefix with max letters`(){

       client.name = "Rodrigo Haus da Silva Bacellar"

        clientEntryModels = contactHandler.handleContacts(clients)

        assertEquals(clientEntryModels[0].prefix, "RHD")
        assertEquals(clientEntryModels[0].companyName, "Haus SA")
        assertEquals(clientEntryModels[0].name, "Rodrigo Haus da Silva Bacellar")
    }
}