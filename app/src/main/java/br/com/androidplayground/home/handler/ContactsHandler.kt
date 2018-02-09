package br.com.androidplayground.home.handler

import br.com.androidplayground.home.entryModel.ContactEntryModel
import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
class ContactsHandler {

    fun handleContacts(all: List<Client>): ArrayList<ContactEntryModel> {

        val arrayList = ArrayList<ContactEntryModel>()
        all.forEach { it ->

            val prefix = it.name
                    .split(" ")
                    .take(3)
                    .map { it.first() }
                    .joinToString("").toUpperCase()

            arrayList.add(ContactEntryModel(prefix, it.name, it.company.fantasyName))
        }
        return arrayList
    }

}