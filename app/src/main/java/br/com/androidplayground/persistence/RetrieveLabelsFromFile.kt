package br.com.androidplayground.persistence

import br.com.androidplayground.R
import com.google.gson.Gson


/**
 * @rodrigohsb
 */
class RetrieveLabelsFromFile : RetrieveLabels {

    override fun fetchAll(): List<String> {
        val jsonMock = ReadFile().read(R.raw.mock_register_labels)
        return convert(jsonMock).toList()
    }

    private fun convert(json: String) =
            Gson().fromJson(json, Array<String>::class.java)
}