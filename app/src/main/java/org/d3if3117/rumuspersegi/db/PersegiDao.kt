package org.d3if3117.rumuspersegi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersegiDao {
    @Insert
    fun insert(persegi: PersegiEntity)
    @Query("SELECT * FROM persegi ORDER BY id DESC")
    fun getLastPersegi(): LiveData<List<PersegiEntity>>
    @Query("DELETE FROM persegi")
    fun clearData()
}