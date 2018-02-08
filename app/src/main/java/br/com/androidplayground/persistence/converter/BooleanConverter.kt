package br.com.androidplayground.persistence.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @rodrigohsb
 */
class BooleanConverter {

    @TypeConverter
    fun intToBoolean(value : Int?) = value == 1

    @TypeConverter
    fun booleanToInt(bool : Boolean?) : Int{
        bool?.apply {
            return if(bool) 1 else 0
        }
        return 0
    }
}