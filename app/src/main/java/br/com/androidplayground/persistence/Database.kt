package br.com.androidplayground.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import br.com.androidplayground.persistence.converter.DateConverter
import br.com.androidplayground.persistence.dao.ClientDAO
import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
@Database(version = 1, entities = [Client::class], exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class Database: RoomDatabase() {

    abstract fun clientDAO(): ClientDAO
}