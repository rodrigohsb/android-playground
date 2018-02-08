package br.com.androidplayground.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import br.com.androidplayground.home.dto.ContactDTO
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
class HomeViewModel(private val retrieveContacts: RetrieveContacts): ViewModel() {

    var isLoading = MutableLiveData<Boolean>()

    var contacts = MutableLiveData<List<ContactDTO>>()

    fun loadContent(){

        isLoading.value = true

        val allContacts = retrieveContacts.fetchAll()
        contacts.value = convertToDTO(allContacts)

        isLoading.value = false
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun convertToDTO(all: List<Client>?): ArrayList<ContactDTO> {

        val arrayList = ArrayList<ContactDTO>()
        all?.forEach { it ->

            val prefix = it.name
                    .split(" ")
                    .take(3)
                    .map { it.first() }
                    .joinToString("")
                    .toUpperCase()

            arrayList.add(ContactDTO(prefix, it.name, it.company.fantasyName))
        }
        return arrayList
    }

}