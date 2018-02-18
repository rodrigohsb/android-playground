package br.com.androidplayground.register

import br.com.androidplayground.register.validator.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * @rodrigohsb
 */
class ValidatorTest {

    private lateinit var cnpjValidator: CNPJValidator
    private lateinit var dateValidator: DateValidator
    private lateinit var emailValidator: EmailValidator
    private lateinit var fantasyNameValidator: FantasyNameValidator
    private lateinit var nameValidator: NameValidator
    private lateinit var phoneValidator: PhoneValidator

    @Before
    fun setup(){
        cnpjValidator = CNPJValidator()
        dateValidator = DateValidator()
        emailValidator = EmailValidator()
        fantasyNameValidator = FantasyNameValidator()
        nameValidator = NameValidator()
        phoneValidator =  PhoneValidator()
    }

    @Test
    fun `test when CNPJ is fine`(){
        val isValid = cnpjValidator.isValid("82173818000170")
        assertEquals(true, isValid)
    }

    @Test
    fun `test when CNPJ is wrong`(){
        val isValid = cnpjValidator.isValid("82173818000171")
        assertEquals(false, isValid)
    }

    @Test
    fun `test when CNPJ is less then 14 numbers`(){
        val isValid = cnpjValidator.isValid("821738100171")
        assertEquals(false, isValid)
    }

    @Test
    fun `test when CNPJ is greater than 14 numbers`(){
        val isValid = cnpjValidator.isValid("8217381800017123534543")
        assertEquals(false, isValid)
    }

    @Test
    fun `test when DATE is fine`(){
        val isValid = dateValidator.isValid(Date())
        assertEquals(true, isValid)
    }

    @Test
    fun `test when DATE is to old`(){
        val isValid = dateValidator.isValid(Date(-535057200000))
        assertEquals(false, isValid)
    }

    @Test
    fun `test when DATE is in the future`(){
        val isValid = dateValidator.isValid(Date(1600830000000))
        assertEquals(false, isValid)
    }

    @Test
    fun `test when EMAIL is fine`(){
        val isValid = emailValidator.isValid("rodrigo.haus@gmail.com")
        assertEquals(true, isValid)
    }

    @Test
    fun `test when EMAIL has multiples dots`(){
        val isValid = emailValidator.isValid("rodrigo...haus@gmail.com")
        assertEquals(false, isValid)
    }

    @Test
    fun `test EMAIL without at`(){
        val isValid = emailValidator.isValid("rodrigo.hausgmail.com")
        assertEquals(false, isValid)
    }

    @Test
    fun `test EMAIL without domain`(){
        val isValid = emailValidator.isValid("rodrigo.haus@gmai")
        assertEquals(false, isValid)
    }

    @Test
    fun `test when fantasy name is fine`(){
        val isValid = fantasyNameValidator.isValid("Rodrigo LTDA")
        assertEquals(true, isValid)
    }

    @Test
    fun `test when fantasy name has special character`(){
        val isValid = fantasyNameValidator.isValid("Rodrigo LTD@")
        assertEquals(false, isValid)
    }

    @Test
    fun `test when name if fine`(){
        val isValid = fantasyNameValidator.isValid("Rodrigo Haus")
        assertEquals(true, isValid)
    }

    @Test
    fun `test when name has special character`(){
        val isValid = nameValidator.isValid("Rodrig0 H@us")
        assertEquals(false, isValid)
    }

    @Test
    fun `test name without surname`(){
        val isValid = nameValidator.isValid("Rodrigo")
        assertEquals(false, isValid)
    }

    @Test
    fun `test name with multiples words`(){
        val isValid = nameValidator.isValid("Rodrigo Haus da Silva Bacellar")
        assertEquals(false, isValid)
    }

    @Test
    fun `test when phone is fine`(){
        val isValid = phoneValidator.isValid("21999999999")
        assertEquals(true, isValid)
    }

    @Test
    fun `test when phone has lettes`(){
        val isValid = phoneValidator.isValid("219a999b99J")
        assertEquals(false, isValid)
    }

    @Test
    fun `test phone with few numbers`(){
        val isValid = phoneValidator.isValid("2199999999")
        assertEquals(false, isValid)
    }

    @Test
    fun `test phone with too much numbers`(){
        val isValid = phoneValidator.isValid("21999999999999")
        assertEquals(false, isValid)
    }
}