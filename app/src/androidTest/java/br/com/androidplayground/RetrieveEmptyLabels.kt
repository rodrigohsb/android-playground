package br.com.androidplayground

import br.com.androidplayground.persistence.RetrieveLabels

/**
 * @rodrigohsb
 */
class RetrieveEmptyLabels : RetrieveLabels {

    override fun fetchAll(): List<String> {
        return ArrayList<String>()
    }
}