package br.com.androidplayground.register

import com.nhaarman.mockito_kotlin.whenever
import br.com.androidplayground.persistence.RetrieveLabels
import br.com.androidplayground.persistence.dao.ClientDAO
import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
import br.com.androidplayground.register.entryModel.RegisterEntryModel
import br.com.androidplayground.register.handler.ContactHandler
import br.com.androidplayground.register.validator.Validator
import br.com.androidplayground.register.viewmodel.RegisterViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import java.util.*

/**
 * @rodrigohsb
 */
class ViewModelTest {

    @Mock
    private lateinit var retrieveLabels: RetrieveLabels
    @Mock
    private lateinit var validator: Validator
    @Mock
    private lateinit var contactHandler: ContactHandler
    @Mock
    private lateinit var clientDAO: ClientDAO

    private lateinit var registerViewModel: RegisterViewModel

    private lateinit var fineRegisterViewModel: RegisterEntryModel

    private lateinit var wrongRegisterViewModel: RegisterEntryModel

    private lateinit var company: Company
    private lateinit var client: Client
    
    @Before
    fun setup(){
        initMocks(this)
        registerViewModel = RegisterViewModel(retrieveLabels,validator,contactHandler,clientDAO)

        val name = "Rodrigo Haus"
        val cnpj = "82173818000170"

        val now = Date()
        val oldDate = Date(-535057200000)

        val email = "rodrigo.haus@gmail.com"
        val fantasyName = "Haus LTDA"
        val phone = "21999999999"
        val isIMEI = true

        fineRegisterViewModel = RegisterEntryModel(name,cnpj,now,email,fantasyName,phone,isIMEI)
        wrongRegisterViewModel = RegisterEntryModel(name,cnpj,oldDate,email,fantasyName,phone,isIMEI)

        company = createCompany()
        client = createClient(company)
    }

    private fun createClient(company: Company): Client {
        val client = Client()
        client.name = fineRegisterViewModel.name
        client.phone = fineRegisterViewModel.phone
        client.email = fineRegisterViewModel.email
        client.company = company
        return client
    }

    private fun createCompany(): Company {
        val company = Company()
        company.cnpj = fineRegisterViewModel.cnpj
        company.since = fineRegisterViewModel.date
        company.fantasyName = fineRegisterViewModel.fantasyName
        company.isMei = fineRegisterViewModel.isMEI
        return company
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

    @Test
    fun `test when data is fine`(){

        whenever(validator.isValid(any())).thenReturn(true)

        val isValid = registerViewModel.valid(fineRegisterViewModel)


        verify(validator, times(1)).isValid(any())
        verify(contactHandler, times(1)).transform(any())
        verify(clientDAO, times(1)).insert(any())

        Assert.assertEquals(true, isValid)
    }

    @Test
    fun `test called clientDAO with right params`(){

        whenever(validator.isValid(any())).thenReturn(true)
        whenever(contactHandler.transform(any())).thenReturn(client)

        val isValid = registerViewModel.valid(fineRegisterViewModel)

        verify(validator, times(1)).isValid(any())
        verify(contactHandler, times(1)).transform(any())
        verify(clientDAO, times(1)).insert(client)

        Assert.assertEquals(true, isValid)
    }

    @Test
    fun `test when data is wrong`(){

        whenever(validator.isValid(any())).thenReturn(false)

        val isValid = registerViewModel.valid(wrongRegisterViewModel)

        verify(validator, times(1)).isValid(any())
        verify(contactHandler, times(0)).transform(any())
        verify(clientDAO, times(0)).insert(any())

        Assert.assertEquals(false, isValid)
    }
}