package br.com.androidplayground.persistence

import br.com.androidplayground.R
import com.google.gson.Gson

/**
 * @rodrigohsb
 */
class RetrieveLabelsInMemory : RetrieveLabels {

    override fun fetchAll(): List<String> {
        val labels = ArrayList<String>()
        labels.add("Nome Completo")
        labels.add("Email")
        labels.add("Telefone para contato")
        labels.add("Nome fantasia")
        labels.add("CNPJ")
        labels.add("Ativa desde")
        labels.add("É MEI?")
        return labels
    }
}