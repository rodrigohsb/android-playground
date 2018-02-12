package br.com.androidplayground.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.androidplayground.home.entryModel.ContactEntryModel
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
class HomeViewModel(private val retrieveContacts: RetrieveContacts): ViewModel() {

    var isLoading = MutableLiveData<Boolean>()

    var contacts = MutableLiveData<List<ContactEntryModel>>()

    fun loadContent(){

        isLoading.value = true

        convertToEntryModel(retrieveContacts.fetchAll())

        isLoading.value = false
    }

    private fun convertToEntryModel(all: List<Client>) {

        val arrayList = ArrayList<ContactEntryModel>()
        all?.forEach { it ->

            val prefix = it.name
                            .split(" ")
                            .take(3)
                            .map { it.first() }
                            .joinToString("").toUpperCase()

            arrayList.add(ContactEntryModel(prefix, it.name, it.company.fantasyName))
        }
        contacts.value = arrayList
    }
}