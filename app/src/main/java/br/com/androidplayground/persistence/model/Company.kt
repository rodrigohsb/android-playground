package br.com.androidplayground.persistence.model

import android.arch.persistence.room.*
import java.util.*

/**
 * @rodrigohsb
 */
data class Company(@ColumnInfo(name = "fantasy_name") var fantasyName : String,
              var cnpj : String,
              var since: Date,
              @ColumnInfo(name = "is_mei") var isMei: Boolean){
    @Ignore
    constructor() : this("","", Date(),true)

}