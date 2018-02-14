package br.com.androidplayground.register.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.androidplayground.persistence.RetrieveLabels

/**
 * @rodrigohsb
 */
class RegisterViewModel(private val retrieveLabels: RetrieveLabels): ViewModel() {

    var labels = MutableLiveData<List<String>>()

    fun fetchLabels() {
        labels.value = retrieveLabels.fetchAll()
    }
}