package br.com.androidplayground.register.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.androidplayground.persistence.RetrieveLabels
import br.com.androidplayground.persistence.dao.ClientDAO
import br.com.androidplayground.register.entryModel.RegisterEntryModel
import br.com.androidplayground.register.handler.ContactHandler
import br.com.androidplayground.register.validator.*

/**
 * @rodrigohsb
 */
class RegisterViewModel(private val retrieveLabels: RetrieveLabels,
                        private val validator: Validator,
                        private val contactHandler: ContactHandler,
                        private val clientDAO: ClientDAO): ViewModel() {

    fun fetchLabels() =
            retrieveLabels.fetchAll()

    fun valid(register: RegisterEntryModel): Boolean{
        if(ableToSave(register)){
            val client = contactHandler.transform(register)
            clientDAO.insert(client)
            return true
        }
        return false
    }

    private fun ableToSave(register: RegisterEntryModel) =
            validator.isValid(register)
}