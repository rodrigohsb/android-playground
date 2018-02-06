package br.com.androidplayground.persistence.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @rodrigohsb
 */
class DateConverter {

    @TypeConverter
    fun longToDate(time : Long?) : Date?{
        time?.apply {
            return Date(time)
        }
        return null
    }

    @TypeConverter
    fun dateToLong(date : Date?) : Long?{
        date?.apply {
            return date.time
        }
        return null
    }

}