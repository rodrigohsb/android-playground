package br.com.androidplayground.persistence

import br.com.androidplayground.persistence.dao.ClientDAO
import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
class RetrieveContactsFromDatabase(private val clientDAO: ClientDAO) : RetrieveContacts {

    override fun fetchAll(): List<Client>? = clientDAO.fetchAll()

}