package br.com.androidplayground.persistence

import br.com.androidplayground.AppApplication
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @rodrigohsb
 */
class ReadFile {

    fun read(resourceId : Int): String{
        val inputStream = AppApplication.instance.resources.openRawResource(resourceId)
        val isr = InputStreamReader(inputStream)
        val br = BufferedReader(isr)
        return br.readLine()
    }
}