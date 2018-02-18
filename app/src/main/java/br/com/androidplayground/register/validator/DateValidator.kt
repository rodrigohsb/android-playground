package br.com.androidplayground.register.validator

import java.util.*

/**
 * @rodrigohsb
 */
class DateValidator: IValidator{
    override fun isValid(any: Any): Boolean {

        val date = any as Date

        return try{
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, -50)

            date.after(calendar.time) && date.before(Date())
        }catch (e: Exception){
            false
        }
    }
}