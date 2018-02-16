package br.com.androidplayground.register.handler

import br.com.androidplayground.persistence.model.Client
import br.com.androidplayground.persistence.model.Company
import br.com.androidplayground.register.entryModel.RegisterEntryModel

/**
 * @rodrigohsb
 */
class ContactHandler {

    fun transform(registerEntryModel: RegisterEntryModel): Client{

        val company = Company(fantasyName = registerEntryModel.fantasyName,
                        cnpj = registerEntryModel.cnpj,
                        since = registerEntryModel.date,
                        isMei = registerEntryModel.isMEI)

        return Client(name = registerEntryModel.name,
                    email = registerEntryModel.email,
                    phone = registerEntryModel.phone,
                    company = company)
    }
}