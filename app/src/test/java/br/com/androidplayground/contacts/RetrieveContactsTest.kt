package br.com.androidplayground.contacts

import br.com.androidplayground.home.handler.ContactsHandler
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import java.util.*
import kotlin.collections.ArrayList

/**
 * @rodrigohsb
 */
class RetrieveContactsTest {

    private lateinit var contactHandler: ContactsHandler

    @Mock
    private lateinit var retrieveContacts: RetrieveContacts

    private lateinit var client : Client
    private lateinit var clients : ArrayList<Client>

    @Before
    fun setup(){
        initMocks(this)
        contactHandler = ContactsHandler()

        createClient()
    }

    private fun createClient() {
        val company = Company(fantasyName = "Haus SA",
                                cnpj = "",
                                since = Date(),
                                isMei = true)

        client = Client(name = "",
                        email = "",
                        phone = "",
                        company = company)

        clients = ArrayList<Client>()
        clients.add(client)

        whenever(retrieveContacts.fetchAll()).thenReturn(clients)
    }

    @Test
    fun `test prefix with one letter`(){

        client.name = "Rodrigo"

        val clientEntryModels = contactHandler.handleContacts(retrieveContacts.fetchAll())

        assertEquals(clientEntryModels[0].prefix,"R")
    }

    @Test
    fun `test prefix with two letters`(){

        client.name = "Rodrigo Haus"

        val clientEntryModels = contactHandler.handleContacts(retrieveContacts.fetchAll())

        assertEquals(clientEntryModels[0].prefix,"RH")
    }

    @Test
    fun `test prefix with three letters`(){

        client.name = "Rodrigo Haus da"

        val clientEntryModels = contactHandler.handleContacts(retrieveContacts.fetchAll())
        assertEquals(clientEntryModels[0].prefix,"RHD")
    }

    @Test
    fun `test prefix with max letters`(){

       client.name = "Rodrigo Haus da Silva Bacellar"

        val clientEntryModels = contactHandler.handleContacts(retrieveContacts.fetchAll())

        assertEquals(clientEntryModels[0].prefix, "RHD")
        assertEquals(clientEntryModels[0].companyName, "Haus SA")
        assertEquals(clientEntryModels[0].name, "Rodrigo Haus da Silva Bacellar")
    }
}