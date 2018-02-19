package br.com.androidplayground.register

import br.com.androidplayground.FileHandler
import br.com.androidplayground.persistence.RetrieveLabels
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

/**
 * @rodrigohsb
 */
class RetrieveLabelsTest {

    @Mock
    private lateinit var retrieveLabels: RetrieveLabels

    private val reader = FileHandler()

    @Before
    fun setup(){
        initMocks(this)
    }

    @Test
    fun `test when labels is fine`(){

        val list = fetchLabelsFrom("mock_register_labels.json")
        whenever(retrieveLabels.fetchAll()).thenReturn(list)
        assertThat(retrieveLabels.fetchAll().size, equalTo(list.size))
    }

    @Test
    fun `test when has no labels`(){

        val list = fetchLabelsFrom("mock_empty_register_labels.json")
        whenever(retrieveLabels.fetchAll()).thenReturn(list)
        assertThat(retrieveLabels.fetchAll().size, equalTo(emptyList<String>().size))

    }

    private fun fetchLabelsFrom(fileName: String) : List<String>{

        val fileContent = readContentFrom(fileName)

        return Gson()
                .fromJson(fileContent, Array<String>::class.java)
                .toList()

    }

    private fun readContentFrom(fileName: String) =
            reader.readResource(fileName)

}