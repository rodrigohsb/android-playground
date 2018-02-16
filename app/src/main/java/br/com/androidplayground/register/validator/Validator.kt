package br.com.androidplayground.register.validator

import br.com.androidplayground.register.entryModel.RegisterEntryModel

/**
 * @rodrigohsb
 */
class Validator(private val cnpjValidator: CNPJValidator,
                private val dateValidator: DateValidator,
                private val emailValidator: EmailValidator,
                private val fantasyNameValidator: FantasyNameValidator,
                private val nameValidator: NameValidator,
                private val phoneValidator: PhoneValidator) {

    fun isValid(register: RegisterEntryModel): Boolean {
        return cnpjValidator.isValid(register.cnpj)
                && dateValidator.isValid(register.date)
                && emailValidator.isValid(register.email)
                && fantasyNameValidator.isValid(register.fantasyName)
                && nameValidator.isValid(register.name)
                && phoneValidator.isValid(register.phone)
    }
}