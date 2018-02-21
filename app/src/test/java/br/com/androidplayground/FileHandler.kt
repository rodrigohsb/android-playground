package br.com.androidplayground

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * @rodrigohsb
 */
class FileHandler {

    fun readResource(fileName: String): String {

        val classLoader = javaClass.classLoader
        val inputStream = classLoader.getResourceAsStream(fileName)
        val isr = InputStreamReader(inputStream)
        val br = BufferedReader(isr)
        return br.readLine()
    }
}