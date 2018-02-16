package br.com.androidplayground.persistence

import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
class RetrieveEmptyContacts : RetrieveContacts {

    override fun fetchAll(): List<Client> {
        return emptyList()
    }
}