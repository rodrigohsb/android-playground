package br.com.androidplayground.contacts

import br.com.androidplayground.home.dto.ContactDTO
import br.com.androidplayground.home.viewmodel.HomeViewModel
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
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
class ContactsProviderTest {

    @Mock
    lateinit var retrieveContacts : RetrieveContacts

    lateinit var homeViewModel : HomeViewModel

    lateinit var company : Company
    lateinit var client : Client
    lateinit var clients : ArrayList<Client>

    lateinit var clientDTOs : ArrayList<ContactDTO>

    @Before
    fun setup(){

        initMocks(this)
        homeViewModel = HomeViewModel(retrieveContacts)

        createClient()

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

        clientDTOs = homeViewModel.convertToDTO(clients)

        assertEquals(clientDTOs[0].prefix, "R")
    }

    @Test
    fun `test prefix with two letters`(){

        client.name = "Rodrigo Haus"

        clientDTOs = homeViewModel.convertToDTO(clients)

        assertEquals(clientDTOs[0].prefix, "RH")
    }

    @Test
    fun `test prefix with three letters`(){

        client.name = "Rodrigo Haus da"

        clientDTOs = homeViewModel.convertToDTO(clients)

        assertEquals(clientDTOs[0].prefix, "RHD")
    }

    @Test
    fun `test prefix with max letters`(){

       client.name = "Rodrigo Haus da Silva Bacellar"

        clientDTOs = homeViewModel.convertToDTO(clients)

        assertEquals(clientDTOs[0].prefix, "RHD")
        assertEquals(clientDTOs[0].companyName, "Haus SA")
        assertEquals(clientDTOs[0].name, "Rodrigo Haus da Silva Bacellar")
    }
}