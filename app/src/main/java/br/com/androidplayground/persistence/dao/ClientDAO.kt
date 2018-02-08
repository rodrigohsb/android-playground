package br.com.androidplayground.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.androidplayground.persistence.model.Client

/**
 * @rodrigohsb
 */
@Dao
interface ClientDAO {

    private companion object {
        const val FETCH_ALL = "SELECT * FROM ${Client.TABLE_NAME}"
        const val FETCH_CLIENT_BY_ID = "SELECT * FROM ${Client.TABLE_NAME} WHERE id = :clientId"
    }

    @Query(FETCH_ALL)
    fun fetchAll(): LiveData<List<Client>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(u: Client)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(u: Client)

    @Query(FETCH_CLIENT_BY_ID)
    fun fetchById(clientId: Int): Client

    @Delete
    fun delete(u: Client)

}