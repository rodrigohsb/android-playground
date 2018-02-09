package br.com.androidplayground.persistence

import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
interface RetrieveContacts {
    fun fetchAll(): List<Client>
}