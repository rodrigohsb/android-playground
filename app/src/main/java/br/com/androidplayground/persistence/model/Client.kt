package br.com.androidplayground.persistence.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import br.com.androidplayground.persistence.model.Client.Companion.TABLE_NAME

/**
 * @rodrigohsb
 */
@Entity(tableName = TABLE_NAME)
class Client(@PrimaryKey(autoGenerate = true) var id: Int,
             var name : String,
             var email: String,
             var phone: String,
             @Embedded var company: Company){
    @Ignore
    lateinit var prefix : String

    companion object {
        const val TABLE_NAME = "CLIENTS"
    }

}