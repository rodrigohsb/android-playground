package br.com.androidplayground.persistence.model

import android.arch.persistence.room.*
import java.util.*

/**
 * @rodrigohsb
 */
data class Company(@ColumnInfo(name = FANTASY_NAME_COLUMN_NAME) var fantasyName : String,
              var cnpj : String,
              var since: Date,
              @ColumnInfo(name = IS_IMEI_COLUMN_NAME) var isMei: Boolean){

    @Ignore
    constructor() : this("","", Date(),true)

    companion object {
        const val FANTASY_NAME_COLUMN_NAME = "fantasy_name"
        const val IS_IMEI_COLUMN_NAME = "is_mei"
    }

}