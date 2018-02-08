package br.com.androidplayground.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.androidplayground.persistence.RetrieveContacts
import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
class HomeViewModel(private val retrieveContacts: RetrieveContacts): ViewModel() {

    var repositories = MutableLiveData<List<Client>>()
    var isLoading = MutableLiveData<Boolean>()

    fun reloadContent(){
        loadContent()
    }

    fun loadContent(){

        isLoading.value = true

        val clients = retrieveContacts.fetchAll()?.apply {
            forEach { it -> it.prefix = createPrefix(it.name) }
        }

        repositories.value = clients

        isLoading.value = false
    }

    private fun createPrefix(name: String) : String{

        val letters = extractPrefix(name)

        var result = ""
        letters.forEach { result += it.value.toUpperCase() }

        if(hasMoreThen3Letters(result))
            result = result.substring(0,3)

        return result
    }

    private fun hasMoreThen3Letters(result: String) = result.length > 3

    private fun extractPrefix(name: String): Sequence<MatchResult> =
            """\b\w""".toRegex().findAll(name)
}