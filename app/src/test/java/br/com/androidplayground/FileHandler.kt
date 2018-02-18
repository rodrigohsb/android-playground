package br.com.androidplayground

import java.io.File
import java.util.*

/**
 * @rodrigohsb
 */
class FileHandler {

    fun readResource(fileName: String): String {


        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource(fileName).path)

        try {
            val scanner = Scanner(file)

            val result = StringBuffer()

            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                result.append(line).append("\n")
            }

            scanner.close()
            return result.toString()

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        throw Exception()
    }
}