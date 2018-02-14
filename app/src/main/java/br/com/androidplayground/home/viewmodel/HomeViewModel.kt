package br.com.androidplayground.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.androidplayground.home.entryModel.ContactEntryModel
import br.com.androidplayground.home.handler.ContactsHandler
import br.com.androidplayground.persistence.RetrieveContacts

/**
 * @rodrigohsb
 */
class HomeViewModel(private val retrieveContacts: RetrieveContacts,
                    private val contactsHandler: ContactsHandler): ViewModel() {

    var isLoading = MutableLiveData<Boolean>()

    var contacts = MutableLiveData<List<ContactEntryModel>>()

    fun loadContent(){

        isLoading.value = true

        val allContacts = retrieveContacts.fetchAll()
        contacts.value = contactsHandler.handleContacts(allContacts)

        isLoading.value = false
    }
}